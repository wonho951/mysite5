package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/api/guestbook")
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestService;
	
	
	//ajax방식으로 리스트 가져오기
	@ResponseBody	//리턴에 보낸 리턴값을 ResponseBody에 보내라
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public List<GuestbookVo> List() {
		System.out.println("[ApiGuestbookController.list()]");
		
		List<GuestbookVo> guestbookList= guestService.list();
		
		System.out.println(guestbookList);
		
		return guestbookList;	//포워드 다이렉트가아니고 게북리스트 데이터를 위에 리스폰스바디에 데이터만 보낸다.

	}
	
	
	/*
	//ajax 방명록 저장
	@ResponseBody	  //기존 포워드 방식으로 하지말고 밑에 resultVo같은 데이터를 리스폰스바디에 보내주라는 뜻이다.
	@RequestMapping(value = "/write", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.write()]");
		System.out.println(guestbookVo);
		
		GuestbookVo resultVo = guestService.writeResultVo(guestbookVo);	//새로 dao만든거임. -> xml에서 insert문 달라지기 때문에
		
		return resultVo;	//제이슨으로 넘겨주는 방법임   리스폰스body에 넣어서 보내라

	}*/
	
	
	//ajax 방명록 삭제
	@ResponseBody
	@RequestMapping(value = "/remove", method = {RequestMethod.GET, RequestMethod.POST})
	public int remove(@ModelAttribute GuestbookVo guestbookVo) {
		//System.out.println("[ApiGuestbookController.remove()]");
		
		//System.out.println(guestbookVo);
		
		int count = guestService.delete(guestbookVo);
		
		
		
		
		return count;
	}
	
	
	
	//안드로이드 방명록 글 1개 가져오기
	@ResponseBody
	@RequestMapping(value = "")
	public String read() {
		System.out.println("[ApiGuestbookController.read()]");
		
		return null;
	}
	
	
	
	
	
	
	/***************************************************/
	/*
	//ajax방식으로 리스트 가져오기
	@ResponseBody	//리턴에 보낸 리턴값을 ResponseBody에 보내라
	@RequestMapping(value = "/list2", method = {RequestMethod.GET, RequestMethod.POST})
	public List<GuestbookVo> List2() {
		System.out.println("[방명록 리스트]");
		
		List<GuestbookVo> guestbookList= guestService.list();
		
		System.out.println(guestbookList);
		
		return guestbookList;
	}*/
	
	
	
	//ajax 방명록 저장 -> 안드로이드 수업 사용
	@ResponseBody
	@RequestMapping(value = "/write2", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestbookVo write2(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("[방명록 저장]");
		System.out.println(guestbookVo);
		
		GuestbookVo resultVo = guestService.writeResultVo(guestbookVo);	//새로 dao만든거임. -> xml에서 insert문 달라지기 때문에
		
		return resultVo;
	}
	
	/*
	//ajax 방명록 삭제
	@ResponseBody
	@RequestMapping(value = "/remove2", method = {RequestMethod.GET, RequestMethod.POST})
	public int remove2(@ModelAttribute GuestbookVo guestbookVo) {
		//System.out.println("[ApiGuestbookController.remove()]");
		
		//System.out.println(guestbookVo);
		
		int count = guestService.delete(guestbookVo);
		
		
		
		
		return count;
	}*/
}