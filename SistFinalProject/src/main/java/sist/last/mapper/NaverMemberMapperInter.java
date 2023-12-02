package sist.last.mapper;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NaverMemberMapperInter {

	public String getAccessToken(String type) throws URISyntaxException, MalformedURLException, UnsupportedEncodingException;
	
}
