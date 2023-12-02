package sist.last.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public interface NaverMemberServiceInter {

	public String getAccessToken(String type) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
		
		
	
}
