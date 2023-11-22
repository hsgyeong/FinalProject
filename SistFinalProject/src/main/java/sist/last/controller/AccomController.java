package sist.last.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sist.last.dto.AccomDto;
import sist.last.dto.MemberDto;
import sist.last.mapper.AccomMapperInter;
import sist.last.mapper.MemberMapperInter;
import sist.last.service.MemberService;

@Controller
public class AccomController {

<<<<<<< HEAD
    @Autowired
    AccomMapperInter mapper;

    @GetMapping("/accom/accom-list")
    public ModelAndView list(@RequestParam String business_id, HttpSession session) {
=======
	@Autowired
	AccomMapperInter mapper;
	@Autowired
	MemberService service;

	@GetMapping("/accom/accom-list")
	public ModelAndView list(HttpSession session) {
>>>>>>> 208204685d848012a28df565ce5ae64cf1206a21

        ModelAndView model = new ModelAndView();

<<<<<<< HEAD
        int totalCount = mapper.getTotalCount(business_id);
        List<AccomDto> list = mapper.getAllData(business_id);

        // 세션에서 로그인 정보를 가져오는 로직
        String business = (String) session.getAttribute("myid");
=======
		String business = (String) session.getAttribute("business_id");
		String info=(String)session.getAttribute("info_id");
		
		int totalCount = mapper.getTotalCount();
		List<AccomDto> list = mapper.getAllData();
	    
	    AccomDto dto = new AccomDto();
	    dto.setBusiness_id(business);
	    
	    MemberDto mdto = new MemberDto();
        mdto.setInfo_id(info);
	    
		model.addObject("totalCount", totalCount);
		model.addObject("list", list);
		model.addObject("dto", dto);
		model.addObject("mdto", mdto);
		
		model.setViewName("/accom/accomList");
		
		return model;
	}

	@GetMapping("/accom/accom-insert")
	public ModelAndView accominsertfrom(HttpSession session) {
		
		ModelAndView model=new ModelAndView();
		
		// 세션에서 로그인 정보를 가져오는 로직
	    String business_id = (String) session.getAttribute("business_id");
	    
	    AccomDto dto = new AccomDto();
	    
	    dto.setBusiness_id(business_id);
	    
	    model.addObject("dto", dto);
	    
	    model.setViewName("/accom/accomInsert");
		
		return model;
	}
>>>>>>> 208204685d848012a28df565ce5ae64cf1206a21

        AccomDto dto = new AccomDto();

        dto.setBusiness_id(business);

        model.addObject("totalCount", totalCount);
        model.addObject("list", list);
        model.addObject("dto", dto);

        model.setViewName("/accom/accomList");

        return model;
    }

    @GetMapping("/accom/accom-insert")
    public ModelAndView accominsertfrom(HttpSession session) {

        ModelAndView model = new ModelAndView();

<<<<<<< HEAD
        // 세션에서 로그인 정보를 가져오는 로직
        String business_id = (String) session.getAttribute("myid");
=======
	}
	
	@GetMapping("/accom/accom-update")
	public ModelAndView accomupdatefrom(HttpSession session,@RequestParam int num) {
		
		ModelAndView model=new ModelAndView();
		
		// 세션에서 로그인 정보를 가져오는 로직
	    String business_id = (String) session.getAttribute("business_id");
	    
	    AccomDto dto = mapper.getOneData(num);
	    
	    dto.setBusiness_id(business_id);
	    
	    model.addObject("dto", dto);
	    
	    model.setViewName("/accom/accomUpdate");
		
		return model;
	}
>>>>>>> 208204685d848012a28df565ce5ae64cf1206a21

        AccomDto dto = new AccomDto();

        dto.setBusiness_id(business_id);

        model.addObject("dto", dto);

        model.setViewName("/accom/accomInsert");

