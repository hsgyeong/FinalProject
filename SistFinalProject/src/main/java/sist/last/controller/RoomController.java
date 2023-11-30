package sist.last.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sist.last.dto.AccomDto;
import sist.last.dto.MemberDto;
import sist.last.dto.ReserveDto;
import sist.last.dto.ReviewDto;
import sist.last.dto.RoomDto;
import sist.last.mapper.AccomMapperInter;
import sist.last.mapper.MemberMapperInter;
import sist.last.mapper.ReviewMapperInter;
import sist.last.mapper.RoomMapperInter;
import sist.last.service.ReserveService;

@Controller
public class RoomController {

    @Autowired
    RoomMapperInter mapper;
    @Autowired
    AccomMapperInter amapper;
    @Autowired
    MemberMapperInter mmapper;
    @Autowired
    ReserveService rmapper;
    @Autowired
    ReviewMapperInter reviewmapper;

    @GetMapping("/room/room-list")
    public ModelAndView list(@RequestParam int accom_num,HttpSession session) {

        ModelAndView model = new ModelAndView();

        int totalCount = mapper.getTotalCount(accom_num);
        List<RoomDto> list = mapper.getAllData(accom_num);
        
        String business = (String) session.getAttribute("business_id");
        String info=(String)session.getAttribute("info_id");
        
        AccomDto dto = new AccomDto();
        dto.setBusiness_id(business);
        
        MemberDto mdto = new MemberDto();
        mdto.setInfo_id(info);
        
        model.addObject("accom_num", accom_num);
        model.addObject("totalCount", totalCount);
        model.addObject("list", list);
        model.addObject("dto", dto);
        model.addObject("mdto", mdto);

        model.setViewName("/room/roomList");

        return model;
    }
    
    @GetMapping("/room/room-detail")
    public ModelAndView detail(@RequestParam int accom_num,@RequestParam int sleep,
    		@RequestParam String checkin,@RequestParam String checkout, HttpSession session,
    		@RequestParam(value = "currentPage",defaultValue = "1") int currentPage)
    {
    	ModelAndView model=new ModelAndView();
    
		List<RoomDto> list = mapper.getAllData(accom_num);
		AccomDto dto=amapper.getOneData(accom_num);
    	
		String accom_name=dto.getAccom_name();
		model.addObject("accom_name", accom_name);
		
		RoomDto rdto = list.get(0);
		String photoNames = rdto.getRoom_photo(); // 가정: 이미지 파일 이름들을 담고 있는 문자열

	    // 이미지 파일 이름들을 콤마로 스플릿하여 리스트로 변환
	    List<String> photoList = Arrays.asList(photoNames.split(","));
	    
	    String info=(String)session.getAttribute("info_id");
	    
	    MemberDto mdto=mmapper.getDataById(info);
	    
		model.addObject("list", list);
    	model.addObject("dto", dto);
    	model.addObject("photoList", photoList);
    	model.addObject("sleep", sleep);
    	model.addObject("checkin", checkin);
    	model.addObject("checkout", checkout);
    	model.addObject("mdto", mdto);
    	
    	//reserve 하나의 데이터 조회
    	ReserveDto reservedto=rmapper.getOneInfoData(info);
    	String reserve_id=reservedto.getInfo_id();
    	
    	
    	
    	double score=dto.getAccom_score();
    	model.addObject("score", score);
    	
    	int count=reviewmapper.ReviewTotalCount(accom_num);
    	model.addObject("count", count);
    	
    	double avgscore=amapper.getAverageScore(accom_num);
    	model.addObject("avgscore", avgscore);
    	
    	List<ReviewDto> reviewdto=reviewmapper.ReviewList(accom_num);
    	model.addObject("reviewdto", reviewdto);
    	
    	// 페이징
    	int totalCount = reviewmapper.ReviewTotalCount(accom_num);
    	int totalPage; // 총 페이지수
    	int startPage; // 각 블럭에서 보여질 시작페이지
    	int endPage; // 각 블럭에서 보여질 끝페이지
    	int startNum; // db에서 가져올 글의 시작번호(mysql은 첫글이 0, 오라클은 1)
    	int perPage = 3; // 한 페이지당 보여질 글의 갯수
    	int perBlock = 5; // 한 블럭당 보여질 페이지 개수

    	// 총페이지수 구하기
    	// 총글의 갯수/한페이지당 보여질 개수로 나눔(7/5=1)
    	// 나머지가 1이라도 있으면 무조건 1페이지 추가(1+1=2페이지가 필요)
    	totalPage = totalCount / perPage + (totalCount % perPage == 0 ? 0 : 1);

    	// 각 블럭당 보여야할 시작페이지
    	// perBlock=5일 경우는 현재페이지 1~5 시작:1 끝:5
    	// 현재페이지 13 시작:11 끝:15
    	startPage = (currentPage - 1) / perBlock * perBlock + 1;

    	endPage = startPage + perBlock - 1;

    	// 총페이지가 23일 경우 마지막 블럭은 25가 아니라 23이다
    	if (endPage > totalPage)
    	    endPage = totalPage;

    	// 각페이지에서 보여질 시작번호
    	// 1페이지: 0, 2페이지:5 3페이지:10....
    	startNum = (currentPage - 1) * perPage;

    	// 각 페이지에서 필요한 게시글 가져오기
    	HashMap<String, Integer> pagingMap = new HashMap<>();
    	pagingMap.put("accom_num", accom_num);
    	pagingMap.put("start", (currentPage - 1) * perPage);
    	pagingMap.put("perpage", perPage);
    	List<ReviewDto> pplist = reviewmapper.getList(pagingMap);

    	// 각 페이지에 출력할 시작번호
    	int no = totalCount - (currentPage - 1) * perPage;

    	// 수정된 부분: 페이징 URL 생성
    	String pagingUrl = "/room/room-detail?accom_num=" + accom_num +
    	        "&checkin=" + checkin +
    	        "&checkout=" + checkout +
    	        "&sleep=" + sleep +
    	        "&currentPage=";

    	model.addObject("totalCount", totalCount);
    	model.addObject("pplist", pplist);
    	model.addObject("startPage", startPage);
    	model.addObject("endPage", endPage);
    	model.addObject("totalPage", totalPage);
    	model.addObject("no", no);
    	model.addObject("currentPage", currentPage);

    	// 수정된 부분: 페이징 URL 추가
    	model.addObject("pagingUrl", pagingUrl);
		
    	model.setViewName("/room/roomDetail");
    	
    	return model;
    }
    
