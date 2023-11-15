package sist.last.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sist.last.dto.RoomDto;
import sist.last.mapper.RoomMapperInter;

@Controller
public class RoomController {

	@Autowired
	RoomMapperInter mapper;
	
	@GetMapping("/room/Room-List")
	public ModelAndView list() {
		
		ModelAndView model=new ModelAndView();
		
		int totalCount=mapper.getTotalCount();
		List<RoomDto> list=mapper.getAllData();
		
		model.addObject("totalCount", totalCount);
		model.addObject("list", list);
		
		model.setViewName("/room/roomList");
		
		return model;
	}
	
	@GetMapping("/room/Room-Insert")
	public String roominsertform()
	{
		return "/room/roomInsert";
	}
	
	@PostMapping("/room/Insert")
	public String insert(@ModelAttribute RoomDto dto, MultipartFile photo, HttpSession session) {
		// save 위치
		String path = session.getServletContext().getRealPath("/roomsave");

		
			// 업로드한 파일 dto 얻기
			String originalFilename = photo.getOriginalFilename();
			// 파일 이름에 날짜 및 시간 추가
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String timestamp = sdf.format(new Date());
			String newFilename = timestamp + "_" + originalFilename;
			dto.setRoom_photo(newFilename);

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

		return "redirect:/room/Room-Insert";
	}
}
