package sist.last.service;

import java.util.List;
import java.util.Map;

import sist.last.dto.AccomDto;

public interface AdminMapperServiceInter {

	public int adminloginPassCheck(String admin_id,String admin_pass);
	public void adminApprovalOfAccom(int accom_num);
	public List<AccomDto> getAccomPagingList(int start, int perpage);
	public int getTotalCount();
}
