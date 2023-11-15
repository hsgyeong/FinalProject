package sist.last.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sist.last.dto.RoomDto;

@Mapper
public interface RoomMapperInter {
	
	public void insertRoom(RoomDto dto);
	public List<RoomDto> getAllData();
	public int getTotalCount();
}
