package sist.last.service;

import java.util.List;
import sist.last.dto.ProductDto;

public interface ProductServiceInter {
    public int getAccommodation(String result);

    public List<ProductDto> getProductDataByCategory(String keyword);

    public List<String> selectCategory();
}
