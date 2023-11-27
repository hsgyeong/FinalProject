package sist.last.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sist.last.dto.MemberDto;
import sist.last.service.BusinessService;
import sist.last.service.MemberService;
import sist.last.service.MemberServiceInter;

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
	public Map<String, Integer> idCheck(@RequestParam String info_id)
	{
		Map<String, Integer> map = new HashMap<>();
		
		int i = service.getSearchId(info_id);
		
		map.put("count", i);
		
		return map;
	}
	
	@GetMapping("/member/nickcheck")
	@ResponseBody
	public Map<String, Integer> nickCheck(@RequestParam String info_nickname)
	{
		Map<String, Integer> map = new HashMap<>();
		
		int a = service.getSearchNick(info_nickname);
		
		map.put("count", a);
		
		return map;
	}
	
	/*
	@PostMapping("/member/sendMessage")
	@ResponseBody
	public String sendMessage(@Valid MemberDto memberDto, Errors errors, Model model) {
		
		if(errors.hasErrors()) {
		
		model.addAttribute("memberDto", memberDto);
		
		Map<String, String> validatorResult = service.validateHandling(errors);
		for(String key : validatorResult.keySet()) {
			model.addAttribute(key, validatorResult.get(key));
		}
		return "/member/memberAddForm";
	}
		service.insertMember(memberDto);
		return "/member/welcom";
	}
	
	*/
	
	@PostMapping("/member/join-member")
	@ResponseBody
	public String insert(@Valid MemberDto memberDto, Errors errors,
			Model model,
			@RequestParam String hp1,
			@RequestParam String hp2,
			@RequestParam String hp3,
			@RequestParam String email1,
			@RequestParam String email2,
			HttpSession session)
	{
		if(errors.hasErrors()) {
			
			model.addAttribute("memberDto", memberDto);
			
			Map<String, String> validatorResult = service.validateHandling(errors);
			for(String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			
			return "/member/memberAddForm";
		}
			String hp = hp1+"-"+hp2+"-"+hp3;
			memberDto.setInfo_hp(hp);
			
			String email = email1+"@"+email2;
			memberDto.setInfo_email(email);	
		
		service.insertMember(memberDto);	

		return "/member/welcome";
	}
	
	@GetMapping("/member/member-mypage")
	public String mypage(Model model,
			HttpSession session)
	{
	/*	List<MemberDto> list = service.getAllMembers();
		
		model.addAttribute("list", list);   */
		
		String info_id = (String)session.getAttribute("info_id");
		//System.out.println(myid);
		
		MemberDto memberDto = service.getDataById(info_id);
		
		model.addAttribute("memberDto", memberDto);
		
		//System.out.println(memberDto.getId());
	
	
		//System.out.println(memberDto.getInfo_id());
		
		
		return "/member/memberMyPage";
	}
	
	@GetMapping("/member/member-update")
	public String updateform(@RequestParam String info_id, Model model)
	{	
		MemberDto memberDto = service.getDataById(info_id);
		
		model.addAttribute("memberDto", memberDto);
		
		return "/member/memberUpdateForm";
	}
	
	@PostMapping("/member/update-member")
	public String update(@ModelAttribute MemberDto dto,
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
		
		service.updateMember(dto);

		return "/member/updateSuccess";
	}
	
	
	
	@GetMapping("/member/delete-member")
	@ResponseBody
	public String delete(@RequestParam String info_id,
			HttpServletRequest request)
	{
		service.deleteMember(info_id);
		return "/";
	}
	
	@GetMapping("/member/mycoupon")
	public String coupon()
	{
		
		return "/member/myCoupon";
	}
	
	@GetMapping("/member/mypoint")
	public String point()
	{
		return "/member/myPoint";
	}
	
	@GetMapping("/notice/event")
	public String event()
	{
		return "/notice/event";
	}

}
