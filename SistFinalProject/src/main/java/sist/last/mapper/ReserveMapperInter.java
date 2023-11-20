package sist.last.mapper;

import org.apache.ibatis.annotations.Mapper;

import sist.last.dto.ReserveDto;

@Mapper
public interface ReserveMapperInter {

	public void reservingInsert(ReserveDto dto);
}
