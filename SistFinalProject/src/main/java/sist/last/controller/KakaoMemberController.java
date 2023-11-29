package sist.last.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sist.last.service.KakaoMemberService;

@Controller //Controller 어노테이션을 통해 이 클래스는 스프링 mvc의 컨트롤러로 선언
public class KakaoMemberController {
	
	//kakaoMemberServiceInter와 memberServiceInter을 @Autowired 어노테이션을 통해 주입
	@Autowired
	public KakaoMemberService kakaoMemberService;

				//kakaologin 메서드는 아래 경로로의 get요청을 처리
 @RequestMapping(value="/login/kakao-member", method =  RequestMethod.GET)
 public String kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpSession session) throws Throwable {
	 					//@Requestparam이라는 어노테이션을 사용하여 code라는 파라미터를 받는다. 이 파라미터는 선택적이며 요청시 제공되지 않을 수 있음
	
	 System.out.println("code: "+code);
	 
	 //kakaoMemberServiceInter.getAccessToken(code)를 호출하여 카카오로부터 받은 code를 사용해 접근 토큰을 얻음
	 String access_token = kakaoMemberService.getAccessToken(code);
	 System.out.println("accessToken: "+access_token);
	 
	 //kakaoMemberServiceInter.getuserInfo(access_token)을 호출하여 해당 토큰을 이용해 카카오 회원 정보를 얻기
	 HashMap<String, Object> userInfo = kakaoMemberService.getUserInfo(access_token);
	 System.out.println("userInfo==== "+userInfo); //userInfo==== {kakao_id=121201221, kakao_nickname=홍성경}
	 
	 String kakao_nickname = (String)userInfo.get("kakao_nickname");
	 System.out.println(kakao_nickname);  //닉네임: 홍성경
	 
	 if(userInfo.get("kakao_nickname")!=null) {
	
		 session.setAttribute("kakao_nickname", userInfo.get("kakao_nickname"));
		 session.setAttribute("access_token", access_token);
		 session.setAttribute("loginok", "kakao");
		
	 }

	 
	 return "/";

 }
 

/*	@RequestMapping(value="/logout/kakaologout")
	public String kakaoLogout(HttpSession session) throws IOException {
		
		String access_token = (String)session.getAttribute("access_token");
	
		Map<String, String> map = new HashMap<String, String>();
		map.put("Authorization", "Bearer "+access_token);
		
		
		if(access_token != null && !"".equals(access_token)) {
			kakaoMemberService.kakaoLogout(access_token);
			session.removeAttribute("access_token");
			session.removeAttribute("userId");
			session.removeAttribute("kakao_nickname");
			session.removeAttribute("loginok");
		}else {
			System.out.println("access_token is null");
			
		} 
		return "redirect:/";
	}
*/
	@RequestMapping(value="/logout/kakaounlink")
 	public String KakaoUnlink(HttpSession session) {
 		String access_token = (String) session.getAttribute("access_token");
 		Integer kakaoId = (Integer) session.getAttribute("kakao_id");
 		int kakao_id = (kakaoId != null)?kakaoId.intValue():0;
 		
 		kakaoMemberService.kakaoUnlink(kakao_id, access_token);
 		session.removeAttribute("access_token");
 		session.removeAttribute("kakao_nickname");
 		session.removeAttribute("kakao_id");
 		session.removeAttribute("loginok");
 //		session.removeAttribute("userInfo");  userInfo는 지우면 안 될 것 같음. 정보가 남아있어야해서
 		session.invalidate();
 		return "redirect:/";
 	}
 
} 
