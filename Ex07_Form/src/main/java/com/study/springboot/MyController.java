package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MyController
{
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Form 데이터 전달받아 사용하기";
	}
	
	// 서블릿처럼 request내장객체를 통해 폼값을 받아 처리한다.
	@RequestMapping("/test1")
	public String test1(HttpServletRequest httpServletRequest, Model model) {
		// getParameter()메서드를 통해 폼값을 받앙 변수에 저장한다.
		String id = httpServletRequest.getParameter("id");
		String name = httpServletRequest.getParameter("name");
		
		// View로 전달할 데이터를 Model객체에 저장한다.
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		
		return "test1";				
	}
	
	// 어노테이션을 통해 파라미터를 받은 후 변수에 저장한다.
	@RequestMapping("/test2")
	public String test2(@RequestParam("id") String id,
							  @RequestParam("name") String name,
							  Model model) 
	{
		/*
		 	형식 : @RequestMapping(전송된 파라미터명) String 메서드에서
		 		사용할 변수명
		*/
		// Model객체에 데이터를 저장한 후 View의 경로를 반환한다.
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		
		return "test2";
	}
	
	/*
	 	커멘드 객체를 통해 파라미터를 한꺼번에 받아 저장하고,
	 	Model객체에 저장까지 해준다. 파라미터를 저장한 DTO를 통해
	 	출력해야 하므로 View에서는 getter를 사용한다.
	*/
	@RequestMapping("/test3")
	public String test2(Member member, Model model) 
	{
		/*
		 	커멘드객체가 모든 작업을 처리하므로 폼값을 받는 부분과 
		 	Model객체에 저장하는 부분에 대한 코드는 기술할 필요 없다.
		*/
		// 파라미터와 일치하는 빈을 만들어서 사용할 수 있다.
		// View 페이지에서 model을 사용하지 않고 member를 사용한다.
		return "test3";
	}
	
	/*
	  	@PathVariable 어노테이션으로 경로형태로 전달되는 값을
	  	경로로 사용한다. 단, 파라미터를 경로명으로 인식하므로
	    정적리소스(이미지, js파일 등) 사용 시 경로설정에 주의
	 */
	// 패스 자체에 변수를 넣어 줄 수도 있다.
	@RequestMapping("/test4/{studentId}/{name}")
	public String getStudent(@PathVariable String studentId,
							 @PathVariable String name,
							 Model model) 
	{
		model.addAttribute("id", studentId);
		model.addAttribute("name", name);
		return "test4";
	}
	
	
}
