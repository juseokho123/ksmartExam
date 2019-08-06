/*
 * package kr.or.ksmart.ksmart_layout1.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestParam;
 * 
 * import kr.or.ksmart.ksmart_layout1.service.MemberService; import
 * kr.or.ksmart.ksmart_layout1.vo.Member;
 * 
 * @Controller public class MemberController2 {
 * 
 * @Autowired MemberService MemberService;
 * 
 * @GetMapping("/memberList") public String getMemberList(Model model) {
 * List<Member> list = MemberService.getMemberList();
 * model.addAttribute("memberList", list); return "/member/mlist/memberList"; }
 * 
 * @GetMapping("/addMember") public String addMember() { return
 * "/member/mInsert/addMember"; }
 * 
 * @PostMapping("/addMember") public String addMember(Member member) {
 * MemberService.addMember(member); System.out.println(member
 * +"<- member kr.or.ksmart.ksmart_layout1.vo Member.java"); return
 * "redirect:/memberList"; }
 * 
 * @GetMapping("/modifyMember") public String
 * modifyMember(@RequestParam(value="memberId") String memberId, Model model) {
 * model.addAttribute("member", MemberService.getMemberById(memberId));
 * System.out.println(memberId + "<--memberId"); return
 * "/member/mUpdate/modifyMember"; }
 * 
 * @PostMapping("/modifyMember") public String modifyMember(Member member) {
 * MemberService.modifyMember(member); System.out.println(member + "<--member");
 * return "redirect:/memberList"; }
 * 
 * @GetMapping("/delMember") public String
 * delMember(@RequestParam(value="memberId") String memberId, Model model) {
 * model.addAttribute("memberId", memberId);
 * 
 * System.out.println(memberId + "<--memberId");
 * 
 * return "/member/mdelete/delMember"; }
 * 
 * @PostMapping("/delMember") public String delMember(Member member, Model
 * model) {
 * 
 * int result = MemberService.delMember(member.getMemberId(),
 * member.getMemberPw()); if(result == 0) { model.addAttribute("result",
 * "비밀번호가 일치하지 않습니다"); model.addAttribute("memberId", member.getMemberId());
 * return "member/mdelete/delMember"; } return "redirect:/memberList"; } }
 */