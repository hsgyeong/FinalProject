package sist.last.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
import sist.last.dto.RoomDto;
import sist.last.mapper.AccomMapperInter;
import sist.last.mapper.RoomMapperInter;

@Controller
public class RoomController {

    @Autowired
    RoomMapperInter mapper;
    @Autowired
    AccomMapperInter amapper;

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
    public ModelAndView detail(@RequestParam int accom_num)
    {
    	ModelAndView model=new ModelAndView();
    
		List<RoomDto> list = mapper.getAllData(accom_num);
		AccomDto dto=amapper.getOneData(accom_num);
    	
		RoomDto rdto = list.get(0);
		String photoNames = rdto.getRoom_photo(); // 가정: 이미지 파일 이름들을 담고 있는 문자열

	    // 이미지 파일 이름들을 콤마로 스플릿하여 리스트로 변환
	    List<String> photoList = Arrays.asList(photoNames.split(","));
	    
		model.addObject("list", list);
    	model.addObject("dto", dto);
    	model.addObject("photoList", photoList);
    	

    	model.setViewName("/room/roomDetail");
    	
    	return model;
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