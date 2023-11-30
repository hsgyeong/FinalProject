package sist.last.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sist.last.dto.AccomDto;
import sist.last.mapper.AdminMapperInter;

@Service
public class AdminMapperService implements AdminMapperServiceInter {

	@Autowired
	AdminMapperInter mapper;

	@Override
	public int adminloginPassCheck(String admin_id, String admin_pass) {
		
		Map<String, String> map=new HashMap<>();
		
		map.put("admin_id", admin_id);
		map.put("admin_pass", admin_pass);
		
		return mapper.adminloginPassCheck(map);
	}

	@Override
	public void adminApprovalOfAccom(int accom_num) {
		// TODO Auto-generated method stub
		mapper.adminApprovalOfAccom(accom_num);
	}

	@Override
	public List<AccomDto> getAccomPagingList(int start, int perpage) {
		// TODO Auto-generated method stub
		Map<String, Integer> map=new HashMap<>();
		
		map.put("start", start);
		map.put("perpage", perpage);
		
		return mapper.getAccomPagingList(map);
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return mapper.getTotalCount();
	}
}
