package sist.last.service;

import java.util.List;
import sist.last.dto.ProductDto;

public interface ProductServiceInter {
    public int getAccommodation(String result);

    public List<ProductDto> getProductDataOfCategory(String keyword);

    public List<ProductDto> getProductDataOfName(String keyword);

    public List<ProductDto> getProductDataOfHashTag(String keyword);

    public List<ProductDto> getProductDataOfLocation(String keyword);

    public List<String> selectCategory();
}
