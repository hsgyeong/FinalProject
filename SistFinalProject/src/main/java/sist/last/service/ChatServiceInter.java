package sist.last.service;

import sist.last.dto.ChatDto;

import java.util.List;

public interface ChatServiceInter {

    public int maxNumOfChat(); // max num 구하기.. 최신 입력글 바로 받아오기 위해
    public void insertChat(ChatDto chatDto); // 채팅 값 넣기
    public List<ChatDto> getAllDataOfChat(int roomnumber); // 채팅 목록 가져오기
}
