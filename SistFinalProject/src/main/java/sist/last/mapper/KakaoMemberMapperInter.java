package sist.last.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KakaoMemberMapperInter {

	public String getAccessToken(String authorization_code) throws Exception;
	public HashMap<String, Object> getUserInfo(String access_token) throws Throwable;
	public void kakaoLogout(String access_token);
}
