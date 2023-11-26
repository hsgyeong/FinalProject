package sist.last.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import sist.last.dto.ProductDto;

@Mapper
public interface ProductMapperInter {

    public int getAccommodation(String result);

    public List<ProductDto> getProductDataOfCategory(String keyword);

    public List<ProductDto> getProductDataOfName(String keyword);

    public List<ProductDto> getProductDataOfHashTag(String keyword);

    public List<ProductDto> getProductDataOfLocation(String keyword);

    public List<ProductDto> getProductClickOrderByLowPriceOfCategory(String keyword);

    public List<ProductDto> getProductClickOrderByLowPriceOfHashTag(String keyword);

    public List<ProductDto> getProductClickOrderByLowPriceOfLocation(String keyword);

    public List<ProductDto> getProductClickOrderByLowPriceOfName(String keyword);


    public List<String> selectCategory();
}
