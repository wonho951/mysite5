package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		
		return guestbookList;
	}
	
	
	
	//ajax 방명록 저장
	@ResponseBody
	@RequestMapping(value = "/write", method = {RequestMethod.GET, RequestMethod.POST})
	public GuestbookVo write(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("[ApiGuestbookController.write()]");
		System.out.println(guestbookVo);
		
		GuestbookVo resultVo = guestService.writeResultVo(guestbookVo);	//새로 dao만든거임. -> xml에서 insert문 달라지기 때문에
		
		return resultVo;
	}
	
	
	//ajax 방명록 삭제
	@RequestMapping(value = "/remove", method = {RequestMethod.GET, RequestMethod.POST})
	public int remove(@ModelAttribute GuestbookVo guestbookVo) {
		//System.out.println("[ApiGuestbookController.remove()]");
		
		//System.out.println(guestbookVo);
		
		int count = guestService.delete(guestbookVo);
		
		
		
		
		return count;
	}
	
	
	
}