        return model;
    }

    @PostMapping("/accom/insert")
    public String insert(@ModelAttribute AccomDto dto, MultipartFile photo, HttpSession session) {
        // save 위치
        String path = session.getServletContext().getRealPath("/accomsave");

        // 업로드한 파일 dto 얻기
        String originalFilename = photo.getOriginalFilename();
        // 파일 이름에 날짜 및 시간 추가
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        String newFilename = timestamp + "_" + originalFilename;
        dto.setAccom_photo(newFilename);

        try {
            photo.transferTo(new File(path + "/" + newFilename));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mapper.insertAccom(dto);

        return "redirect:/accom/accom-list?business_id=" + dto.getBusiness_id();
    }

    @GetMapping("/accom/delete")
    public String delete(@RequestParam int num, HttpSession session) {

        AccomDto dto = mapper.getOneData(num);

        //AccomDto가 null이 아닌지 확인
        if (dto != null) {
            // 기존 이미지 파일 삭제
            String oldFilename = dto.getAccom_photo();
            if (oldFilename != null && !oldFilename.isEmpty()) {
                String path = session.getServletContext().getRealPath("/accomsave");
                File oldFile = new File(path, oldFilename);

                //파일이 존재하고 삭제에 성공한 경우에만 삭제
                if (oldFile.exists() && oldFile.delete()) {
                    System.out.println("기존 이미지 파일 삭제 성공");
                } else {
                    System.out.println("기존 이미지 파일 삭제 실패");
                }
            }
        }

        mapper.deleteAccom(num);

        return "redirect:/accom/accom-list";

    }

    @GetMapping("/accom/accom-update")
    public ModelAndView accomupdatefrom(HttpSession session, @RequestParam int num) {

        ModelAndView model = new ModelAndView();

        // 세션에서 로그인 정보를 가져오는 로직
        String business_id = (String) session.getAttribute("myid");

        AccomDto dto = mapper.getOneData(num);

        dto.setBusiness_id(business_id);

        model.addObject("dto", dto);

        model.setViewName("/accom/accomUpdate");

        return model;
    }

    @PostMapping("/accom/update")
    public String update(@ModelAttribute AccomDto dto, MultipartFile photo, HttpSession session) {

        // save 위치
        String path = session.getServletContext().getRealPath("/accomsave");
        // 업로드한 파일 dto 얻기
        String originalFilename = photo.getOriginalFilename();
        // 파일 이름에 날짜 및 시간 추가
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String timestamp = sdf.format(new Date());
        String newFilename = timestamp + "_" + originalFilename;
        // 파일 삭제
        String oldFilename = mapper.getOneData(dto.getAccom_num()).getAccom_photo();
        try {
            // 기존 이미지 파일 삭제
            File oldFile = new File(path + "/" + oldFilename);
            oldFile.delete();

            // 신규 이미지 파일 저장
            photo.transferTo(new File(path + "/" + newFilename));

            // 새 파일 이름 설정
            dto.setAccom_photo(newFilename);

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        mapper.updateAccom(dto);

        return "redirect:/accom/accom-list?business_id=" + dto.getBusiness_id();
    }

    /*이성신 추가 부분*/
    @GetMapping("/accom/non-book")
    public String nonBook() {
        return "/accom/accomNonBookable";
    }

    @PostMapping("/room/duplicate-date")
    @ResponseBody
    public void duplicateDate(@RequestParam("checkin") List<String> checkin,
                              @RequestParam("checkout") List<String> checkout,
                              @RequestParam("idx") List<Integer> idx) {

        for (int i = 0; i < checkin.size(); i++) {
            LocalDate checkinDate = LocalDate.parse(checkin.get(i));
            LocalDate checkoutDate = LocalDate.parse(checkout.get(i));

            // 중복 검사
            if (isDateRangeOverlap(checkinDate, checkoutDate, checkin, checkout, i)) {
                // 중복된 날짜가 있을 경우 필요한 로직을 수행하고 응답을 반환
                // 예를 들면, 오류 응답이나 처리 중단 등을 수행할 수 있습니다.
            }
        }

        // 중복이 없는 경우 필요한 로직을 수행하고 응답을 반환
        // ...
    }

    private boolean isDateRangeOverlap(LocalDate newCheckin, LocalDate newCheckout, List<String> checkinList,
                                       List<String> checkoutList, int currentIndex) {
        for (int i = 0; i < checkinList.size(); i++) {
            if (i != currentIndex) {
                LocalDate existingCheckin = LocalDate.parse(checkinList.get(i));
                LocalDate existingCheckout = LocalDate.parse(checkoutList.get(i));

                // 새로운 체크인 날짜가 기존 기간에 포함되거나
                // 새로운 체크아웃 날짜가 기존 기간에 포함되는 경우 중복으로 처리
                if ((newCheckin.isEqual(existingCheckin) || newCheckin.isAfter(existingCheckin)) &&
                        (newCheckin.isBefore(existingCheckout) || newCheckin.isEqual(existingCheckout)) ||
                        (newCheckout.isEqual(existingCheckin) || newCheckout.isAfter(existingCheckin)) &&
                                (newCheckout.isBefore(existingCheckout) || newCheckout.isEqual(existingCheckout))) {
                    return true; // 중복이 발생했음
                }
            }
        }
        return false; // 중복이 없음
    }
}
