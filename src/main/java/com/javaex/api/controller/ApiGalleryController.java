package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;

@Controller
@RequestMapping(value = "/api/gallery")
public class ApiGalleryController {

	@Autowired
	private GalleryService galleryService;
	
	
	//이미지 읽기
	@ResponseBody
	@RequestMapping(value = "/read", method = {RequestMethod.GET, RequestMethod.POST})
	public GalleryVo read(@RequestParam("no") int no) {
		System.out.println("[ApiGalleryController.read()]");
		
		GalleryVo galleryVo = galleryService.read(no);
		
		
		return galleryVo;
	}
	
	
	
	//삭제
	@ResponseBody
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public int delete(@RequestParam("no") int no) {
		System.out.println("[ApiGalleryController.delete()]");
		
		int count = galleryService.delete(no);
		System.out.println("[ApiGalleryController.delete()]");
		System.out.println(count);
		
		
		return count;
		
	}
	
}
