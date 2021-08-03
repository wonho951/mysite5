package com.javaex.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApiGuestbookController {

	//ajax방식으로 리스트 가져오기
	@RequestMapping(value = "/api/guestbook/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String List() {
		System.out.println("[ApiGuestbookController.list()]");
		
		return "";
	}
}
