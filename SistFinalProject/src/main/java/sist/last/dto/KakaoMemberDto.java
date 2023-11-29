package sist.last.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("kakaomember")
public class KakaoMemberDto {

	private String kakao_id;
	private String kakao_nickname;
	private Timestamp kakao_gaipday;
}
