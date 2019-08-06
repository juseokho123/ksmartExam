package kr.or.ksmart.ksmart_layout1.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart.ksmart_layout1.service.MemberService;
import kr.or.ksmart.ksmart_layout1.vo.Member;

@Controller
public class MemberController {

	@Autowired MemberService MemberService;
	
	@GetMapping("/memberList")
	public String getMemberList(Model model) {
		List<Member> list = MemberService.getMemberList();
		model.addAttribute("memberList", list);
		return "/member/mlist/memberList";
	}
	
	@GetMapping("/addMember")
	public String addMember() {
		return "/member/mInsert/addMember";
	}
	@PostMapping("/addMember")
	public String addMember(Member member, Model model) {
		Member memberCheck = MemberService.getMemberById(member.getMemberId());
		if(memberCheck != null) {
			model.addAttribute("result", "동일한 아이디가 존재합니다");
			return "member/mInsert/addMember";
		}
		
		//System.out.println(member +"<- member kr.or.ksmart.ksmart_layout1.vo Member.java");
		MemberService.addMember(member);
		return "redirect:/memberList";
	}
	@GetMapping("/modifyMember")
	public String modifyMember(@RequestParam(value="memberId")
								String memberId, Model model) {
		model.addAttribute("member", MemberService.getMemberById(memberId));
		System.out.println(memberId + "<--memberId");
		return "/member/mUpdate/modifyMember";
	}
	@PostMapping("/modifyMember")
	public String modifyMember(Member member) {
		MemberService.modifyMember(member);
		System.out.println(member + "<--member");
		return "redirect:/memberList";
	}
	@GetMapping("/delMember")
	public String delMember(@RequestParam(value="memberId")
							String memberId,
							Model model) {
		model.addAttribute("memberId", memberId);

		System.out.println(memberId + "<--memberId");

		return "/member/mdelete/delMember";
	}
	@PostMapping("/delMember")
	public String delMember(Member member, Model model) {
		
		int result = MemberService.delMember(member.getMemberId(), member.getMemberPw());
		if(result == 0) {
			model.addAttribute("result", "비밀번호가 일치하지 않습니다");
			model.addAttribute("memberId", member.getMemberId());
			return "member/mdelete/delMember";
		}
		return "redirect:/memberList";
	}
	@GetMapping("/login")
	public String login() {
		return "/login/login";
	}
	@PostMapping("/login") //HttpSession session
	public String login(Member member, HttpSession session, Model model) {
		//화면에 입력된 아이디, 비번이 들어오는지 확인
		System.out.println(member.toString() +"<--id, pw");
		//Map클래스데이터타입으로 map변수 선언하고 MemberService클래스 내 login 메서드 호출,실행 후 리턴된 값을 할당한다
		Map<String, Object> map = MemberService.login(member);
		
		//map객체 내 result키에 해당하는 값을 String데이터타입으로 변환하여 String데이터타입으로 선언된 result 변수에 담는다 
		String result = (String)map.get("result");
		//map객체 내 loginMember키에 해당하는 값을 Member데이터 타입으로 변환하여 Member데이터타입으로 선언된 loginMember 변수에 담는다 
		Member loginMember = (Member)map.get("loginMember");
				
		//map객체 내 result 키에 해당하는 값이 로그인성공이 아니면 
		if(!map.get("result").equals("로그인성공")) {
			model.addAttribute("result", result);
			return "/login/login";
		}
		session.setAttribute("S_ID", 	loginMember.getMemberId());
		session.setAttribute("S_NAME", 	loginMember.getMemberName());
		session.setAttribute("S_LEVEL", loginMember.getMemberLevel());
		System.out.println(session.getAttribute("S_ID"));
		System.out.println(session.getAttribute("S_NAME"));
		System.out.println(session.getAttribute("S_LEVEL"));
		//로그인성공시화면 index
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	  @PostMapping("/memberList") 
	  public String memberList(String sk, String sv, Model model) { 
		  System.out.println(sk +"<--sk");
		  System.out.println(sv + "<--sv"); 
	  List<Member>list = MemberService.sMemberList(sk, sv);
	  model.addAttribute("memberList", list);
	  return "/member/mlist/memberList"; }
	 
	/*
	 * @PostMapping("/memberList") public String
	 * sMemberList(@RequestParam(value="sk") String sk ,@RequestParam(value="sv")
	 * String sv ,Model model) { List<Member> list = MemberService.sMemberList(sk,
	 * sv); model.addAttribute("memberList", list); return
	 * "/member/mlist/memberList"; }
	 */
	
}
