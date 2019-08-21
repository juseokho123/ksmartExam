package kr.or.ksmart.ksmart_layout1.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Common {

	@RequestMapping(value="/ajaxTest", produces = "application/json")
	public @ResponseBody Map<String,Object> ajaxTest(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "홍길동");
		map.put("age", "20");
		
		return map;
	}
}
