package sist.last.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<String> selectCategory() {
        return mapperInter.selectCategory();
    }
}
