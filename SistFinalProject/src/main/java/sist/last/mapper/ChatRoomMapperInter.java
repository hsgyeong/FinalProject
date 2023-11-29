package sist.last.mapper;

import org.apache.ibatis.annotations.Mapper;
import sist.last.dto.ChatDto;
import sist.last.dto.ChatRoomDto;

import java.util.List;

@Mapper
public interface ChatRoomMapperInter {
    public int getMaxNum();
    public void insertChatRoom(ChatDto chatDto);
    public List<ChatDto> selectChatRoom();


}
