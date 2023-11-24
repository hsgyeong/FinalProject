package sist.last.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sist.last.dto.AccomDto;
import sist.last.dto.MemberDto;
import sist.last.dto.ReserveDto;
import sist.last.dto.RoomDto;
import sist.last.mapper.AccomMapperInter;
import sist.last.mapper.RoomMapperInter;
import sist.last.service.MemberService;
import sist.last.service.ReserveService;

@Controller
public class ReserveController {

	@Autowired
	ReserveService rservice;
	
	@Autowired
	MemberService mservice;
	
	@Autowired
	RoomMapperInter roominter;
	
	@Autowired
	AccomMapperInter accominter;
	
	@GetMapping("/reserve/reserve-form")
	public String form(Model model,@RequestParam int room_num,
			@ModelAttribute MemberDto memberDto,
			HttpSession session)
	{
		String info_id=(String)session.getAttribute("info_id");
		
		RoomDto rdto=roominter.getOneData(room_num);
		
		AccomDto adto=accominter.getOneData(rdto.getAccom_num());
		
		String info_hp=mservice.getDataById(info_id).getInfo_hp();
		
		model.addAttribute("info_hp", info_hp);
		model.addAttribute("adto", adto);
		model.addAttribute("rdto", rdto);
		model.addAttribute("info_id", info_id);
		
		return "/reservation/reserveForm";
	}
	
	@PostMapping("/reserve/insert")
	@ResponseBody
	public String payment(@RequestParam("reservationNumber") String reservationNumber,
						  @RequestParam("reserve_name") String reserve_name,
						  @RequestParam("reserve_hp") String reserve_hp,
						  @RequestParam("amount") int amount,
						  @RequestParam("room_num") int room_num,
						  @RequestParam("room_checkin") String room_checkin,
						  @RequestParam("room_checkout") String room_checkout,
	                      @ModelAttribute ReserveDto reserveDto,
	                      HttpSession session) {
		String info_id=(String)session.getAttribute("info_id");
		
	    reserveDto.setReserve_id(reservationNumber); // reservationNumber 설정
	    reserveDto.setReserve_amount(amount);
	    reserveDto.setReserve_name(reserve_name);
	    reserveDto.setReserve_hp(reserve_hp);
	    reserveDto.setInfo_id(info_id);
	    reserveDto.setRoom_num(room_num);
	    reserveDto.setReserve_checkin(room_checkin);
	    reserveDto.setReserve_checkout(room_checkout);

	    rservice.reservingInsert(reserveDto);

	    return "Success"; // 또는 원하는 응답을 반환할 수 있습니다.
	}
}
