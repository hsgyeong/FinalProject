package sist.last.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Mapper;

import com.fasterxml.jackson.databind.JsonNode;

import sist.last.dto.MemberDto;
//JsonNode 지우기
public interface KakaoMemberServiceInter {

	public String getAccessToken(String authorization_code) throws Exception;
	public HashMap<String, Object> getUserInfo(String access_token) throws Throwable;
	public void kakaoLogout(String access_token); 
	public MemberDto getDataByKakao(String kakao_id);
	public void insertKakaoMember (MemberDto memberDto);
	public int getSearchKakaoId(String kakao_id);
	public MemberDto getDataByKakaoId(String loggedKakaoId);
/*	public JsonNode Logout(String authorization_code); */
/*	public void kakaoUnlink(int kakao_id, String access_token);*/
}
