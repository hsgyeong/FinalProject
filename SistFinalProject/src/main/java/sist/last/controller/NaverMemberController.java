/*package sist.last.controller;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class NaverMemberController {

	@GetMapping("/login/naverLogin")
	public String naverConnect() {
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130,random).toString(32);
		//System.out.println(state);
		
		StringBuffer br = new StringBuffer();
		br.append("https://nid.naver.com/oauth2.0/authorize?");
		br.append("response_type=code");
		br.append("&client_id=HGSZO2Y2v6oIfhPXDCxu");
		br.append("&redirect_uri=http://localhost:9000/login/naver-member");
		br.append("&state="+state);
		
		return "redirect:"+br;
	}
	
	@RequestMapping(value = "/login/naver-member", method = {RequestMethod.GET, RequestMethod.POST},
			produces = "application/json")
	public void naverLogin(@RequestParam(value="code") String code, @RequestParam(value="state") String state) {
		
		WebClient webclient = WebClient.builder()
				.baseUrl("https://nid.naver.com")
				.defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE)
				.build();
		
		JSONObject response = webclient.post()
				.uri(uriBuilder -> uriBuilder
						.path("/oauth2.0/token")
						.queryParam("client_id", "HGSZO2Y2v6oIfhPXDCxu")
						.queryParam("client_secret","IRLj_NcV1v")
						.queryParam("grant_type", "authorization_code")
						.queryParam("state", state)
						.queryParam("code", code)
						.build())
				.retrieve().bodyToMono(JSONObject.class).block();
		System.out.println(response.toString());
		
		String token = response.getString("access_token");
		System.out.println(token);
	}
	
	@RequestMapping(value = "/login/naver-member", method = {RequestMethod.GET,
			RequestMethod.POST}, produces = "application/json")
	public void NaverLogin(@RequestParam(value="code") String code, 
			@RequestParam(value="state") String state) {
		
		String token = response.get("access_token");
		
	}
	
	public void getNaverMemberInfo(String accessToken) {
		
		WebClient webclient = WebClient.builder()
				.baseUrl("https://openapi.naver.com")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		JSONObject response = webclient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/v1/nid/me")
						.build())
				.header("Authorization", "Bearer "+accessToken)
				.retrieve()
				.bodyToMono(JSONObject.class).block();
		
		Map<String, Object> res = (Map<String, Object>)
				response.get("response");
				String naver_name = (String) res.get("name");
				String naver_nickname = (String) res.get("nickname");
				String naver_hp = (String) res.get("hp");
				String naver_email = (String) res.get("email");
				
	}
}
*/