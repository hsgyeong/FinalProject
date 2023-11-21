package sist.last.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sist.last.dto.ProductDto;
import sist.last.mapper.ProductMapperInter;

@Service
public class ProductService implements ProductServiceInter {

    @Autowired
    ProductMapperInter mapperInter;

    @Override
    public int getAccommodation(String result) {
        return 0;
    }

    @Override
    public List<ProductDto> getProductDataOfCategory(String keyword) {
        return mapperInter.getProductDataOfCategory(keyword);
    }

    @Override
    public List<ProductDto> getProductDataOfName(String keyword) {
        return mapperInter.getProductDataOfName(keyword);
    }

    @Override
    public List<ProductDto> getProductDataOfHashTag(String keyword) {
        return mapperInter.getProductDataOfHashTag(keyword);
    }

    @Override
    public List<ProductDto> getProductDataOfLocation(String keyword) {
        return mapperInter.getProductDataOfLocation(keyword);
    }


    @Override
    public List<String> selectCategory() {
        return mapperInter.selectCategory();
    }
}
