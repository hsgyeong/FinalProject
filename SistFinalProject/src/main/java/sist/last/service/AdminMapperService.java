package sist.last.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sist.last.mapper.AdminMapperInter;

@Service
public class AdminMapperService implements AdminMapperServiceInter {

	@Autowired
	AdminMapperInter mapper;

	@Override
	public int AdminloginPassCheck(String admin_id, String admin_pass) {
		
		Map<String, String> map=new HashMap<>();
		
		map.put("admin_id", admin_id);
		map.put("admin_pass", admin_pass);
		
		return mapper.AdminloginPassCheck(map);
	}
}
