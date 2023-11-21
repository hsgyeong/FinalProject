package sist.last.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("AccomDto")
public class AccomDto {

    private int accom_num;
    private String business_id;
    private String accom_name;
    private String accom_category;
    private String accom_photo;
    private String accom_location;
    private String accom_address;
    private String accom_hashtag;
    private int accom_readcount;
    private double accom_score;
    private double accom_latitude;
    private double accom_longitude;


}
