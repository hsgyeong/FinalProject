package sist.last.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sist.last.dto.MemberDto;
import sist.last.mapper.MemberMapperInter;

@Service
public class MemberService implements MemberServiceInter {

	@Autowired
	MemberMapperInter mapperInter;
	
	@Override
	public void insertMember(MemberDto dto) {
		// TODO Auto-generated method stub
		mapperInter.insertMember(dto);	
	}

	@Override
	public int loginPassCheck(String id, String pass) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<>();
		
		map.put("id", id);
		map.put("pass", pass);
		
		return mapperInter.loginPassCheck(map);
	}

	@Override
	public MemberDto getDataById(String id) {
		// TODO Auto-generated method stub
		return mapperInter.getDataById(id);
	}

	@Override
	public int getSearchId(String id) {
		// TODO Auto-generated method stub
		return mapperInter.getSearchId(id);
	}

	@Override
	public int getSearchNick(String nickname) {
		// TODO Auto-generated method stub
		return mapperInter.getSearchNick(nickname);
	}

	@Override
	public String getNick(String id) {
		// TODO Auto-generated method stub
		return mapperInter.getNick(id);
	}

	
	
	

}
