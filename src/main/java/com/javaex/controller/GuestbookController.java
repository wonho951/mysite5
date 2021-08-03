package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value="/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestService; 
	
	
	//방명록 리스트
	@RequestMapping(value = "/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		//System.out.println("컨트롤러 리스트");
		
		List<GuestbookVo> guestList = guestService.list();
		
		model.addAttribute("guestList", guestList);
		//System.out.println("리스트 :" + guestList.toString());
		
		return "guestbook/addList";
	}
	
	
	//등록
	@RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("컨트롤러 등록");
		
		guestService.insert(guestbookVo);
		
		return "redirect:/guestbook/addList";
	}
	

	
	//삭제폼
	@RequestMapping(value = "/dform", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@RequestParam("no") int no) {
		System.out.println("컨트롤러 deleteForm");
		
		return "guestbook/deleteForm";
	}
	
	
	/* 
	//삭제폼2
	@RequestMapping(value = "/dform", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("컨트롤러 deleteForm");
		
		return "guestbook/deleteForm";
	}*/
	
	
	
	//삭제
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("컨트롤러 delete");
		
		int count = guestService.delete(guestbookVo);
		
		if(count == 1) {
			return "redirect:/guestbook/addList";
		} else {
			//비밀번호 틀리면 비밀번호 틀렸다고 다시 dfrom으로 보내버림. 세상에 마상에  =  이거 확인좀 잘해라 제발!!!!
			return "redirect:/guestbook/dform?result=fail&no=" + guestbookVo.getNo();
		}
		
	}
	
	
	/*
	//삭제2
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		System.out.println("컨트롤러 delete");
		
		//파라미터 하나씩만 꺼내와서 map써볼거임
		int count = guestService.delete(no, password);
		
		if (count == 1) {
			return "redirect:/guestbook/addList";
		} else {
			//다시 삭제폼으로 보내버림
			return "redirect:/guestbook/dform?no=" + no + "&result=fail";
		}
		
		
	}*/
	
	
	
	//2021.08.03 ajax방식
	//ajax 방명록 메인페이지
	@RequestMapping(value = "/ajaxMain", method = {RequestMethod.GET, RequestMethod.POST})
	public String ajaxMain() {
		System.out.println("[controller.ajaxMain()]");
		
		
		return "guestbook/ajaxList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
