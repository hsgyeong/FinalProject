package sist.last.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccomController {

	@GetMapping("/accom/accominsert")
	public String accominsertfrom()
	{
		return "/accom/accominsert";
	}
}
