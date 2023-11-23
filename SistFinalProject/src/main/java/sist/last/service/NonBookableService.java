package sist.last.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sist.last.dto.NonBookableDto;
import sist.last.mapper.NonBookableMapperInter;

@Service
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

    @Override
    public List<String> getNonBookableCheckin(int accom_num) {
        return mapperInter.getNonBookableCheckin(accom_num);
    }

    @Override
    public List<String> getNonBookableCheckout(int accom_num) {
        return mapperInter.getNonBookableCheckout(accom_num);
    }
}
