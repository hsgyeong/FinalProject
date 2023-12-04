package sist.last.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface NaverMemberServiceInter {

	 HttpEntity<MultiValueMap<String, String>>authorizationCodeRequest(String code, String state);
	 ResponseEntity<String> naverAccessToken(HttpEntity request);
	 ResponseEntity<String> requestProfile(HttpEntity request);
	 HttpEntity<MultiValueMap<String, String>>NaverProfileRequest(String accessToken);
}
