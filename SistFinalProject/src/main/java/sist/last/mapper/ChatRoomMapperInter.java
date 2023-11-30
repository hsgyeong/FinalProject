package sist.last.mapper;

import org.apache.ibatis.annotations.Mapper;
import sist.last.dto.ChatDto;
import sist.last.dto.ChatRoomDto;

import java.util.List;

@Mapper
public interface ChatRoomMapperInter {
    public int getMaxNum();
    public void insertChatRoom(ChatDto chatDto); // 채팅방 생성
    public List<ChatDto> selectChatRoom(); // 채팅방 출력
    public String getSenderId(String senderid); // 채팅방에 내 아이디로 된 채팅 방 있는지 찾기


}
