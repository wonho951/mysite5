package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	//필드
	//생성자
	//메소드-gs
	//메소드-일반
	
	//초반세팅 해두고 컨트롤러에서 테스트 한번 해보기
	@RequestMapping(value = "/main", method = {RequestMethod.GET, RequestMethod.POST})
	public String main() {
		System.out.println("[MainController.main()]");
		
		return "/WEB-INF/views/main/index.jsp";
	}
	
	
	
	
	
	
	
	
	
}
