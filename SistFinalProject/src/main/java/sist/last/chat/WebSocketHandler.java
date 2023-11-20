package sist.last.chat;

import org.springframework.stereotype.Component;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Component
@ServerEndpoint("/websocket")
public class WebSocketHandler {

    @OnMessage
    public void onMessage(String message, Session session) {
        // WebSocket 메시지 수신 시 처리 로직
    }
}