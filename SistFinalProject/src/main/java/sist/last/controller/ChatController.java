package sist.last.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sist.last.chat.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller // 이 클래스가 Spring MVC의 컨트롤러 임을 나타내는 어노테이션이다.
@RequestMapping("/chat") // 이 컨트롤러의 기본 URL 정보가 "/chat" 임을 지정합니다.
public class ChatController {

    List<Room> roomList = new ArrayList<Room>(); // 방 정보를 저장하는 리스트
    static int roomNumber = 0; // 방 번호 관리하는 변수, 0으로 초기화

    @RequestMapping("/chat")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/chat/chat");
        return mv;
    }

    /**
     * 방 페이지
     * @return
     */
    @RequestMapping("/room1")
    public ModelAndView room() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/chat/room");
        return mv;
    }

    /**
     * 방 생성하기
     * @param params
     * @return
     */
    @RequestMapping("/createRoom") // '/chat/createRoom'경로로  POST 요청이 오면 호출되는 메서드로, 새로운 채탕방을 생성합니다. 생성된 방은 'roomLIst'에 추가되고, 현재까지 생성된 모든 방의 목록이 클라이언트에게 반환됩니다.
    public @ResponseBody List<Room> createRoom(@RequestParam HashMap<Object, Object> params){ // '@ResponseBody' : 메서드의 반환 값이 HTTP 응답 본문에 직접 포함되어야 함을 나타내는 어노테이션입니다.
        // 'createRoom' 메서드 파라미터 :
        String roomName = (String) params.get("roomName");
        if(roomName != null && !roomName.trim().equals("")) {
            Room room = new Room();
            room.setRoomNumber(++roomNumber);
            room.setRoomName(roomName);
            roomList.add(room);
        }
        return roomList;
    }

    /**
     * 방 정보가져오기
     * @param params
     * @return
     */
    @RequestMapping("/getRoom")
    public @ResponseBody List<Room> getRoom(@RequestParam HashMap<Object, Object> params){
        return roomList;
    }

    /**
     * 채팅방
     * @return
     */
    @RequestMapping("/moveChating")
    public ModelAndView chating(@RequestParam HashMap<Object, Object> params) {
        ModelAndView mv = new ModelAndView();
        int roomNumber = Integer.parseInt((String) params.get("roomNumber"));

        List<Room> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
        if(new_list != null && new_list.size() > 0) {
            mv.addObject("roomName", params.get("roomName"));
            mv.addObject("roomNumber", params.get("roomNumber"));
            mv.setViewName("/chat/chat");
        }else {
            mv.setViewName("/chat/room");
        }
        return mv;
    }
}

