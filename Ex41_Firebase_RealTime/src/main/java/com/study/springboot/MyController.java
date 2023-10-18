package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping("/")
    public String root() throws Exception{
        return "realtime";
    }

	@RequestMapping("/1")
	public String welcome1() {

		return "realtime";
	}
	
}
