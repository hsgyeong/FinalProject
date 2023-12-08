package sist.last.mapper;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface NaverMemberMapperInter {
	
	public String getAccessToken(String Authorization_code)throws Exception;
}
