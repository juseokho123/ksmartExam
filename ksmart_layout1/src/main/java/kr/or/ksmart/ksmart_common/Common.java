package kr.or.ksmart.ksmart_common;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Common {

	@RequestMapping(value="/ajaxTest")
	public @ResponseBody Map<String,Object> ajaxTest(){
		return null;
	}
}
