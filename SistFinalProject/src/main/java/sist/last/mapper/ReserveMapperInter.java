package sist.last.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import sist.last.dto.ReserveDto;

@Mapper
public interface ReserveMapperInter {

	public void reservingInsert(ReserveDto dto);
	public ReserveDto getOneDataByIdOfReserve(Map<String, Object> map);
	public int getMaxIdxOfReserve();
}
