package sist.last.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sist.last.dto.ChatDto;
import sist.last.mapper.ChatMapperInter;

import java.util.List;

@Service
public class ChatService implements ChatServiceInter{

    @Autowired
    ChatMapperInter inter;

    @Override
    public int maxNumOfChat() {
        return inter.maxNumOfChat();
    }

    @Override
    public void insertChat(ChatDto chatDto) {
        inter.insertChat(chatDto);
    }

    @Override
    public List<ChatDto> getAllDataOfChat(int roomnumber) {
        return inter.getAllDataOfChat(roomnumber);
    }
}
