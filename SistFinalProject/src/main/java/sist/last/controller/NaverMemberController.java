/*package sist.last.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import sist.last.dto.MemberDto;
import sist.last.service.MemberService;
import sist.last.service.NaverMemberService;

@Controller
public class NaverMemberController {

	@Autowired
	NaverMemberService naverMemberService;

	@Autowired
	MemberService memberService;

	@RequestMapping(value="/login/naver-member", method= RequestMethod.GET)
	private String getAccessToken(@RequestParam(value = "authorization_code", required = false)String code, HttpSession session, MemberDto memberDto) throws Exception {
		
		String access_token = "";
		String refresh_token = "";
		String reqURL = "https://nid.naver.com/oauth2.0/authorize";

		try {
		
		URL url = new URL(reqURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		StringBuilder sb = new StringBuilder();
		
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130,random).toString(32);
		
		System.out.println("state===="+state);
			
		sb.append("response_type=code");
		sb.append("&client_id=HGSZO2Y2v6oIfhPXDCxu");
		sb.append("&state="+state);
		sb.append("&redirect_uri=http://localhost:9000/login/naver-member");
		
		bw.write(sb.toString());
		bw.flush();
		
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode: "+responseCode);
		
		if(responseCode ==200) {
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder result = new StringBuilder();
		String line;
		//String result = "";
		
		while((line=br.readLine())!=null) {
			result.append(line);
		}
		
		System.out.println("responseBody: "+result.toString());
		
		br.close();
		}else
		{
			System.out.println("에러발생에러발생에러발생에러발생에러발생에러발생");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>(){
		});
		access_token = jsonMap.get("access_token").toString();
		refresh_token = jsonMap.get("refresh_token").toString();
		
		System.out.println(access_token);
		System.out.println(refresh_token);
		
		br.close();
		bw.close(); 
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return access_token;
	}
	
	
	@GetMapping()
	public void neverLoin(HttpServletRequest request, HttpServletResponse response)
	{
	
	}
	
	
	
}
*/