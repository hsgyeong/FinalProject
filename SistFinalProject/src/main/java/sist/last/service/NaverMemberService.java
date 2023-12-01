package sist.last.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import sist.last.mapper.NaverMemberMapperInter;

@Service
public class NaverMemberService implements NaverMemberServiceInter {

	@Autowired
	NaverMemberMapperInter naverMemberMapperInter;
	
	@Override
	public String getAccessToken(String code, String state) throws JsonProcessingException {
		// TODO Auto-generated method stub
		return naverMemberMapperInter.getAccessToken(code, state);
	}

}