    @PostMapping("/room/review-insert")
    public String review(@ModelAttribute ReviewDto dto,@ModelAttribute AccomDto adto,
    		@RequestParam String checkin, @RequestParam String checkout, @RequestParam int sleep,HttpSession session)
    {
    	
    	reviewmapper.insertReview(dto);	
    	
    	return "redirect:/room/room-detail?accom_num=" + dto.getAccom_num() +
                "&checkin="+checkin+
                "&checkout="+checkout+
                "&sleep=" + sleep;
    }

    @GetMapping("/room/room-insert")
    public String roominsertform(@RequestParam String accom_num,Model model) {
    	
    	model.addAttribute("accom_num", accom_num);
    	
        return "/room/roomInsert";
    }

    @PostMapping("/room/insert")
    public String insert(@ModelAttribute RoomDto dto, List<MultipartFile> photos, HttpSession session,
                         @RequestParam String accom_num) {
        // save 위치
        String path = session.getServletContext().getRealPath("/roomsave");

        String uphoto="";
        
        if(photos.get(0).getOriginalFilename().equals("")) {
        	uphoto="no";
        }
        
        else {
        	for (MultipartFile photo : photos) {
                String originalFilename = photo.getOriginalFilename();
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String newFilename = timestamp + "_" + originalFilename;
                uphoto+=newFilename+",";
             
                try {
                	
					photo.transferTo(new File(path+"/"+newFilename));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	uphoto=uphoto.substring(0,uphoto.length()-1);
        	dto.setRoom_photo(uphoto);
        	mapper.insertRoom(dto);
        }
        
        return "redirect:/room/room-list?accom_num="+dto.getAccom_num();
    }

    @GetMapping("/room/delete")
    public String delete(@RequestParam int num, HttpSession session) {

        RoomDto dto = mapper.getOneData(num);
        
        // AccomDto가 null이 아닌지 확인
        if (dto != null) {
            // 기존 이미지 파일 삭제
            String oldFilename = dto.getRoom_photo();
            if (oldFilename != null && !oldFilename.isEmpty()) {
                String path = session.getServletContext().getRealPath("/roomsave");
                File oldFile = new File(path, oldFilename);

                // 파일이 존재하고 삭제에 성공한 경우에만 삭제
                if (oldFile.exists() && oldFile.delete()) {
                    System.out.println("기존 이미지 파일 삭제 성공");
                } else {
                    System.out.println("기존 이미지 파일 삭제 실패");
                }
            }
        }

        mapper.deleteRoom(num);
        
        return "redirect:/room/room-list?accom_num="+dto.getAccom_num();
    }

    @GetMapping("/room/room-update")
    public ModelAndView accomupdatefrom(@RequestParam int room_num) {
    	
    	ModelAndView model=new ModelAndView();
    	
        RoomDto dto = mapper.getOneData(room_num);

        model.addObject("dto", dto);
        
        model.setViewName("/room/roomUpdate");

        return model;
    }
    
    

    @PostMapping("/room/update")
    public String update(@ModelAttribute RoomDto dto, List<MultipartFile> photos, HttpSession session) {

    	// save 위치
        String path = session.getServletContext().getRealPath("/roomsave");

        String uphoto="";
        
        if(photos.get(0).getOriginalFilename().equals("")) {
        	uphoto="no";
        }
        
        else {
        	for (MultipartFile photo : photos) {
                String originalFilename = photo.getOriginalFilename();
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                String newFilename = timestamp + "_" + originalFilename;
                uphoto+=newFilename+",";
             
                try {
                	
					photo.transferTo(new File(path+"/"+newFilename));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	uphoto=uphoto.substring(0,uphoto.length()-1);
        	dto.setRoom_photo(uphoto);
        	mapper.updateRoom(dto);
        }

        return "redirect:/room/room-list?accom_num="+dto.getAccom_num();
    }


}