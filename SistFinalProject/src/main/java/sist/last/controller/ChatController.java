package sist.last.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sist.last.chat.Room;
import sist.last.dto.ChatDto;
import sist.last.dto.ChatRoomDto;
import sist.last.mapper.AccomMapperInter;
import sist.last.mapper.ChatMapperInter;
import sist.last.mapper.ChatRoomMapperInter;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller // 이 클래스가 Spring MVC의 컨트롤러 임을 나타내는 어노테이션이다.
@RequestMapping("/chat") // 이 컨트롤러의 기본 URL 정보가 "/chat" 임을 지정합니다.
public class ChatController {

    @Autowired
    AccomMapperInter accomMapperInter;
    @Autowired
    ChatRoomMapperInter roomMapperInter;
    @Autowired
    ChatMapperInter chatMapperInter;

    @GetMapping("/goChattingRoom")
    public String goChattingRoom(@RequestParam int room_num,
                                 Model model){
        String roomName=accomMapperInter.getOneData(roomMapperInter.getChatRoom(room_num).getAccom_num()).getAccom_name();

        model.addAttribute("room_num",room_num);
        model.addAttribute("roomName",roomName);

        return "/chat/chat";
    }

    @GetMapping("/goSellerRooms")
    public String goSellerRooms(@RequestParam int accom_num,Model model){
        List<ChatRoomDto> chatRoomList=roomMapperInter.getChatRoomByAccom(accom_num);
        String roomName=accomMapperInter.getOneData(accom_num).getAccom_name();

        model.addAttribute("chatRoomList",chatRoomList);
        model.addAttribute("roomName",roomName);

        return "/chat/room";
    }

    @GetMapping("/chatting")
    @ResponseBody
    public List<ChatDto> chatting(@RequestParam(required = false)int room_num,
                                  HttpSession session){
        try {
            Thread.sleep(100);// 0.1초 기다리기
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // 사용자의 num 받기
        String myid=(String) session.getAttribute("myid");
        List<ChatDto> chatList=new ArrayList<>();

        for (ChatDto chatDto:chatList){
            Date today=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
            Date writeday=new Date();
            try {
                writeday=sdf.parse(chatDto.getMess_writeday().toString());
            } catch (ParseException e){
                e.printStackTrace();
            }

            long diffSec=(today.getTime()-writeday.getTime());
//            diffSec-=

            // 일, 시, 분, 초
            long day=(diffSec/(60*60*24*1000L))%365;
            long hour=(diffSec/(60*60*1000L))%24;
            long minute=(diffSec/(60*1000L))%60;
            long second=(diffSec/1000L)%60;

            String preTime="";

            if(day!=0){
                // 하루 이상이 지났으면 일수만 표시
                preTime=""+day+"일 전";
            } else {
                if (hour!=0){
                    // 1분 이상 지났으면 분만 표시
                    preTime=""+minute+"분 전";
                } else {
                    // 1분 미만 방금전 표시
                    preTime="방금 전";
                }
            }
            chatDto.setMess_time(preTime);
        }
        return chatList;
    }

    @PostMapping("/fileupload")
    @ResponseBody
    public Map<String,String> fileUpload(@RequestParam MultipartFile upload,
                                         HttpSession session){
        Map<String,String> map=new HashMap<>();

        // 저장 경로
        String path=session.getServletContext().getRealPath("/chatsave");

        // 파일 이름
        String fileName=upload.getOriginalFilename();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");

        String uploadName="msg_"+sdf.format(new Date())+fileName; // 저장할 파일명

        try {
            upload.transferTo(new File(path+"/"+uploadName));
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        map.put("upload",uploadName);

        return map;
    }

}

