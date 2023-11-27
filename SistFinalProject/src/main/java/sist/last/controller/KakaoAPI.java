package sist.last.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class KakaoAPI {
	
	public String getAccessToken(String code) {
	//받은 코드를 가지고 2번째 요청인 토큰을 요청하여 받기 위한 작업
		String accessToken = "";
		String refreshToken = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		String authorization_code=code;
		
		try {
			//웹상의 경로 객체화하기
			URL url = new URL(reqURL);
			
			//웹상의 경로와 연결하기
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//POST방식으로 요청하기 위해 setDoOupPut을 true로 지정해줘야 한다.
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			//POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송하기
			//전달하고자 하는 파라미터들을 보낼 OutputStream 준비하기
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			
			//파라미터 4개 만들어서 bw를 통해 카카오 서버로 보내기
			//파라미터들을 담을 문자열 생성
			
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=f74ba8d7ae81ba39d038f11c32d7c6ae");
			sb.append("&redirect_uri=http://localhost:9000/login/kakao-member");
	//		sb.append("&code="+code);
	//		sb.append("&code="+authorization_code);
			sb.append("&response_type=code");
	//		sb.append("&response_type=code");
			
			//연결된 카카오서버(bw)로 준비된 파라미터 전달
			bw.write(sb.toString());
			bw.flush();
		    int responseCode= conn.getResponseCode();
			System.out.println("response code = "+ responseCode); 
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			String result ="";
			//StringBuffer result = new StringBuffer();
		
			//한줄 단위로 읽어서 result라는 StringBuffer에 적재하기
			while((line = br.readLine())!=null) {
			
				result += line;
			}
			
			//현재 result는 하나의 문자열로 인식
			
			System.out.println("resonse body = "+result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
	//		accessToken = (String) json.get("access_token");
	//		refreshToken = (String) json.get("refresh_token");
			
		accessToken  = element.getAsJsonObject().get("access_token").getAsString();
		refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
			
			br.close();
			bw.close();
		
		//	String header = "Bearer "+accessToken;
		//	String apiUrl = "https://kapi.kakao.com/v2/user/me";
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return accessToken;
	
		}
	
	public HashMap<String, Object> getMemberInfo(String accessToken){
		
		HashMap<String, Object> userInfo  = new HashMap<String, Object>();
		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode =" + responseCode);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			String result="";
			
			while((line = br.readLine()) != null) {
				
				result += line;
			}
			System.out.println("response body = "+result);
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
		//	String email = properties.getAsJsonObject().get("email").getAsString();
			
			userInfo.put("nickname", nickname);
		//	userInfo.put("email", email);
					
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return userInfo;
	}

	public void KakaoLogout(String accessToken) {
	
	String reqURL = "https://kapi.kakao.com/v1/user/logout";
	try {
		URL url = new URL(reqURL);
		
		HttpURLConnection conn =(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "Bearer "+accessToken);
		int responseCode = conn.getResponseCode();
		System.out.println("responseCode ="+responseCode);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		
		String result = "";
		String line = "";
		
		while((line = br.readLine())!=null) {
			result += line;
			
		}
		System.out.println(result);
	}catch(Exception e) {
		e.printStackTrace();
	}

	}
}

