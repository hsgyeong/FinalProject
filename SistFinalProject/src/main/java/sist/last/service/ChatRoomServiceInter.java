package sist.last.service;

import sist.last.dto.ChatDto;
import sist.last.dto.ChatRoomDto;

import java.util.List;

public interface ChatRoomServiceInter {

    public int getMaxNum();
    public void insertChatRoom(ChatDto chatDto);
    public List<ChatDto> selectChatRoom();
}
