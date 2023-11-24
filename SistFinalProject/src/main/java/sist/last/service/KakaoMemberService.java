package sist.last.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KakaoMemberService implements KakaoMemberServiceInter {

	
	@Override
	public String getAccessToken(String authorization_code) throws Exception {
		// TODO Auto-generated method stub
		
		String access_token = "";
		String refresh_token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		
	try {	
		
		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		StringBuilder sb = new StringBuilder();
		
		sb.append("grant_type=authorization_code");
		sb.append("&client_id=f74ba8d7ae81ba39d038f11c32d7c6ae");
		sb.append("&redirect_uri=http://localhost:9000/login/kakao-member");
		sb.append("&code="+authorization_code);
		
		bw.write(sb.toString());
		bw.flush();
		
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode: "+responseCode);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line = "";
		String result = "";
		
		while((line=br.readLine())!=null) {
			result += line;
		}
		System.out.println("response body: "+result);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>(){
		});
		access_token = jsonMap.get("access_token").toString();
		refresh_token = jsonMap.get("refresh_token").toString();
		
		System.out.println("access_token: "+access_token);
		System.out.println("refresh_token: "+refresh_token);
		
		
		br.close();
		bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}

		return access_token;
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getMemberInfo(String access_token) throws Throwable {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> memberInfo = new HashMap<String, Object>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		
		try {
		
		URL url = new URL (reqURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		
		conn.setRequestProperty("Authorization", "Bearer "+access_token);
		
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode: "+responseCode);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String line = "";
		String result = "";
		
		while((line = br.readLine())!=null) {
			
			result += line;
		}
		
		System.out.println("response body: "+result);
		System.out.println("result type: "+result.getClass().getName());
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
			});
			
			System.out.println(jsonMap.get("properties"));
			
			Map<String, Object> properties = (Map<String, Object>)
			jsonMap.get("properties");
			Map<String, Object> kakao_account = (Map<String, Object>)
			jsonMap.get("kakao_account");
			
			String nickname = properties.get("nickname").toString();
			
			memberInfo.put("nickname", nickname);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}catch(IOException e) {
		e.printStackTrace();
	}
		return memberInfo;
	}

}
