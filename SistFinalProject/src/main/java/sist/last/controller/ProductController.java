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

    String selectDate1;
    String selectDate2;
    List<String> categoryList;

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
                                 @RequestParam(required = false) String sort,
                                 @RequestParam(required = false) String selCate) {
        categoryList = new ArrayList<>();
        selectDate1 = selDate1;
        selectDate2 = selDate2;
        List<String> category = pService.selectCategory();
        if (selCate == "") {
            selCate = null;
        }
        System.out.println(sort + "," + selCate);
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
        if (selCate != null) {
            String[] splitCategory = selCate.split(",");
            for (int arrayIndex = 0; arrayIndex < splitCategory.length; arrayIndex++) {
                categoryList.add(splitCategory[arrayIndex].trim());
                model.addAttribute("selCate", categoryList);
            }
            assert products != null;
            products = compareSelectCategory(products);
        }
        //System.out.println(products.size() + " 개 의 데이터");
        if (products != null) {
            //System.out.println("selDate1이 null이 아님");
            products = compareLimitDate(products, selDate1, selDate2);
            if (sort != null) {
                System.out.println(products.size() + " 개 의 데이터");
                compareSort(sort, model, minPrice,
                        maxPrice, products, keyword);
                return "/product/searchMainPage";
            }
            if (minPrice == null) {
                model.addAttribute("productList", products);
            }
            if (products.isEmpty()) {
                model.addAttribute("productList", null);
                return "/product/searchMainPage";
            }
            //System.out.println(products.get(0).getAccom_name());
            //products = compareLimitPrice(products);
            if (minPrice != null) {
                //System.out.println("여기까진 들어감");
                System.out.println(products.size() + " 개 의 데이터");
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

    private List<ProductDto> compareSelectCategory(List<ProductDto> products) {
        List<ProductDto> result = new ArrayList<>();
        for (ProductDto product : products) {
            System.out.println(product.getAccom_name() + "의 카테고리 : " + product.getAccom_category());
            if (!isEqualInputCategory(product)) {
                continue;
            }
            result.add(product);
        }
        return result;
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
        System.out.println("min:" + integerMinPrice + ", max:" + integerMaxPrice);
        if (integerMaxPrice == 300000) {
            products = selectLimitMinPrice(integerMinPrice, products);
        }
        if (integerMaxPrice != 300000) {
            products = selectLimitPrice(integerMinPrice, integerMaxPrice, products);
        }
        model.addAttribute("productList", products);
    }

    private void compareSort(String sort, Model model, String minPrice,
                             String maxPrice, List<ProductDto> products,
                             String keyword) {
        int integerMinPrice = Integer.parseInt(minPrice);
        int integerMaxPrice = Integer.parseInt(maxPrice);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        if (integerMaxPrice == 300000) {
            products = selectLimitMinPrice(integerMinPrice, products);
        }
        if (integerMaxPrice != 300000) {
            products = selectLimitPrice(integerMinPrice, integerMaxPrice, products);
        }
        List<ProductDto> sortProducts = new ArrayList<>();
        if (sort.equals("distance")) {

        }
        if (sort.equals("lowprice")) {
            sortProducts = sortLowPriceOfProducts(keyword, integerMinPrice, integerMaxPrice);
            //System.out.println("가격순 리스트 갯수 : " + sortProducts.size());
        }
        if (sort.equals("score")) {
            sortProducts = products;

        }
        model.addAttribute("productList", sortProducts);
    }

    private List<ProductDto> sortLowPriceOfProducts(String keyword, int minPrice, int maxPrice) {
        List<ProductDto> products = selectLowPrice(keyword);
        List<ProductDto> sortedProducts = new ArrayList<>();
        assert products != null;
        products = compareLimitDate(products, selectDate1, selectDate2);
        for (ProductDto product : products) {
            System.out.println(product.getAccom_name() + "의 카테고리 : " + product.getAccom_category());
            if (!isEqualInputCategory(product)) {
                continue;
            }
            if (sortPrice(product, minPrice, maxPrice, sortedProducts)) {
                System.out.println("방 이름 : " + product.getAccom_name());
                int price = pService.getProductOfLowPrice(product.getAccom_num());
                product.setRoom_price(price);
                sortedProducts.add(product);
            }
        }
        return sortedProducts;
    }

    private boolean isEqualInputCategory(ProductDto product) {
        if (categoryList.isEmpty()) {
            return true;
        }
        for (String category : categoryList) {
            if (category.equals(product.getAccom_category())) {
                return true;
            }
        }
        return false;
    }

    private boolean sortPrice(ProductDto product, int minPrice, int maxPrice,
                              List<ProductDto> sortedProducts) {
        if (maxPrice == 300000) {
            return sortPriceOfMinPrice(product, minPrice, sortedProducts);
        }
        return sortPriceOfRangePrice(product, minPrice, maxPrice, sortedProducts);
    }

    private boolean sortPriceOfMinPrice(ProductDto product, int minPrice,
                                        List<ProductDto> sortedProducts) {
        if (!sortedProducts.isEmpty()) {
            if (isDuplicateAccom(product, sortedProducts)) {
                return false;
            }
            return product.getRoom_price() >= minPrice;
        }
        return product.getRoom_price() >= minPrice;

    }

    private boolean sortPriceOfRangePrice(ProductDto product, int minPrice, int maxPrice,
                                          List<ProductDto> sortedProducts) {
        if (!sortedProducts.isEmpty()) {
            if (isDuplicateAccom(product, sortedProducts)) {
                return false;
            }
            return product.getRoom_price() >= minPrice && product.getRoom_price() <= maxPrice;
        }
        return product.getRoom_price() >= minPrice && product.getRoom_price() <= maxPrice;
    }

    private boolean isDuplicateAccom(ProductDto product, List<ProductDto> sortedProducts) {
        //System.out.println("중복체크할 방이름 : " + product.getAccom_name());
        for (ProductDto dto : sortedProducts) {
            if (product.getAccom_num() == dto.getAccom_num()) {
                return true;
            }
        }
        return false;
    }

    private List<ProductDto> selectLimitMinPrice(int minPrice, List<ProductDto> products) {
        List<ProductDto> selectProductList = new ArrayList<>();
        for (ProductDto product : products) {
            if (compareEachRoomMinPrice(product, minPrice)) {
                selectProductList.add(product);
            }
        }
        return selectProductList;
    }

    private boolean compareEachRoomMinPrice(ProductDto productDto, int minPrice) {
        //System.out.println("hihi");
        //System.out.println(productDto.getAccom_num());
        List<Integer> priceList = pService.getPriceOfAccomNumber(productDto.getAccom_num());
        //System.out.println("list size : " + priceList.size());
        for (int price : priceList) {
            if (price >= minPrice) {
                return true;
            }
        }
        return false;
    }

    private List<ProductDto> selectLimitPrice(int minPrice, int maxPrice, List<ProductDto> products) {
        //System.out.println("최소 : " + minPrice + ", 최대 : " + maxPrice);
        List<ProductDto> selectProductList = new ArrayList<>();
        for (ProductDto product : products) {
            if (compareEachRoomPrice(product, minPrice, maxPrice)) {
                selectProductList.add(product);
            }
            //System.out.println("가격 : " + price);
        }
        return selectProductList;
    }

    private boolean compareEachRoomPrice(ProductDto productDto, int minPrice, int maxPrice) {
        List<Integer> priceList = pService.getPriceOfAccomNumber(productDto.getAccom_num());
        for (int price : priceList) {
            if (price >= minPrice && price <= maxPrice) {
                //System.out.println("가격 저장 " + product.getAccom_name());
                return true;
            }
        }
        return false;
    }

    private List<ProductDto> selectLowPrice(String keyword) {
        List<ProductDto> categoryList = pService.getProductOfCategoryByLowPrice(keyword);
        if (!categoryList.isEmpty()) {
            return categoryList;
        }
        List<ProductDto> hashtagList = pService.getProductOfHashTagByLowPrice(keyword);
        if (!hashtagList.isEmpty()) {
            return hashtagList;
        }
        List<ProductDto> locationList = pService.getProductOfLocationByLowPrice(keyword);
        if (!locationList.isEmpty()) {
            return locationList;
        }
        List<ProductDto> nameList = pService.getProductOfNameByLowPrice(keyword);
        if (!nameList.isEmpty()) {
            return nameList;
        }
        return null;
    }

    private List<ProductDto> compareLimitDate(List<ProductDto> products, String selDate1, String selDate2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
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
