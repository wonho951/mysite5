package com.javaex.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.GalleryService;

@Controller
@RequestMapping(value = "api/gallery")
public class ApiGalleryController {

	@Autowired
	private GalleryService galleryService;
	
	
	
	
	
}
