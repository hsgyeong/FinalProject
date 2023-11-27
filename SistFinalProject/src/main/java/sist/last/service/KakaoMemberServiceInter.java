package sist.last.service;

import java.util.HashMap;

public interface KakaoMemberServiceInter {

	String getAccessToken(String authorize_code) throws Throwable;
	
	public HashMap<String, Object> getUserInfo(String access_token) throws Throwable;
	public void kakaoLogout(String access_token);
}
