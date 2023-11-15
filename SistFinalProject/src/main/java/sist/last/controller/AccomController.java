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

import sist.last.dto.AccomDto;
import sist.last.mapper.AccomMapperInter;

@Controller
public class AccomController {

	@Autowired
	AccomMapperInter mapper;
	
	@GetMapping("/accom/Accom-List")
	public ModelAndView list() {
		
		ModelAndView model=new ModelAndView();
		
		int totalCount=mapper.getTotalCount();
		List<AccomDto> list=mapper.getAllData();
		
		model.addObject("totalCount", totalCount);
		model.addObject("list", list);
		
		model.setViewName("/accom/accomList");
		
		return model;
	}
	
	@GetMapping("/accom/Accom-Insert")
	public String accominsertfrom()
	{
		return "/accom/accomInsert";
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
		
		mapper.insertAccom(dto);

		return "redirect:/accom/Accom-Insert";
	}
}
