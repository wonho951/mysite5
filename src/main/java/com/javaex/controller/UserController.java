package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
public class UserController {
	//필드
	@Autowired
	private UserDao userDao;
	
	//생성자
	//메소드-g/s
	//메소드-일반
	
	
	//로그인폼
	@RequestMapping(value = "/user/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");
		
		return "user/loginForm";
	}
	
	
	//로그인
	@RequestMapping(value = "/user/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");
		System.out.println(userVo);
		
		UserVo authUser = userDao.seletUser(userVo);
		
		//로그인 성공하면(authUser가 null이 아니면)
		if(authUser != null) {
			System.out.println("[로그인성공]");
			session.setAttribute("authUser", authUser);
			
			return "redirect:/main";
		} else {	//로그인 성공못하면(authUser가 null이면)
			System.out.println("[로그인 실패]");
			return "redirect:/user/loginForm?result=fail";
		}
		//if문에서 리턴값 왔기 때문에 리턴 올수가 없음
		
	}
}
