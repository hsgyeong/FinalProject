package sist.last.service;

import sist.last.dto.ReserveDto;

public interface ReserveServiceInter {

	public void reservingInsert(ReserveDto dto);
	public ReserveDto getOneDataByIdOfReserve(String info_id,int idx);
	public int getMaxIdxOfReserve();
	public ReserveDto getOneInfoData(String info_id);
	public ReserveDto getReservationDataById(String reserve_id);
}
