package sist.last.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sist.last.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	
	@GetMapping("/member/joinform")
	public String addform()
	{
		return "/member/addForm";
	}
	
	@GetMapping("/member/idcheck")
	@ResponseBody
	public Map<String, Integer> idCheck(@RequestParam String id)
	{
		Map<String, Integer> map = new HashMap<>();
		
		int i = service.getSearchId(id);
		
		map.put("count", i);
		
		return map;
	}

}
