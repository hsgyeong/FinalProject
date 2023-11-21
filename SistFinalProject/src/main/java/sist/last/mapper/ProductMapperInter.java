package sist.last.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import sist.last.dto.ProductDto;

@Mapper
public interface ProductMapperInter {

    public int getAccommodation(String result);

    public List<ProductDto> getProductDataByCategory(String keyword);

    public List<String> selectCategory();
}
