package kr.or.ksmart.ksmart_layout1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart.ksmart_layout1.service.BoardService;
import kr.or.ksmart.ksmart_layout1.vo.Board;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardservice;
	
	@GetMapping("/addBoard")
	public String addBoard() {
		
		return "board/bInsert/addBoard";
	}
	@PostMapping("/addBoard")
	public String addBoard(Board board) {
		System.out.println(board.toString() +"<-boardController addBoard");
		
		boardservice.addBoard(board);
		
		return "redirect:/bList";
	}
	@GetMapping("/bList")
	public String boardList(Model model,Board board
							,@RequestParam(value="currentPage",required = false, defaultValue = "1")int currentPage) {
		
		Map<String,Object> map = boardservice.boardList(currentPage);
		
		model.addAttribute("boardList",map.get("list"));
		model.addAttribute("currentPage",map.get("currentPage"));
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("startPageNum",map.get("startPageNum"));
		model.addAttribute("lastPageNum",map.get("lastPageNum"));
		
		return "board/bList/bList";
	}
	@PostMapping("/searchList")
	public String searchList(@RequestParam(value="sk")String sk
							,@RequestParam(value="sv")String sv
							,Model model) {
		System.out.println(sk);
		System.out.println(sv);
		List<Board> serach = boardservice.searchList(sk, sv);
		model.addAttribute("boardList",serach);
		return "/board/bList/bList";
	}
	
}
