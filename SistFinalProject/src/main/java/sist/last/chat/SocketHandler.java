//package sist.last.chat;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//import java.util.HashMap;
//
//@Component
//public class SocketHandler extends TextWebSocketHandler {
//
//    HashMap<String,WebSocketSession> sessionMap=new HashMap<>(); // 웹소켓 세션을 담아둘 맵
//
//    @Override
//    public void handleTextMessage(WebSocketSession session, TextMessage message){
//        // 메시지 발송
//        String msg=message.getPayload();
//        for (String key:sessionMap.keySet()){
//            WebSocketSession wss=sessionMap.get(key);
//            try {
//                wss.sendMessage(new TextMessage(msg));
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @SuppressWarnings("uncheked")
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
//        // 소켓 연결
//        super.afterConnectionEstablished(session);
//        sessionMap.put(session.getId(),session);
//
//        JSONObject obj=new JSONObject();
//        obj.put("type","getId");
//        obj.put("sessionId",session.getId());
//        session.sendMessage(new TextMessage(obj.toJSONString()));
//    }
//
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
//        // 소켓 종료
//        sessionMap.remove(session.getId());
//        super.afterConnectionClosed(session,status);
//    }
//
//    public static JSONObject JsonToObjectParser(String jsonStr){
//        JSONParser parser=new JSONParser();
//        JSONObject obj=null;
//
//            try {
//                obj=(JSONObject) parser.parse(jsonStr);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//        return obj;
//    }
//}



//////////////////////////////////////////////////////////////////////
package sist.last.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


// 구현체에 등록할 SocketHandler 입니다.
// 이 코드는 Spring Framework에서 WebSocket핸들러를 정의하는 'SocketHandler' 클래스 입니다. 이 핸들러는 웹소켓 연결, 메시지 수신, 소켓 종료 등과 관련된 로직을 구현하고 있습니다. 아래 코드는 주요 부분에 대한 설명입니다.

// afterConnectionEstablished 메소드는 웹소켓 연결이 되면 동작합니다.
// afterConnectionClosed 메소드는 반대로 웹소켓이 종료되면 동작합니다.
// handleTextMessage 메소드는 메시지를 수신하면 실행됩니다. 상속받은 TextWebSocketHandler는 handleTextMEssage를 실행시키며, 메시지 타입에 따라 HandleBinaryMessage 또는 handleTextMessage가 실행됩니다.

@Component
public class SocketHandler extends TextWebSocketHandler {

    //HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
    List<HashMap<String, Object>> rls = new ArrayList<>(); //웹소켓 세션을 담아둘 리스트 ---roomListSessions. 각 항목은 방 번호와 해당 방에 속한 세션들의 맵입니다.

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 이 메서드는 클라이언트로부터 메시지를 받았을 때 실행되는 부분입니다.
        // 수신한 메시지를 JSON 형식으로 파싱한 후, 해당 메시지의 방 번호를 추출합니다.
        // 방 번호에 해당하는 방의 세션 리스트에서 각 세션에게 메시지를 전송합니다.

        //메시지 발송
        String msg = message.getPayload(); // WebSocket에서 받은 메시지를 가져옵니다.
        JSONObject obj = jsonToObjectParser(msg); // 수신한 메시지를 JSON 객체로 파싱

        String rN = (String) obj.get("roomNumber"); // 메시지에서 방 번호를 추출합니다.
        HashMap<String, Object> temp = new HashMap<String, Object>(); // 방에 속한 세션들을 저장할 임시 맵입니다.
        if(rls.size() > 0) {
            for(int i=0; i<rls.size(); i++) {
                String roomNumber = (String) rls.get(i).get("roomNumber"); //세션리스트의 저장된 방번호를 가져와서
                if(roomNumber.equals(rN)) { // 메시지에 명시된 방 번호와 같은값의 방이 존재한다면
                    temp = rls.get(i); // 해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
                    break;
                }
            }

            //해당 방의 세션들만 찾아서 메시지를 발송해준다.
            for(String k : temp.keySet()) {
                if(k.equals("roomNumber")) { //다만 방번호일 경우에는 건너뛴다.
                    continue;
                }

                WebSocketSession wss = (WebSocketSession) temp.get(k);
                if(wss != null) {
                    try {
                        wss.sendMessage(new TextMessage(obj.toJSONString())); // 메시지를 해당 세션에게 전송
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //소켓 연결
        super.afterConnectionEstablished(session);
        boolean flag = false;
        String url = session.getUri().toString();
        System.out.println(url);
        String roomNumber = url.split("/chating/")[1];
        int idx = rls.size(); //방의 사이즈를 조사한다.
        if(rls.size() > 0) {
            for(int i=0; i<rls.size(); i++) {
                String rN = (String) rls.get(i).get("roomNumber");
                if(rN.equals(roomNumber)) {
                    flag = true;
                    idx = i;
                    break;
                }
            }
        }

        if(flag) { //존재하는 방이라면 세션만 추가한다.
            HashMap<String, Object> map = rls.get(idx);
            map.put(session.getId(), session);
        }else { //최초 생성하는 방이라면 방번호와 세션을 추가한다.
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("roomNumber", roomNumber);
            map.put(session.getId(), session);
            rls.add(map);
        }

        //세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
        JSONObject obj = new JSONObject();
        obj.put("type", "getId");
        obj.put("sessionId", session.getId());
        session.sendMessage(new TextMessage(obj.toJSONString()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 종료
        if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
            for(int i=0; i<rls.size(); i++) {
                rls.get(i).remove(session.getId());
            }
        }
        super.afterConnectionClosed(session, status);
    }

    private static JSONObject jsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}