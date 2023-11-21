package sist.last.service;

import java.util.List;
import sist.last.dto.AccomDto;

public interface ProductServiceInter {
    public int getAccommodation(String result);

    public List<AccomDto> getProductData(String keyword);

    public List<String> selectCategory();
}
