package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/")
    public String root() throws Exception{
        return "storage";
    }

	@RequestMapping("/1")
	public String firestore() {

		return "storage";
	}
	
}
