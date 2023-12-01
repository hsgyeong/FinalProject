package sist.last.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sist.last.chat.Room;
import sist.last.dto.ChatRoomDto;
import sist.last.mapper.AccomMapperInter;
import sist.last.mapper.ChatRoomMapperInter;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller // 이 클래스가 Spring MVC의 컨트롤러 임을 나타내는 어노테이션이다.
@RequestMapping("/chat") // 이 컨트롤러의 기본 URL 정보가 "/chat" 임을 지정합니다.
public class ChatController {

    @Autowired
    AccomMapperInter accomMapperInter;

    @Autowired
    ChatRoomMapperInter chatRoomMapperInter;

    @GetMapping("/goChattingRoom")
    public String goChattingRoom(@RequestParam int accom_num,
                                 Model model){
        String roomname=accomMapperInter.getOneData(accom_num);

        model.addAttribute("accom_num",accom_num);

        return "/chat/chat"
    }

    @GetMapping("/goSellerRooms")
    public String goSellerRooms(@RequestParam int sangidx,Model model){
        List<ChatRoomDto> chatRoomDtoList=chatRoomMapperInter.get
    }

}

