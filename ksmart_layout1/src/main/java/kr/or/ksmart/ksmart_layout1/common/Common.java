package kr.or.ksmart.ksmart_layout1.common;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import kr.or.ksmart.ksmart_layout1.service.BoardService;

@Controller
public class Common {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/ajaxTest", produces = "application/json")
	//디폴트값 1, 값이 안들어와도 된다.
	public @ResponseBody Map<String,Object> ajaxTest(
			@RequestParam(value="currentPage",
			required = false, 
			defaultValue = "1")int currentPage){
		
		/*
		 * map<String,Object> map = boardService.addBoard(board); 
		 * return map;
		 */
		
		return boardService.boardList(currentPage);
	}
	
	@RequestMapping("/ajaxCall")
	public String ajaxCall() {
		
		return "ajax/ajaxCall";
	}
}
