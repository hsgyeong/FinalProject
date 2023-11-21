package sist.last.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import sist.last.dto.AccomDto;

@Mapper
public interface ProductMapperInter {

    public int getAccommodation(String result);

    public List<AccomDto> getProductData(String keyword);

    public List<String> selectCategory();
}
