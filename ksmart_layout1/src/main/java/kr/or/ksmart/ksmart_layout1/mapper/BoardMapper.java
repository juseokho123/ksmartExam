package kr.or.ksmart.ksmart_layout1.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart.ksmart_layout1.vo.Board;

@Mapper
public interface BoardMapper {

	public int addBoard(Board board);
	
	public int getBoardAllCount();
	
	public List<Board> boardList(Map<String,Integer> map);
	
	public List<Board> searchList(String sk,String sv);
	
	public Board modifyBoard(int boardNo);
	
	public int ModifyBoard(Board board);
	
	public int DelBoard(Board board);
}
