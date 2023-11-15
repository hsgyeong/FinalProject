package sist.last.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import sist.last.dto.MemberDto;

@Mapper
public interface MemberMapperInter {

	public void insertMember(MemberDto dto);
	public int loginPassCheck(Map<String, String> map);
	public MemberDto getDataById(String id);
	public int getSearchId(String id);
}
