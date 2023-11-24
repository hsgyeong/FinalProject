package sist.last.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sist.last.service.KakaoMemberServiceInter;
import sist.last.service.MemberServiceInter;

@Controller
public class KaKaoMemberController {
	
	@Autowired
	public KakaoMemberServiceInter kakaoMemberServiceInter;
	
	@Autowired
	public MemberServiceInter memberServiceInter;
	

 @RequestMapping(value="/login/kakao-member", method =  RequestMethod.GET)
 public ModelAndView kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Throwable {
	 
	
	 System.out.println("code: "+code);
	 
	 String access_token = kakaoMemberServiceInter.getAccessToken(code);
	 System.out.println("accessToken: "+access_token);
	 
	 HashMap<String, Object> memberInfo = kakaoMemberServiceInter.getMemberInfo(access_token);
	 System.out.println("memberInfo: "+memberInfo);
	 
	 String nickname = (String)memberInfo.get("nickname");
	 
	 if("".equals(nickname)) {
		 
		 return new ModelAndView("redirect:/");
	 }else{
		 
		 return new ModelAndView("redirect:/login/login-fail");
	 }

 }
	
} 
