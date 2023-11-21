package sist.last.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sist.last.dto.RoomDto;

@Mapper
public interface RoomMapperInter {
	
	public void insertRoom(RoomDto dto);
	public List<RoomDto> getAllData(String accom_num);
	public RoomDto getData(String accom_num);
	public int getTotalCount(String accom_num);
	public void deleteRoom(int num);
	public RoomDto getOneData(int num);
	public void updateRoom(RoomDto dto);
}
