package sist.last.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sist.last.dto.AccomDto;

@Mapper
public interface AccomMapperInter {
	
	public void insertAccom(AccomDto dto);
	public List<AccomDto> getAllData();
	public int getTotalCount();
	public void deleteAccom(int accom_num);
	public AccomDto getOneData(int accom_num);
	public void updateAccom(AccomDto dto);
}