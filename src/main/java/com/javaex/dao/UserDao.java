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
	
}
