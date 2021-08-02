package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestDao;
	
	//리스트
	public List<GuestbookVo> list(){
		//System.out.println("서비스 리스트");
		
		List<GuestbookVo> guestList = guestDao.getGuestList();
		//System.out.println("서비스 리스트" + guestList.toString());
		
		return guestList;
	}
	
	
	//등록
	public int insert(GuestbookVo guestbookVo) {
		System.out.println("서비스: 등록");
		
		 return guestDao.insert(guestbookVo);
	}
	
	
	//삭제
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("서비스: 삭제");
		
		int count = guestDao.delete(guestbookVo);
		
		return count;
	}
	
	
	/*
	//삭제2
	public int delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		System.out.println("서비스: 삭제");
		
		int count = guestDao.delete(no, password); 
		
		return count;
	}*/
}
