/*package sist.last.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NaverMemberController {

	@GetMapping("/login/naver-member")
	public String NaverLogin(@RequestParam String code, @RequestParam String state) {
		
		String accessToken = AccessToken(naverAccessToken(authorizationCodeRequest(code,state)).getBody());
		return "code: "+code;
	}
	

}
*/