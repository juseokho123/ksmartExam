package kr.or.ksmart.ksmart_layout1.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ksmart.ksmart_layout1.mapper.GoodsMapper;
import kr.or.ksmart.ksmart_layout1.mapper.MemberMapper;
import kr.or.ksmart.ksmart_layout1.vo.Goods;
import kr.or.ksmart.ksmart_layout1.vo.Member;

@Service
@Transactional //a(원자성),c(일관성),i(고립성),d(영속성)
public class GoodsService {
	@Autowired GoodsMapper goodsMapper;
	@Autowired MemberMapper memberMapper;

	public List<Goods> getGoodsList(){
		List<Goods> list = goodsMapper.getGoodsList();
		return list;
	}
	
	public List<Goods> sGoodslist(String sk, String sv, String firstMoney, String lastMoney){
		return goodsMapper.sGoodslist(sk, sv, firstMoney, lastMoney);
	}
	
	public int addGoods(Goods goods, HttpSession session) {
		int max = goodsMapper.getGoodsCodeMax()+1;
		String tempCode = "goods_";
		goods.setMemberId((String)session.getAttribute("S_ID"));
		goods.setGoodsCode(tempCode+max);
		return goodsMapper.addGoods(goods);
	}
	
	public int addGoods1(Goods goods, HttpSession session) {
		goods.setMemberId((String)session.getAttribute("S_ID"));
		return goodsMapper.addGoods1(goods);
	}
	
	public Goods getGoodsByCode(String goodsCode) {
		return goodsMapper.getGoodsByCode(goodsCode) ;
	}
	
	public int modifyGoods(Goods goods) {
		return goodsMapper.modifyGoods(goods);
	}
	
	public int delGoods(String goodsCode, String memberId, String memberPw) {

		return goodsMapper.delGoods(goodsCode, memberId, memberPw);
	}
}
