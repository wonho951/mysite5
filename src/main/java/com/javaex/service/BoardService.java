package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	public BoardVo getBoard(int no) {
		System.out.println("[BoardService.getBoard]");
		//System.out.println(no);
		
		boardDao.updateHit(no);
		
		
		return null;
	}
	
}
