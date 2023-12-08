/*package sist.last.service;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sist.last.mapper.NaverMemberMapperInter;

@Service
public class NaverMemberService implements NaverMemberMapperInter {

	@Autowired
	NaverMemberMapperInter naverMemberMapperInter;
	
	@Override
	public String getAccessToken(String Authorization_code) throws Exception {
		// TODO Auto-generated method stub
		
		String access_token = "";
		String refresh_token = "";
		String reqURL = "https://nid.naver.com/oauth2.0/token";
		
		URL url = new URL(reqURL);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		StringBuilder sb = new StringBuilder();
		
		sb.append("grant_type=authorization_code");
		sb.append("&client_id=HGSZO2Y2v6oIfhPXDCxu");
		sb.append("&")
		
		return access_token;
	}

}
*/