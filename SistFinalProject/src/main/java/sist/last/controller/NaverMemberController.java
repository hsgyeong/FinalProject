package sist.last.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NaverMemberController {

	@RequestMapping("/login/naverLogin")
	public String naverLogin(HttpSession session) throws UnsupportedEncodingException, UnknownHostException
	{		
		SecureRandom random = new SecureRandom();
		
		String state = new BigInteger(130, random).toString();
		
		StringBuffer url = new StringBuffer();
		url.append("https://nid.naver.com/oauth2.0/authorize?");
		url.append("response_type=code");
		url.append("&client_id=HGSZO2Y2v6oIfhPXDCxu");
		url.append("&state="+state);
		url.append("&redirect_uri=http://localhost:9000/login/naver-member");
	
				session.setAttribute("state", state);
				
		return "redirect:"+url;
			
	}
	
	
}
