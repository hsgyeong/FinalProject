package sist.last.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

	@GetMapping("/admin/admin-page")
	public String adminStart()
	{
		return "/2/layout/adminMain";
	}
	
	@GetMapping("/admin/admin-loginform")
	public String adminLoginForm()
	{
		return "/admin/adminLoginForm";
	}
	
	@PostMapping("/admin/admin-login")
	
}
