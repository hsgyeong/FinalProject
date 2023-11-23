package sist.last.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import sist.last.dto.NonBookableDto;
import sist.last.mapper.NonBookableMapperInter;

public class NonBookableService implements NonBookableServiceInter {

    @Autowired
    NonBookableMapperInter mapperInter;

    @Override
    public List<String> getAccomName(String business_id) {
        return mapperInter.getAccomName(business_id);
    }

    @Override
    public String getAccomNumber(String accom_name) {
        return mapperInter.getAccomNumber(accom_name);
    }

    @Override
    public void addNonBookableDate(NonBookableDto dto) {
        mapperInter.addNonBookableDate(dto);
    }
}
