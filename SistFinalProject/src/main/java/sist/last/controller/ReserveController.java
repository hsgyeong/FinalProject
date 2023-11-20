package sist.last.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import sist.last.dto.ReserveDto;
import sist.last.service.MemberService;
import sist.last.service.ReserveService;

@Controller
public class ReserveController {

	@Autowired
	ReserveService rservice;
	
	@Autowired
	MemberService mservice;
	
	@GetMapping("/reserve/reserve-form")
	public String form()
	{
		return "/reservation/reserveForm";
	}
	
	@PostMapping("/reserve/payment")
	public ModelAndView payment(@ModelAttribute ReserveDto dto,HttpSession session)
	{
		ModelAndView model=new ModelAndView();
		
		String myid=(String)session.getAttribute("myid");
		
		
		dto.setInfo_id(myid);
		
		rservice.reservingInsert(dto);
		
		model.setViewName("/payment/payment");
		
		return model;
	}
}
