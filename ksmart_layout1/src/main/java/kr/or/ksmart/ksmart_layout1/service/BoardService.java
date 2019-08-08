package kr.or.ksmart.ksmart_layout1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart.ksmart_layout1.mapper.BoardMapper;
import kr.or.ksmart.ksmart_layout1.vo.Board;

@Service
@Transactional
public class BoardService {

	@Autowired
	private BoardMapper boardmapper;
	
	public int addBoard(Board board) {
		
		return boardmapper.addBoard(board);
	}
	public Map<String, Object> boardList(int currentPage) {
		//페이지 구성 할 행의 갯수
		//페이지를 구성 할 때 한페이지 몇개의 목록들이 나오게하는지 설정
		final int Row_PER_PAGE=10;
		
		//보여줄 첫번쨰 페이지번호 초기화
		int startPageNum=1;
		
		//보여줄 페이지 번호의 갯수 초기화
		int lastPageNum = Row_PER_PAGE;
		
		//6번쨰 페이지 클릭시 
		//10/2 -> 5니까 5 보다 클때만 움직인다.
		if(currentPage > (Row_PER_PAGE/2)) {
			startPageNum = currentPage - ((lastPageNum/2)-1);
			lastPageNum += (startPageNum-1);
		}
		//limit 적용될 값 startRow, 상수 Row_PER_PAGE
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int startRow = (currentPage-1)*Row_PER_PAGE;
		
		//mybits limit에 넣어질 값
		map.put("startRow", startRow);
		map.put("rowPerPage", Row_PER_PAGE);
		
		//전체행의 갯수를 가져오는 쿼리
		double boardCount = boardmapper.getBoardAllCount();
		
		int lastPage =(int)(Math.ceil(boardCount/Row_PER_PAGE));
		
		if(currentPage >= (lastPage-4)) {
			lastPageNum = lastPage;
		}
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", boardmapper.boardList(map));
		resultMap.put("currentPage", currentPage);
		resultMap.put("lastPage", lastPage);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("lastPageNum", lastPageNum);
		
		return resultMap;
	}

	public List<Board> searchList(String sk,String sv){
		return boardmapper.searchList(sk, sv);
	}
}
