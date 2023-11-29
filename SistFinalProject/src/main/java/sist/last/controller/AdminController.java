package sist.last.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sist.last.service.AdminMapperService;

@Controller
public class AdminController {

	@Autowired
	AdminMapperService adminService;
	
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
	public String adminLogin(@RequestParam String admin_id,
			@RequestParam String admin_pass,
			HttpSession session)
	{
		int adminCheck=adminService.AdminloginPassCheck(admin_id, admin_pass);
		
		if(adminCheck==1)
		{
			session.setMaxInactiveInterval(60*60*8);
			
			session.setAttribute("admin_id", admin_id);
			session.setAttribute("loginok", "admin");

			return "redirect:/";
		}
		else
		{
			return "/login/loginFail";
		}
	}
}
