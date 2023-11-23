package sist.last.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import sist.last.mapper.NonBookableMapperInter;

public class NonBookableService implements NonBookableServiceInter {

    @Autowired
    NonBookableMapperInter mapper;

    @Override
    public List<String> getAccomName(String business_id) {
        return mapper.getAccomName(business_id);
    }
}
