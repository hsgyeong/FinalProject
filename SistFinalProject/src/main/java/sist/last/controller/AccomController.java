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
import sist.last.mapper.AccomMapperInter;

@Controller
public class AccomController {

	@Autowired
	AccomMapperInter mapper;

	@GetMapping("/accom/Accom-List")
	public ModelAndView list() {

		ModelAndView model = new ModelAndView();

		int totalCount = mapper.getTotalCount();
		List<AccomDto> list = mapper.getAllData();

		model.addObject("totalCount", totalCount);
		model.addObject("list", list);

		model.setViewName("/accom/accomList");

		return model;
	}

	@GetMapping("/accom/Accom-Insert")
	public String accominsertfrom() {
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

		return "redirect:/accom/Accom-List";
	}

	@GetMapping("/accom/Delete")
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
			
		return "redirect:/accom/Accom-List";

	}

	@GetMapping("/accom/Accom-Update")
	public String accomupdatefrom(@RequestParam int num, Model model) {
		AccomDto dto = mapper.getOneData(num);

		model.addAttribute("dto", dto);

		return "/accom/accomUpdate";
	}

	@PostMapping("/accom/Update")
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

	    return "redirect:/accom/Accom-List";
	}
}
