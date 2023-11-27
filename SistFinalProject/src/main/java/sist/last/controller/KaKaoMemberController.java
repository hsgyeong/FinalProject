package sist.last.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sist.last.service.KakaoMemberServiceInter;
import sist.last.service.MemberServiceInter;

@Controller //Controller 어노테이션을 통해 이 클래스는 스프링 mvc의 컨트롤러로 선언
public class KaKaoMemberController {
	
	//kakaoMemberServiceInter와 memberServiceInter을 @Autowired 어노테이션을 통해 주입
	@Autowired
	public KakaoMemberServiceInter kakaoMemberServiceInter;
	
	@Autowired
	public MemberServiceInter memberServiceInter;
	
				//kakaologin 메서드는 아래 경로로의 get요청을 처리
 @RequestMapping(value="/login/kakao-member", method =  RequestMethod.GET)
 public String kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpSession session) throws Throwable {
	 					//@Requestparam이라는 어노테이션을 사용하여 code라는 파라미터를 받는다. 이 파라미터는 선택적이며 요청시 제공되지 않을 수 있음
	
	 System.out.println("code: "+code);
	 
	 //kakaoMemberServiceInter.getAccessToken(code)를 호출하여 카카오로부터 받은 code를 사용해 접근 토큰을 얻음
	 String access_token = kakaoMemberServiceInter.getAccessToken(code);
	 System.out.println("accessToken: "+access_token);
	 
	 //kakaoMemberServiceInter.getuserInfo(access_token)을 호출하여 해당 토큰을 이용해 카카오 회원 정보를 얻기
	 HashMap<String, Object> userInfo = kakaoMemberServiceInter.getUserInfo(access_token);
	 System.out.println("userInfo: "+userInfo);
	 
	 String nickname = (String)userInfo.get("nickname");
	 System.out.println(nickname);
	 
	 if(userInfo.get("nickname")!=null) {
	
		 session.setAttribute("userId", userInfo.get("nickname"));
		 session.setAttribute("access_token", access_token);
		 session.setAttribute("loginok", "kakao");
	 }

	 
	 return "/";

 }
	@RequestMapping(value="/logout/kakao-member")
	public String logout(HttpSession session) {
		
		String access_token = (String)session.getAttribute("access_token");
		
		if(access_token != null && !"".equals(access_token)) {
			kakaoMemberServiceInter.kakaoLogout(access_token);
			session.removeAttribute("access_token");
			session.removeAttribute("userId");
		}else {
			System.out.println("access_token is null");
			
		}
		return "redirect:/";
	}
 
 	
} 
