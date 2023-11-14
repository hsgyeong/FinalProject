package sist.last.mapper;

import org.apache.ibatis.annotations.Mapper;

import sist.last.dto.AccomDto;

@Mapper
public interface AccomMapperInter {
	
	public void insert_Accom(AccomDto dto);
}
