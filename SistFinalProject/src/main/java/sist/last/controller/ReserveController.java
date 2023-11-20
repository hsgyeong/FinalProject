package sist.last.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import sist.last.dto.ReserveDto;
import sist.last.dto.RoomDto;
import sist.last.service.ReserveService;

@Controller
public class ReserveController {

	@Autowired
	ReserveService rservice;
	
	@GetMapping("/reserve/reserve-form")
	public String form()
	{
		return "/reservation/reserveForm";
	}
	
	@PostMapping("/reserve/payment")
	public ModelAndView payment(@ModelAttribute ReserveDto reserveDto,@ModelAttribute RoomDto roomDto,HttpSession session)
	{
		ModelAndView model=new ModelAndView();
		
		String myid=(String)session.getAttribute("myid");
		
		int room_num=roomDto.getRoom_num();
		
		reserveDto.setInfo_id(myid);
		
		reserveDto.setRoom_num(room_num);
		
		rservice.reservingInsert(reserveDto);
		
		model.setViewName("/payment/payment");
		
		return model;
	}
}
