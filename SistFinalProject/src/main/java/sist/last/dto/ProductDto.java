package sist.last.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("productDto")
@Data
public class ProductDto {
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
    private int room_num;
    private String room_name;
    private int room_price;
    private int room_minpeople;
    private int room_maxpeople;
    private String room_location;
    private String room_photo;
    private String room_info;
    private int room_count;
}
