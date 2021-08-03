package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;

	
	//리스트
	public List<GuestbookVo> getGuestList(){
		//System.out.println("dao 리스트");
		
		List<GuestbookVo> getGuestList = sqlSession.selectList("guestbook.guestList");
		
		return getGuestList;
	}
	
	
	
	//등록
	public int insert(GuestbookVo guestbookVo) {
		System.out.println("dao 등록");
		
		return sqlSession.insert("guestbook.guestInsert", guestbookVo);
	}
	
	
	//방명록 글 저장
	public int insertGuestbookKey(GuestbookVo guestbookVo) {
		System.out.println("[guestbookDao.insertGuestbookKey]");
		
		return sqlSession.insert("guestbook.insertGuestbookKey", guestbookVo);
	}
	
	//삭제
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("dao 삭제");
		
		int count = sqlSession.delete("guestbook.guestDelete", guestbookVo);
		
		return count;
	}
	
	
	/*
	//삭제2
	public int delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		System.out.println("dao 삭제");
		
		Map<String, Object> guestMap = new HashMap<String, Object>();
		guestMap.put("no", no);
		guestMap.put("password", password);
		
		System.out.println(guestMap);
		
		int count = sqlSession.delete("guestbook.guestDelete2", guestMap);
		
		return count;
	}*/
}
