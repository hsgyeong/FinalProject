package sist.last.mapper;

import org.apache.ibatis.annotations.Mapper;

import sist.last.dto.RoomDto;

@Mapper
public interface RoomMapperInter {
	
	public void insert_Room(RoomDto dto);
}
