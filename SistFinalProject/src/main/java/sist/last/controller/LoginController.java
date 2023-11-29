package sist.last.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sist.last.dto.BusinessDto;
import sist.last.dto.MemberDto;
import sist.last.service.AdminMapperService;
import sist.last.service.BusinessService;
import sist.last.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	MemberService service;
	
	@Autowired
	BusinessService businessService;
	
	@Autowired
	AdminMapperService adminService;
	
	
	@GetMapping("/login/loginmain")
	public String loginform(HttpSession session,
			Model model)
	{
		
		//String myid = (String)session.getAttribute("myid");
		String loginok = (String)session.getAttribute("loginok");
		
		
		if(loginok==null)
			return "/login/loginForm";	
		else
		{
			String info_id = (String) session.getAttribute("info_id");
			String business_id = (String) session.getAttribute("business_id");
			//String info_nickname = service.getNickname(info_id);
			//model.addAttribute("myid", myid);
			//model.addAttribute("info_nickname", info_nickname);
			if(info_id != null) {
				String info_nickname = service.getNickname(info_id);
				
				model.addAttribute("info_id", info_id);
				model.addAttribute("info_nickname", info_nickname);
				
				return "/";
			}
			
			else if (business_id != null) {
				
				model.addAttribute("business_id", business_id);
				
				return "/";
				
			}
			
			else {
				
				return "/login/loginForm";
			}
			
		}
	}
	
	
	@GetMapping("/login/fail")
	public String loginfail()
	{
		return "/member/loginFail";
		
	}
	
	@PostMapping("/login/member-login")
	public String login(@RequestParam String info_id,
			@RequestParam String info_pass,
			@RequestParam(required = false) String cbsave,
			HttpSession session,
			HttpServletResponse response)
	{
		HashMap<String, String> map = new HashMap<>();
		int check = service.loginPassCheck(info_id, info_pass);
		
		if(check==1) {
			
			session.setMaxInactiveInterval(60*60*8);
			
			Cookie cookie = new Cookie("infoId", info_id);
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
			
			session.setAttribute("info_id", info_id);
			session.setAttribute("loginok", "member");
			session.setAttribute("saveok", cbsave);
			 
			MemberDto memberDto = service.getDataById(info_id);  //session으로 못넘김 model로 넘겨야함
		
			//String info_id = (String) session.getAttribute("info_id");

		/*	if (myid != null) {
			    System.out.println("세션에 myid가 저장되어 있습니다. 값: " + myid+memberDto);
			} else {
			    System.out.println("세션에 myid가 저장되어 있지 않습니다.");
			}
		*/	
			return "redirect:/";
		}else {
			
			return "/login/loginFail";
		}
		
	} 
	
	@PostMapping("/login/business-login")
	public String Blogin(@RequestParam String business_id,
			@RequestParam String business_pass,
			@RequestParam String admin_id,
			@RequestParam String admin_pass,
			@RequestParam(required = false) String cbsave,
			HttpSession session,
			HttpServletResponse response)
	{
		HashMap<String, String> map = new HashMap<>();
		
		int Bcheck = businessService.BloginPassCheck(business_id, business_pass);
		
		if(Bcheck==1) {
			
			session.setMaxInactiveInterval(60*60*8);
			
			Cookie cookie = new Cookie("businessId", business_id);
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
			
			session.setAttribute("business_id", business_id);
			session.setAttribute("loginok", "business");
			session.setAttribute("saveok", cbsave);
			
			BusinessDto businessDto = businessService.getDataByBusinessId(business_id);
			
			
		/*	 String myid = (String) session.getAttribute("myid");
			  
			 if (myid != null) { System.out.println("세션에 myid가 저장되어 있습니다. 값: " + myid); }
			 else { 
			 		System.out.println("세션에 myid가 저장되어 있지 않습니다."); 
			 	}  */
			 		
			return "redirect:/";
		}
		else {
			
			return "/login/loginFail";
		}
		
	} 
	
	@GetMapping("/login/login-fail")
	public String loginFail() {
		
		return "/login/loginFail";
	}
	
	@GetMapping("/login/logout")
	public String logout(HttpSession session, HttpServletResponse response)
	{
		String loginok = (String) session.getAttribute("loginok");
		
		if(loginok != null) {
			
			if(session.getAttribute("info_id") != null) {
				session.removeAttribute("info_id");
			}
			else if (session.getAttribute("business_id") != null) {
				session.removeAttribute("business_id");
			}
			
		}
		
		session.removeAttribute("loginok");
		session.removeAttribute("userId");
		session.removeAttribute("kakao_nickname");
		//session.removeAttribute("myid");
		
		return "redirect:/";
	}

}
