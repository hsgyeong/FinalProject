package sist.last.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sist.last.mapper.ProductMapperInter;

@Service
public class ProductService implements ProductServiceInter{

    @Autowired
    ProductMapperInter mapperInter;
}
