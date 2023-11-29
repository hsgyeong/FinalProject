package sist.last.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;

import com.fasterxml.jackson.databind.JsonNode;
//JsonNode 지우기
public interface KakaoMemberServiceInter {

	public String getAccessToken(String authorization_code) throws Exception;
	public HashMap<String, Object> getUserInfo(String access_token) throws Throwable;
/*	public void kakaoLogout(String access_token);  */
	public JsonNode Logout(String authorization_code);
	public void kakaoUnlink(int kakao_id, String access_token);
}
