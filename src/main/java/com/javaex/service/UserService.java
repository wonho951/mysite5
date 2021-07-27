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
	
}
