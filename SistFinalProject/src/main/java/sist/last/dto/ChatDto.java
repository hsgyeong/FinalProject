package sist.last.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("chatDto")
public class ChatDto {
    private int chatNum;
    private int roomNumber;
    private String senderId;
    private String receiverId;
    private String chatContent;
    private String chatDate;
}
