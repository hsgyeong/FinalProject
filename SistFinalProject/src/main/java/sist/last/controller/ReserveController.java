package sist.last.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping("/reserve/insert")
	@ResponseBody
	public String payment(@RequestParam("reservationNumber") String reservationNumber, 
	                      @ModelAttribute ReserveDto reserveDto,
	                      @ModelAttribute RoomDto roomDto, HttpSession session) {
	    reserveDto.setReserve_id(reservationNumber); // reservationNumber 설정

	    rservice.reservingInsert(reserveDto);

	    return "Success"; // 또는 원하는 응답을 반환할 수 있습니다.
	}
}
