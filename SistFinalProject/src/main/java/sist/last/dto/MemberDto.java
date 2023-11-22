package sist.last.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data
@Alias("member")
public class MemberDto {

	private String info_id;
	private String info_name;
	private String info_nickname;
	private String info_pass;
	private String info_hp;
	private String info_email;
	private String info_birth;
	private Timestamp info_gaipday;
	
	
}
