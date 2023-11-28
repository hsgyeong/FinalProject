package sist.last.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("chatRoomDto")
public class ChatRoomDto {
    private int roomNumber;
    private String senderId;
    private String receiverId;
    private Timestamp roomCreateDay;
}
