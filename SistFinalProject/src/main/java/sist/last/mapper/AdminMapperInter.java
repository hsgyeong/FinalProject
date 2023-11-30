package sist.last.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import sist.last.dto.AccomDto;

@Mapper
public interface AdminMapperInter {

	public int adminloginPassCheck(Map<String, String> map);
	public void adminApprovalOfAccom(int accom_num);
	public List<AccomDto> getAccomPagingList(Map<String, Integer> map);
	public int getTotalCount();
}
