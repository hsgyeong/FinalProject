package sist.last.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

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
import sist.last.service.BusinessService;
import sist.last.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	MemberService service;
	
	@Autowired
	BusinessService Bservice;
	
	
	@GetMapping("/login/loginmain")
	public String loginform(HttpSession session,
			Model model)
	{
		
		String myid = (String)session.getAttribute("myid");
		String loginok = (String)session.getAttribute("loginok");
		
		if(loginok==null)
			return "/login/loginForm";	
		else
		{
			String nickname = service.getNickname(myid);
			model.addAttribute("myid", myid);
			model.addAttribute("nickname", nickname);
			
			
			return "/";
		}
	}
	
	
	@GetMapping("/login/fail")
	public String loginfail()
	{
		return "/member/loginFail";
		
	}
	
	@PostMapping("/login/member-login")
	public String login(@RequestParam String id,
			@RequestParam String pass,
			@RequestParam(required = false) String cbsave,
			HttpSession session)
	{
		HashMap<String, String> map = new HashMap<>();
		int check = service.loginPassCheck(id, pass);
		
		if(check==1) {
			
			session.setMaxInactiveInterval(60*60*8);
			
			session.setAttribute("myid", id);
			session.setAttribute("loginok", "yes");
			session.setAttribute("saveok", cbsave);
			
			MemberDto memberDto = service.getDataById(id);
	/*		
			String myid = (String) session.getAttribute("myid");

			if (myid != null) {
			    System.out.println("세션에 myid가 저장되어 있습니다. 값: " + myid);
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
			@RequestParam(required = false) String cbsave,
			HttpSession session)
	{
		HashMap<String, String> map = new HashMap<>();
		
		int Bcheck = Bservice.BloginPassCheck(business_id, business_pass);
		
		if(Bcheck==1) {
			
			session.setMaxInactiveInterval(60*60*8);
			
			session.setAttribute("myid", business_id);
			session.setAttribute("loginok", "yes");
			session.setAttribute("saveok", cbsave);
			
			BusinessDto businessDto = Bservice.getDataByBusinessId(business_id);
			
			
		/*	 String myid = (String) session.getAttribute("myid");
			  
			 if (myid != null) { System.out.println("세션에 myid가 저장되어 있습니다. 값: " + myid); }
			 else { 
			 		System.out.println("세션에 myid가 저장되어 있지 않습니다."); 
			 	}  */
			 		
			return "redirect:/";
		}else {
			
			return "/login/loginFail";
		}
		
	} 
	

	
	@GetMapping("/login/logout")
	public String logout(HttpSession session)
	{
		session.removeAttribute("loginok");
		session.removeAttribute("myid");
		
		return "redirect:/";
	}
	
	
}
