package sist.last.service;

import java.util.HashMap;

public interface KakaoMemberServiceInter {

	String getAccessToken(String authorize_code) throws Throwable;
	
	public HashMap<String, Object> getMemberInfo(String access_token) throws Throwable;
}
