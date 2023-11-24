package sist.last.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import sist.last.dto.MemberDto;

@Mapper
public interface MemberMapperInter {

	public void insertMember(MemberDto dto);
	public int loginPassCheck(Map<String, String> map);
	public MemberDto getDataById(String info_id);
	public int getSearchId(String info_id);
	public int getSearchNick(String info_nickname);
	public String getNickname(String info_id);
	public List<MemberDto> getAllMembers();
	public void updateMember(MemberDto dto);
	public void deleteMember(String info_id);
	public int getCouponCount(String info_id);
}
