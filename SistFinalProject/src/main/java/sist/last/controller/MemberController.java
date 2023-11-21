package sist.last.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sist.last.dto.MemberDto;
import sist.last.service.BusinessService;
import sist.last.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService service;
	
	@GetMapping("/member/joinform")
	public String addform()
	{
		return "/member/addFormMain";
	}
	
	@GetMapping("/member/member-join")
	public String addperson()
	{
		return "/member/memberAddForm";
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
	
	@GetMapping("/member/nickcheck")
	@ResponseBody
	public Map<String, Integer> nickCheck(@RequestParam String nickname)
	{
		Map<String, Integer> map = new HashMap<>();
		
		int a = service.getSearchNick(nickname);
		
		map.put("count", a);
		
		return map;
	}
	
	
	@PostMapping("/member/join-member")
	public String insert(@ModelAttribute MemberDto dto,
			@RequestParam String hp1,
			@RequestParam String hp2,
			@RequestParam String hp3,
			@RequestParam String email1,
			@RequestParam String email2,
			HttpSession session)
	{
		String hp = hp1+"-"+hp2+"-"+hp3;
		dto.setInfo_hp(hp);
		
		String email = email1+"@"+email2;
		dto.setInfo_email(email);
		
		service.insertMember(dto);	

		return "/member/welcome";
	}
	
	@GetMapping("/member/member-mypage")
	public String mypage(Model model,
			HttpSession session)
	{
	/*	List<MemberDto> list = service.getAllMembers();
		
		model.addAttribute("list", list);   */
		
		String myid = (String)session.getAttribute("myid");
		//System.out.println(myid);
		
		MemberDto memberDto = service.getDataById(myid);
		
		model.addAttribute("memberDto", memberDto);
		
		//System.out.println(memberDto.getId());
	
	
		//System.out.println(memberDto.getInfo_id());
		
		
		return "/member/memberMyPage";
	}

}
