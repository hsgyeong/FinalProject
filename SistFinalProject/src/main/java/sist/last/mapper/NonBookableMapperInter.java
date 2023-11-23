package sist.last.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import sist.last.dto.NonBookableDto;

@Mapper
public interface NonBookableMapperInter {

    public List<String> getAccomName(String business_id);

    public String getAccomNumber(String accom_name);

    public void addNonBookableDate(NonBookableDto dto);
}
