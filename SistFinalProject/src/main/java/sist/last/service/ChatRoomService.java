package sist.last.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sist.last.dto.ChatDto;
import sist.last.dto.ChatRoomDto;
import sist.last.mapper.ChatMapperInter;

import java.util.List;

@Service
public class ChatRoomService implements ChatRoomServiceInter {


    @Override
    public int getMaxNum() {
        return 0;
    }

    @Override
    public void insertChatRoom(ChatDto chatDto) {

    }

    @Override
    public List<ChatDto> selectChatRoom() {
        return null;
    }
}



