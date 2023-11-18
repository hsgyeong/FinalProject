package sist.last.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapperInter {

    public int getAccommodation(String result);

    public List<String> selectCategory();
}
