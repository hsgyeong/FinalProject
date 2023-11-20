package sist.last.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("reserveDto")
public class ReserveDto {

	private int reserve_id;
	private String info_id;
	private int room_num;
	private String reserve_checkin;
	private String reserve_checkout;
	private String reserve_status;
}
