package sist.last.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapperInter {

	public int AdminloginPassCheck(Map<String, String> map);
}
