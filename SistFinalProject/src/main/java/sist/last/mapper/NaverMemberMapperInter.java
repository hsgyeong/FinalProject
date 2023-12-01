package sist.last.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fasterxml.jackson.core.JsonProcessingException;

@Mapper
public interface NaverMemberMapperInter {

	public String getAccessToken(String code, String state) throws JsonProcessingException;
	
}
