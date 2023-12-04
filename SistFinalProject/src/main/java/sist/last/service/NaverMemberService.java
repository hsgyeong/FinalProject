package sist.last.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class NaverMemberService implements NaverMemberServiceInter {

	@Override
	public HttpEntity<MultiValueMap<String, String>> authorizationCodeRequest(String code, String state) {
		// TODO Auto-generated method stub
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		params.add("grant_type", "authorization_code");
		params.add("client_id","HGSZO2Y2v6oIfhPXDCxu");
		params.add("client_secret", "IRLj_NcV1v");
		params.add("code",code);
		
		return new HttpEntity<>(params, headers);
		
	}

	@Override
	public ResponseEntity<String> naverAccessToken(HttpEntity request) {
		// TODO Auto-generated method stub
		
		RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.exchange("https://nid.naver.com/oauth2.0/token", 
				HttpMethod.POST,
				request,
				String.class);
	}

	@Override
	public ResponseEntity<String> requestProfile(HttpEntity request) {
		// TODO Auto-generated method stub
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange("https://openapi.naver.com/v1/nid/me",
				HttpMethod.POST,
				request,
				String.class);
	}

	@Override
	public HttpEntity<MultiValueMap<String, String>> NaverProfileRequest(String accessToken) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		return new HttpEntity<>(headers);
	}

}
