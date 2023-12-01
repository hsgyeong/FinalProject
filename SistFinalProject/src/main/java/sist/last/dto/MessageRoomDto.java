package sist.last.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("messageRoomDto")
public class MessageRoomDto {
    private int room_num;
    private int accom_num;
    private String sender_id;
    private String receiver_id;
    private Timestamp room_createday;
}
