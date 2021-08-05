package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {

	
	//갤러리 리스트
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list() {
		System.out.println("[GalleryController.list]");
		
		
		return "/gallery/list";
	}
	
}
