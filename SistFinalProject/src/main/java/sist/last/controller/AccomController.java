package sist.last.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import sist.last.dto.AccomDto;
import sist.last.mapper.AccomMapperInter;

@Controller
public class AccomController {

	@Autowired
	AccomMapperInter mapper;
	
	@GetMapping("/accom/Accom-Insert")
	public String accominsertfrom()
	{
		return "/accom/AccomInsert";
	}
	
	
	@PostMapping("/accom/Insert")
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
		
		mapper.insert_Accom(dto);

		return "redirect:/accom/Accom-Insert";
	}
}
