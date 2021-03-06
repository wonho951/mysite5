package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	//필드
	@Autowired
	private UserService userService;
	
	//생성자
	//메소드-g/s
	//메소드-일반
	
	
	//로그인폼
	@RequestMapping(value = "/loginForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");
		
		return "user/loginForm";
	}
	
	
	//로그인
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");
		System.out.println(userVo);
		
		//dao사용할때
		//UserVo authUser = userDao.seletUser(userVo);
		
		//dao로 바로 연결이 아닌 service 사용할때 --> 이렇게 사용해야함. dao사용 x
		UserVo authUser = userService.getUser(userVo);
		//System.out.println("로그인 정보:" + authUser.toString());
		
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
	
	
	
	//회원가입폼
	@RequestMapping(value = "/joinForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("[UserController.joinForm()]");
		
		return "user/joinForm";
	}
	
	
	//회원가입 
	@RequestMapping(value = "/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.join]");
		System.out.println("join : " + userVo);
		
		userService.join(userVo);
		
		return "user/joinOk";
	}
	
	
	//회원정보 수정폼
	@RequestMapping(value = "/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("[UserController.modifyForm()]");
		
		int no = ((UserVo)session.getAttribute("authUser")).getNo();
		
		UserVo userVo = userService.modifyForm(no);
		//System.out.println(userVo);
		
		model.addAttribute("userVo",userVo);
		System.out.println(model.toString());
		return "user/modifyForm";
		
	}
	
	
	//http://localhost:8088/mysite5/user/modify?password=11&name=원호&gender=femal
	//회원정보 수정
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.modify()]");
		
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		System.out.println("authUser정보: "+authUser.toString());
		//authUser = userService.modify(userVo);
		
		userVo.setNo(authUser.getNo());
		System.out.println("userVo: " + userVo);
		
		
		userService.modify(userVo);
		
		userVo.setName(authUser.getName());
		System.out.println("userVo: " + userVo);	
		
		//리다이렉트
		return "redirect:/main";	
	}
	
	
	//로그아웃
	@RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("[UserController.logout]");
		System.out.println(session);
		
		//authUser 정보 삭제
		session.removeAttribute("authUser");
		//메모리 지움
		session.invalidate();
		
		//리다이렉트
		return "redirect:/main";
	}

	
	/*
	//아이디 중복체크
	@RequestMapping(value = "user/idcheck", method = {RequestMethod.GET, RequestMethod.POST})
	public int idCheck(UserVo userVo) {
		System.out.println("[UserController.idCheck]");
		System.out.println(userVo);
		
		int count = userService.idCheck(userVo);
		System.out.println("[UserController.idCheck]");
		System.out.println(userVo);
		
		
		return count;
		
	}*/
	
	
	/* 아이디 중복체크(ajax) - 선생님 */
	@ResponseBody
	@RequestMapping(value = "/idcheck", method = {RequestMethod.GET, RequestMethod.POST})
	public boolean idCheck(@RequestParam("id") String id) {
		System.out.println("[UserController.idCheck()]");
		System.out.println(id);
		
		boolean state = userService.getUser(id);
		System.out.println("[UserController.idCheck]");
		System.out.println(id);
		
		return state;
	}
	
	/* json방식으로 데이터 받기 */
	@ResponseBody
	@RequestMapping(value = "/join2", method = {RequestMethod.GET, RequestMethod.POST})
	public int join2(@RequestBody UserVo userVo) {
		System.out.println("[UserController.join2()]");
		System.out.println(userVo);
		
		
		//서비스를통해 회원정보 저장
		int count = userService.join(userVo);
		
		return count;
	}
}
