package sist.last.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sist.last.dto.ChatDto;
import sist.last.dto.ChatRoomDto;
import sist.last.mapper.ChatMapperInter;
import sist.last.mapper.ChatRoomMapperInter;

import java.util.List;

@Service
public class ChatRoomService implements ChatRoomServiceInter {

    @Autowired
    ChatRoomMapperInter chatRoomMapperInter;

    @Override
    public int getMaxNum() {
        return chatRoomMapperInter.getMaxNum();
    }

    @Override
    public void insertChatRoom(ChatDto chatDto) {
        chatRoomMapperInter.insertChatRoom(chatDto);
    }

    @Override
    public List<ChatDto> selectChatRoom() {
        return chatRoomMapperInter.selectChatRoom();
    }

    @Override
    public String getSenderId(String senderid) {

        return String.valueOf(chatRoomMapperInter.getSenderId(senderid));
    }
}



