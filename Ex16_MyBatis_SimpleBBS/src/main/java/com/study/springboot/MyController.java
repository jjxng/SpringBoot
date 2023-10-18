package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.dao.ISimpleBbsDao;

import jakarta.servlet.http.HttpServletRequest;

// 껍데기만 있어서 알맹이만 넣어주는 작업만 하면 됨. 쉬워짐.
@Controller
public class MyController
{
	// MyBatis 사용을 위해 자동 주입
	@Autowired
	ISimpleBbsDao dao;		// 약한 결합으로 주입 받는다.
	
	@RequestMapping("/")
	public String root() throws Exception{
		// Mybatis : SimpleBBS
		return "redirect:list";
	}
	
	// 게시물 목록
	@RequestMapping("/list")
	public String userlistPage(Model model) {
		// dao(Mapeer)의 listDao() 메서드를 호출해서 게시물 목록 인출
		model.addAttribute("list", dao.listDao());
		return "/list";				
	}
	
	// 게시물 내용 보기
	@RequestMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		String sId = request.getParameter("id");
		model.addAttribute("dto", dao.viewDao(sId));
		return "/view";				
	}
	
	@RequestMapping("/writeForm")
	public String writeForm() {
		
		return "/writeForm";				
	}
	
	// 게시물 등록
	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		// 파라미터가 몇 개 안 될 때 다 쓰는 것이 좋음
		dao.writeDao(request.getParameter("writer"),
					 request.getParameter("title"),
					 request.getParameter("content"));
		return "redirect:list";				
	}
	
	// 게시물 삭제
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		dao.deleteDao(request.getParameter("id"));
		return "redirect:list";				
	}
}
