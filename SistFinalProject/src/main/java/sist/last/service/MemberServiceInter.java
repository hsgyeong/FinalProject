package sist.last.service;

import sist.last.dto.MemberDto;

public interface MemberServiceInter {

	public void insertMember(MemberDto dto);
	public int loginPassCheck(String id, String pass);
	public MemberDto getDataById(String id);
	public int getSearchId(String id);
	public int getSearchNick(String nickname);
	public String getNick(String id);
}
