package sist.last.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReserveController {

	@GetMapping("/reserve/form")
	public String form()
	{
		return "/reservation/reserveForm";
	}
}
