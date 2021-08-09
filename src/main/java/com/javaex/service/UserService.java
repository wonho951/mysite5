package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service	//안쓰면 atuowired 안됨.
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	//로그인 (사용자 정보 가져오기)
	public UserVo getUser(UserVo uesrVo) {
		System.out.println("[UserService.getUser()]");
		
		UserVo authUser = userDao.seletUser(uesrVo);
		
		return authUser;
	}
	
	
	//회원가입
	public int join(UserVo userVo) {
		System.out.println("[UserService.join()]");
		
		int count = userDao.insert(userVo);
		System.out.println(count);
		
		return count;
	}
	
	
	
	//회원정보 수정폼
	public UserVo modifyForm(int no) {
		System.out.println("[UserService.modifyForm()]");
		
		return userDao.selectUser(no);
	}
	
	
	
	//회원정보 수정
	public int modify(UserVo userVo) {
		System.out.println("[UserService.modify()]");
		
		return userDao.modify(userVo);
	}
	
	/*
	//아이디중복체크
	public int idCheck(UserVo userVo) {
		System.out.println("[UserService.idCheck()]");
		System.out.println(userVo);
		
		int count = userDao.idCheck(userVo);
		
		return count;
	}*/
	
	
	/* 아이디 중복체크(ajax) */
}
