package kr.or.ksmart.ksmart_layout1.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ksmart.ksmart_layout1.service.GoodsService;
import kr.or.ksmart.ksmart_layout1.service.MemberService;
import kr.or.ksmart.ksmart_layout1.vo.Goods;
import kr.or.ksmart.ksmart_layout1.vo.Member;

@Controller
public class GoodsController {

	@Autowired GoodsService GoodsService;
	@GetMapping("/goodsList")
	public String getGoodsList(Model model) {
		List<Goods> list = GoodsService.getGoodsList();
		model.addAttribute("goodsList", list);
		return "/goods/glist/goodsList";
	}
	@GetMapping("/addGoods")
	public String addGoods() {
		return "/goods/ginsert/addGoods";
	}
	@PostMapping("/goodsList")
	public String sGoodslist(@RequestParam(value="sk") String sk 
							,@RequestParam(value="sv") String sv
							,@RequestParam(value="firstMoney") String firstMoney
							,@RequestParam(value="lastMoney") String lastMoney
							, Model model) {
		System.out.println(sk +"<-- sk");
		System.out.println(sv +"<-- sv");
		List<Goods> list = GoodsService.sGoodslist(sk, sv, firstMoney, lastMoney);
		System.out.println(list.toString()+"<--list.toString()");
		model.addAttribute("goodsList", list);
		return "/goods/glist/goodsList";
	}
	@PostMapping("/addGoods")
	public String addGoods(Goods goods, HttpSession session) {
		System.out.println(goods.toString() + "<-- goods.toString");
		GoodsService.addGoods(goods, session);
		return "redirect:/goodsList";
	}
	/*
	 * @PostMapping("/addGoods") 
	 * public String addGoods(Goods goods, HttpSession session) {
	 * System.out.println(goods.toString() + "<-- goods.toString");
	 * GoodsService.addGoods1(goods, session); 
	 * return "redirect:/goodsList"; }
	 */
	@GetMapping("/modifyGoods")
	public String modifyGoods(@RequestParam(value="goodsCode") String goodsCode, Model model) {
		System.out.println(goodsCode + "<-- goodsCode");
		Goods goods = GoodsService.getGoodsByCode(goodsCode);
		model.addAttribute("goods", goods);
		return "/goods/gUpdate/modifyGoods";
	}
	@PostMapping("/modifyGoods")
	public String modifyGoods(Goods goods) {
		System.out.println(goods.toString() + "<--goods.toString()");
		GoodsService.modifyGoods(goods);
		return "redirect:/goodsList";
	}
	@GetMapping("/delGoods")
	public String delGoods(@RequestParam(value="goodsCode") String goodsCode
							,Model model) {
		model.addAttribute("goodsCode", goodsCode);

		//System.out.println(goodsCode.toString() + "<-- goodsCode.toString()");
		return "/goods/gdelete/delGoods";
	}
	
	@PostMapping("/delGoods")
	public String delGoods(@RequestParam(value="goodsCode") String goodsCode
							,@RequestParam(value="memberId") String memberId
							,@RequestParam(value="memberPw") String memberPw
							,Model model) {
		System.out.println(goodsCode.toString() + "<-- goodsCode.toString()");
		System.out.println(memberId.toString() + "<-- memberId.toString()");
		System.out.println(memberPw.toString() + "<-- memberPw.toString()");
		int result = GoodsService.delGoods(goodsCode, memberId, memberPw);
			if(result == 0) {
				model.addAttribute("result", "비밀번호 불 일치");
				model.addAttribute("goodsCode", goodsCode);
				return "/goods/gdelete/delGoods";
			}
		return "redirect:/goodsList";
	}
	
}
