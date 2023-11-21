package sist.last.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sist.last.dto.ReserveDto;
import sist.last.mapper.ReserveMapperInter;

@Service
public class ReserveService implements ReserveServiceInter {

	@Autowired
	ReserveMapperInter mapper;
	
	@Override
	public void reservingInsert(ReserveDto dto) {
		// TODO Auto-generated method stub
		mapper.reservingInsert(dto);
	}

}
