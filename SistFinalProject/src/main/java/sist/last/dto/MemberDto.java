package sist.last.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class MemberDto {

	private String id;
	private String name;
	private String nickname;
	private String pass;
	private String hp;
	private String email;
	private String birth;
	private Timestamp gaipday;
}
