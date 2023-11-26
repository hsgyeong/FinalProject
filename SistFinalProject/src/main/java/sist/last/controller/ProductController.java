package sist.last.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sist.last.dto.ProductDto;
import sist.last.service.NonBookableService;
import sist.last.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    ProductService pService;

    @Autowired
    NonBookableService nService;

    public ProductController() {
        ProductDto productDto = new ProductDto();
    }

    @GetMapping("/product/search-main")
    public String searchMainForm(@RequestParam String keyword, Model model,
                                 @RequestParam(required = false) String selDate1,
                                 @RequestParam(required = false) String selDate2,
                                 @RequestParam(required = false) String minPrice,
                                 @RequestParam(required = false) String maxPrice,
                                 @RequestParam(required = false) String sort) {
        List<String> category = pService.selectCategory();
        System.out.println(sort);
        model.addAttribute("keyword", keyword);
        model.addAttribute("category", category);
        if (selDate1 == null) {
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            selDate1 = today.format(formatter);
            selDate2 = tomorrow.format(formatter);
            //System.out.println(selDate1 + "," + selDate2);
        }
        processDateSelection(selDate1, selDate2, model);
        List<ProductDto> products = searchCompareKeyword(keyword);
        if (sort != null) {
            compareSort(keyword, sort, model, products);
            return "/product/searchMainPage";
        }
        if (products != null) {
            //System.out.println("selDate1이 null이 아님");
            if (minPrice == null) {
                model.addAttribute("productList", products);
            }
            products = compareLimitDate(products, selDate1, selDate2);
            if (products.isEmpty()) {
                model.addAttribute("productList", null);
                return "/product/searchMainPage";
            }
            //System.out.println(products.get(0).getAccom_name());
            //products = compareLimitPrice(products);
            if (minPrice != null) {
                processPriceSelection(minPrice, maxPrice, model, products);
                return "/product/searchMainPage";
            }
        }

        return "/product/searchMainPage";
    }

    private List<ProductDto> searchCompareKeyword(String keyword) {
        List<ProductDto> categoryList = pService.getProductDataOfCategory(keyword);
        if (!categoryList.isEmpty()) {
            return categoryList;
        }
        List<ProductDto> hashtagList = pService.getProductDataOfHashTag(keyword);
        if (!hashtagList.isEmpty()) {
            return hashtagList;
        }
        List<ProductDto> locationList = pService.getProductDataOfLocation(keyword);
        if (!locationList.isEmpty()) {
            return locationList;
        }
        List<ProductDto> nameList = pService.getProductDataOfName(keyword);
        if (!nameList.isEmpty()) {
            return nameList;
        }
        return null;
    }

    private void processDateSelection(String selDate1, String selDate2,
                                      Model model) {
        int[] splitDate1 = new int[3];
        int[] splitDate2 = new int[3];
        int sleep = 1;
        if (selDate1 != null) {
            splitDate1 = splitIntegerDay(selDate1);
            splitDate2 = splitIntegerDay(selDate2);
            sleep = calculateSleep(splitDate1, splitDate2);
        }
        model.addAttribute("selDate1", selDate1);
        model.addAttribute("selDate2", selDate2);
        model.addAttribute("firstYear", splitDate1[0]);
        model.addAttribute("firstMonth", splitDate1[1]);
        model.addAttribute("firstDay", splitDate1[2]);
        model.addAttribute("secondYear", splitDate2[0]);
        model.addAttribute("secondMonth", splitDate2[1]);
        model.addAttribute("secondDay", splitDate2[2]);
        model.addAttribute("sleep", sleep);
    }

    private void processPriceSelection(String minPrice, String maxPrice,
                                       Model model, List<ProductDto> products) {
        System.out.println("저장됨");
        int integerMinPrice = Integer.parseInt(minPrice);
        int integerMaxPrice = Integer.parseInt(maxPrice);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        if (integerMaxPrice == 300000) {
            selectLimitMinPrice(integerMinPrice, products, model);
        }
        if (integerMaxPrice != 300000) {
            selectLimitPrice(integerMinPrice, integerMaxPrice, products, model);
        }
    }

    private void compareSort(String keyword, String sort, Model model, List<ProductDto> products) {
        List<ProductDto> sortProducts = new ArrayList<>();
        if (sort.equals("distance")) {
        }
        if (sort.equals("lowprice")) {
            sortProducts = searchSortKeyword(keyword);
        }
        if (sort.equals("score")) {
            sortProducts = products;

        }
        model.addAttribute("productList", sortProducts);
    }

    private List<ProductDto> searchSortKeyword(String keyword) {
        List<ProductDto> sortCategory = pService.getProductClickOrderByLowPriceOfCategory(keyword);
        if (!sortCategory.isEmpty()) {
            return sortCategory;
        }
        List<ProductDto> hashtagList = pService.getProductClickOrderByLowPriceOfHashTag(keyword);
        if (!hashtagList.isEmpty()) {
            return hashtagList;
        }
        List<ProductDto> locationList = pService.getProductClickOrderByLowPriceOfLocation(keyword);
        if (!locationList.isEmpty()) {
            return locationList;
        }
        List<ProductDto> nameList = pService.getProductClickOrderByLowPriceOfName(keyword);
        if (!nameList.isEmpty()) {
            return nameList;
        }
        return null;
    }

    private void selectLimitMinPrice(int minPrice, List<ProductDto> products,
                                     Model model) {
        List<ProductDto> selectProductList = new ArrayList<>();
        for (ProductDto product : products) {
            int price = product.getRoom_price();
            if (price >= minPrice) {
                selectProductList.add(product);
            }
        }
        model.addAttribute("productList", selectProductList);
    }

    private void selectLimitPrice(int minPrice, int maxPrice, List<ProductDto> products,
                                  Model model) {
        List<ProductDto> selectProductList = new ArrayList<>();
        for (ProductDto product : products) {
            int price = product.getRoom_price();
            if (price >= minPrice && price <= maxPrice) {
                selectProductList.add(product);
            }
        }
        model.addAttribute("productList", selectProductList);
    }

    /*private List<ProductDto> secondSearchCompareKeyword(String keyword) {
        List<ProductDto> categoryList = pService.getProductDataOfCategory(keyword);
        if (!categoryList.isEmpty()) {
            return categoryList;
        }
        List<ProductDto> hashtagList = pService.getProductDataOfHashTag(keyword);
        if (!hashtagList.isEmpty()) {
            return hashtagList;
        }
        List<ProductDto> locationList = pService.getProductDataOfLocation(keyword);
        if (!locationList.isEmpty()) {
            return locationList;
        }
        List<ProductDto> nameList = pService.getProductDataOfName(keyword);
        if (!nameList.isEmpty()) {
            return nameList;
        }
        return null;
    }*/

    private List<ProductDto> compareLimitDate(List<ProductDto> products, String selDate1, String selDate2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        //System.out.println("제외날짜비교 로직 들어옴");
        LocalDate checkinDate = LocalDate.parse(selDate1, formatter);
        LocalDate checkoutDate = LocalDate.parse(selDate2, formatter);
        List<ProductDto> nonDuplicateList = new ArrayList<>();
        for (ProductDto dto : products) {
            //System.out.println("for문 안에 들어옴");
            int accom_num = dto.getAccom_num();
            //System.out.println("accomnum: " + accom_num);
            List<String> checkin = nService.getNonBookableCheckin(accom_num);
            List<String> checkout = nService.getNonBookableCheckout(accom_num);
            //System.out.println("list size : " + checkin.size());
            if (!duplicateDate(checkin, checkout, checkinDate, checkoutDate)) {
                nonDuplicateList.add(dto);
                //System.out.println("발생안해서 dto 저장");
                //System.out.println("리스트사이즈 : " + nonDuplicateList.size());
            }
        }
        return nonDuplicateList;
    }

    private boolean duplicateDate(List<String> checkinList, List<String> checkoutList,
                                  LocalDate checkinDate, LocalDate checkoutDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        for (int i = 0; i < checkinList.size(); i++) {
            //System.out.println(checkinDate + "," + checkoutDate + "," + checkinList.get(i) + "," + checkoutList.get(i));
            LocalDate existingCheckin = LocalDate.parse(checkinList.get(i), formatter);
            LocalDate existingCheckout = LocalDate.parse(checkoutList.get(i), formatter);

            // 새로운 체크인 날짜가 기존 기간에 포함되거나
            // 새로운 체크아웃 날짜가 기존 기간에 포함되는 경우 중복으로 처리
            if ((checkinDate.isBefore(existingCheckout) || checkinDate.isEqual(existingCheckout)) &&
                    (checkoutDate.isAfter(existingCheckin) || checkoutDate.isEqual(existingCheckin))) {
                //System.out.println("중복발생");
                return true; // 중복이 발생했음
            }
        }
        //System.out.println("중복발생x");
        return false; // 중복이 없음
    }

    private int[] splitIntegerDay(String selDate) {
        String[] splitDay = selDate.split("-");
        int[] integerDay = new int[splitDay.length];
        for (int i = 0; i < integerDay.length; i++) {
            integerDay[i] = Integer.parseInt(splitDay[i]);
            //System.out.println(integerDay[i]);
        }
        return integerDay;
    }

    private int calculateSleep(int[] first, int[] second) {
        return compareYear(first, second);
    }

    private int compareYear(int[] first, int[] second) {
        if (first[0] == second[0]) {
            return compareMonth(first, second);
        }
        return divideYear(first, second);
    }

    private int compareMonth(int[] first, int[] second) {
        if (first[1] == second[1]) {
            return compareDay(first, second);
        }
        return divideMonth(first, second);
    }

    private int compareDay(int[] first, int[] second) {
        return second[2] - first[2];
    }

    private int divideYear(int[] first, int[] second) {
        return 31 - first[2] + second[2];
    }

    private int divideMonth(int[] first, int[] second) {
        int firstDate = getDate(first[1], first[0]);

        return second[2] + firstDate - first[2];
    }

    private int getDate(int month, int year) {
        Calendar cal = Calendar.getInstance();

        cal.set(year, (month - 1), 1);

        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


}
