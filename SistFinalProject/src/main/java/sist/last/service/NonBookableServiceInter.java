package sist.last.service;

import java.util.List;
import sist.last.dto.NonBookableDto;

public interface NonBookableServiceInter {
    public List<String> getAccomName(String business_id);

    public String getAccomNumber(String accom_name);

    public void addNonBookableDate(NonBookableDto dto);
}
