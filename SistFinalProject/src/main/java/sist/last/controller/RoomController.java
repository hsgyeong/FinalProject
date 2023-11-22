package sist.last.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import sist.last.dto.RoomDto;
import sist.last.mapper.RoomMapperInter;

@Controller
public class RoomController {

    @Autowired
    RoomMapperInter mapper;

    @GetMapping("/room/room-list")
    public ModelAndView list(@RequestParam String accom_num,HttpSession session) {

        ModelAndView model = new ModelAndView();

        int totalCount = mapper.getTotalCount(accom_num);
        List<RoomDto> list = mapper.getAllData(accom_num);
        
        String business = (String) session.getAttribute("myid");
        
        AccomDto dto = new AccomDto();
        dto.setBusiness_id(business);
        
        model.addObject("accom_num", accom_num);
        model.addObject("totalCount", totalCount);
        model.addObject("list", list);
        model.addObject("dto", dto);

        model.setViewName("/room/roomList");

        return model;
    }
    
    @GetMapping("/room/room-detail")
    public ModelAndView detail(int room_num)
    {
    	ModelAndView model=new ModelAndView();
    	
    	RoomDto dto=mapper.getOneData(room_num);
    	
    	model.addObject("dto", dto);
    	
    	model.setViewName("/room/roomDetail");
    	
    	return model;
    }

    @GetMapping("/room/room-insert")
    public String roominsertform(@RequestParam String accom_num,Model model) {
    	
    	model.addAttribute("accom_num", accom_num);
    	
        return "/room/roomInsert";
    }

    @PostMapping("/room/insert")
    public String insert(@ModelAttribute RoomDto dto, MultipartFile photo, HttpSession session,
                         @RequestParam String accom_num) {
        // save 위치
        String path = session.getServletContext().getRealPath("/roomsave");

        // 업로드한 파일 dto 얻기
        String originalFilename = photo.getOriginalFilename();
        // 파일 이름에 날짜 및 시간 추가
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        String newFilename = timestamp + "_" + originalFilename;
        dto.setRoom_photo(newFilename);
        
        dto.setAccom_num(Integer.parseInt(accom_num));
        
        try {
            photo.transferTo(new File(path + "/" + newFilename));
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        mapper.insertRoom(dto);
        
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
    public String update(@ModelAttribute RoomDto dto, MultipartFile photo, HttpSession session) {

        // save 위치
        String path = session.getServletContext().getRealPath("/roomsave");
        // 업로드한 파일 dto 얻기
        String originalFilename = photo.getOriginalFilename();
        // 파일 이름에 날짜 및 시간 추가
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String timestamp = sdf.format(new Date());
        String newFilename = timestamp + "_" + originalFilename;
        // 파일 삭제
        String oldFilename = mapper.getOneData(dto.getRoom_num()).getRoom_photo();
        
        try {
            // 기존 이미지 파일 삭제
            File oldFile = new File(path + "/" + oldFilename);
            oldFile.delete();

            // 신규 이미지 파일 저장
            photo.transferTo(new File(path + "/" + newFilename));

            // 새 파일 이름 설정
            dto.setRoom_photo(newFilename);

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        mapper.updateRoom(dto);

        return "redirect:/room/room-list?accom_num="+dto.getAccom_num();
    }

}