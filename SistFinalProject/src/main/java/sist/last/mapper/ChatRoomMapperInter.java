package sist.last.mapper;

import org.apache.ibatis.annotations.Mapper;
import sist.last.dto.ChatRoomDto;

import java.util.List;

@Mapper
public interface ChatRoomMapperInter {
    public int getMaxNum();
    public void insertChatRoom(String senderid); // 채팅방 생성
    public List<ChatRoomDto> selectChatRoom(); // 채팅방 출력
    public String getSenderId(String senderid); // 채팅방에 내 아이디로 된 채팅 방 있는지 찾기
    public ChatRoomDto getChatRoom(String senderid); // senderid 로 정보 해당 정보 가져오기

}
