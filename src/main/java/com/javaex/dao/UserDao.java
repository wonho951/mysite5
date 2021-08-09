package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	//필드
	@Autowired
	private SqlSession sqlSession;
	//생성자
	//메소드-g/s
	//메소드-일반
	
	
	//로그인
	public UserVo seletUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");
		//System.out.println(userVo);
		
		//UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);
		//System.out.println("authUser " + authUser);
		
		//authUser처럼 변수 안만들고 바로 리턴주는게 보통임
		return sqlSession.selectOne("user.selectUser", userVo);
	}
	
	
	//회원가입
	public int insert(UserVo userVo) {
		System.out.println("[UserDao.join()]");
		System.out.println(userVo);
		
		return sqlSession.insert("user.insert", userVo);
	}
	
	
	//회원정보 수정폼(no만 가지고 정보 가져오기)
	public UserVo selectUser(int no) {
		System.out.println("[UserDao.정보수정(no만가져오기)()]");
		System.out.println(no);
		
		return sqlSession.selectOne("user.selectOne", no);
	}
	
	
	//회원정보 수정
	public int modify(UserVo userVo) {
		System.out.println("[UserDao.회원정보수정()]");
		
		return sqlSession.update("user.update", userVo);
	}
	
	/*
	//아이디 중복체크
	public int idCheck(UserVo userVo) {
		System.out.println("[UserDao.idCheck()]");
		System.out.println(userVo);
		
		
		int count = sqlSession.selectOne("user.idcheck", userVo);
		
		return count;
		
	}*/
	
	
	/* 아이디 중복체크(ajax) */
	//회원정보 가져오기 -> 아이디 체크
	public UserVo selectUser(String id) {
		System.out.println("[UserDao.selectUser()]");
		System.out.println(id);
		
		//UserVo userVo = 
		//System.out.println(userVo);
		
		
		return sqlSession.selectOne("user.selectUserById", id);
	}
}
