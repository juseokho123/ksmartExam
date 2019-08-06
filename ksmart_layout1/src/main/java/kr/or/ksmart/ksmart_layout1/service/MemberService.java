package kr.or.ksmart.ksmart_layout1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart.ksmart_layout1.mapper.MemberMapper;
import kr.or.ksmart.ksmart_layout1.vo.Member;

@Service
@Transactional // sql문 처리할때 예외처리
public class MemberService {

	@Autowired MemberMapper memberMapper;
	
	public List<Member> getMemberList(){
		List<Member> list = memberMapper.getMemberList();
		return list;
	}
	public int addMember(Member member){
		int result = memberMapper.addMember(member);
		return result;
	}
	public Member getMemberById(String memberId) {
		
		return memberMapper.getMemberById(memberId);
	}
	public int modifyMember(Member member) {
		
		return memberMapper.modifyMember(member);
	}
	public int delMember(String memberId, String memberPw) {
		return memberMapper.delMember(memberId, memberPw);
	}
	//Map데이터타입으로 login메서드 호출
	public Map<String, Object> login(Member member) {
		//result 변수 선언
		String result = "";
		
		//HashMap으로 Map객체를 구현
		Map<String, Object> map = new HashMap<String, Object>();
		//Member클래스타입으로 membercheck 변수 선언하고 변수에 memberMapper에 id=login에 속한 쿼리문 실행한 결과값을 담는다
		Member membercheck = memberMapper.login(member);
		//쿼리문 결과값이 null일경우는 검색조건이었던 id, pw값이 들어오지 않아서 쿼리문 결과가 null이 되었다는 뜻이다
		if(membercheck == null) {
			//membercheck 가 null일 때 id, pw 중 id가 정확히 들어왔는지 확인하기 위해 
			//Member클래스 타입으로 memberIdcheck 변수 선언하고 변수에 memberMapper.getMememberById(member.getMemberId())를 실행한 id값을 담는다
			Member memberIdcheck = memberMapper.getMemberById(member.getMemberId());
			//memberIdcheck 가 null이란말은 login쿼리문에서 id가 들어오지 않았다는 뜻이다
			if(memberIdcheck == null) {
				result = "등록된 아이디의 정보가 없습니다";
			//else 조건은 id가 들어왔지만 pw가 달라서 쿼리문 결과가 null이 된 것이다.
			}else {
				result = "비밀번호가 일치하지 않습니다";
			}
		//membercheck 쿼리문 결과가 null이 아니면 id와 pw를 정확히 입력한 것이니까 로그인 성공
		}else {
			result = "로그인성공";
			//HashMap 으로 구현한 map 객체에 키 - "loginMember", 값 -  membercheck 형태로 쿼리문 실행결과값을 부여한다
			map.put("loginMember", membercheck);
		}
		//HashMap 으로 구현한 map 객체에 키 - "result", 값 -  result 형태로 result변수에 담긴 값들을 부여한다 
		map.put("result", result);
		
		//쿼리실행결과, 유효성검사결과값들이 map객체에 들어가있으니까 map객체를 리턴한다 
		return map;
	}
	public List<Member> sMemberList(String sk, String sv) {
		return memberMapper.sMemberList(sk, sv);
	}
}
