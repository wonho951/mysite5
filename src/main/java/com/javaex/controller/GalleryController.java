package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/gallery")
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	
	//갤러리 리스트
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("[GalleryController.list]");
		
		//List<GalleryVo> galleryList = galleryService.list();
		
		return "/gallery/list";
	}
	
	
	//이미지 올리기
	@RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
	public String upload(@RequestParam("file") MultipartFile file, @ModelAttribute GalleryVo galleryVo, HttpSession session) {
		System.out.println("[GalleryController.upload]");
		
		System.out.println("실제 파일명 : " + file.getOriginalFilename());
		System.out.println("파일 사이즈 : " + file.getSize());
		
		//세션에서 유저 no 가져와서 담기
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int user_no = authUser.getNo();
		
		System.out.println(user_no);
		galleryVo.setUser_no(user_no);
		
		
		galleryService.upload(file, galleryVo);
		
		return "redirect:/gallery/list";
	}
	
}
