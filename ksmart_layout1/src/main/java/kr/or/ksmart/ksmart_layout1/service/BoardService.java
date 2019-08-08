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
		//다른 조건이 없을 경우 기본적으로 조회할때 1이 나오게 한다.
		int startPageNum=1;
		
		//보여줄 페이지 번호의 갯수 초기화
		//다른 조건이 없을 경우 기본적으로 조회할떄 10이 나오게 한다.
		int lastPageNum = Row_PER_PAGE;
		
		//6번쨰 페이지 클릭시 
		//currentPage가 >5보다 클경우
		//startPageNum currentPage가 6이고 - lastPageNum 5-1해서 2가  startPageNum에 담긴다
		//lastPageNum 에는 startPageNum에 담겨던 값 -1 값이 lastPageNum에 더해진다.
		if(currentPage > (Row_PER_PAGE/2)) {
			startPageNum = currentPage - ((lastPageNum/2)-1);
			lastPageNum += (startPageNum-1);
		}
		//limit 적용될 값 startRow, 상수 Row_PER_PAGE
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//화면에 보여지는 목록의 처음 행의 값
		int startRow = (currentPage-1)*Row_PER_PAGE;
		
		//mybits limit에 넣어질 값
		//mapper를 거쳐 mapper.xml에서 넣어질 값을 map에 담겨진다.
		map.put("startRow", startRow);
		map.put("rowPerPage", Row_PER_PAGE);
		
		//전체행의 갯수를 가져오는 쿼리
		//int가 아닌 double에 담아서 가져온다.(이유: int는 소수점을 버려서 )
		double boardCount = boardmapper.getBoardAllCount();
		
		//double 선언한 boardCount 값을 10으로 나뉘어서 나온값을 올림을 하고
		//나온 값을 int형으로 바뀌어서 lastPage에다가 담는다.
		int lastPage =(int)(Math.ceil(boardCount/Row_PER_PAGE));
		
		//curttentPage가 lastPage에서 - 4 하는 것보다  크거나 같다면
		//lastPageNum의 값을 lastPage에다가  담는다.
		if(currentPage >= (lastPage-4)) {
			lastPageNum = lastPage;
		}
		
		//필요한 값들을 넘겨주기위해 map.put을 이용해서 key에는 담겨질 이름
		//value에다가는 담겨질 값을 넣어주면된다.
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", boardmapper.boardList(map));
		resultMap.put("currentPage", currentPage);
		resultMap.put("lastPage", lastPage);
		resultMap.put("startPageNum", startPageNum);
		resultMap.put("lastPageNum", lastPageNum);
		
		//map에 담겨져 있는 값을 리턴값에 넣어 리턴 시킨다.
		return resultMap;
	}

	public List<Board> searchList(String sk,String sv){
		return boardmapper.searchList(sk, sv);
	}
	
	public Board modifyBoard(int boardNo) {
		return boardmapper.modifyBoard(boardNo);
	}
	public int ModifyBoard(Board board) {
		return boardmapper.ModifyBoard(board);
	}
	public int DelBoard(Board board) {
		return boardmapper.DelBoard(board);
	}
}
