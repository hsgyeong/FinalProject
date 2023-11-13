package sist.last.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("AccomDto")
public class AccomDto {

	private int accom_num;
	private String business_id;
	private String accom_name;
	private String accom_category;
	private String accom_photo;
	private String accom_location;
	private int accom_readcount;
	private double accom_score;
	
}
