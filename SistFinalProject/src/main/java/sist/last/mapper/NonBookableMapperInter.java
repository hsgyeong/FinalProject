package sist.last.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NonBookableMapperInter {

    public List<String> getAccomName(String business_id);
}
