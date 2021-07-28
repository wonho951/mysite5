package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	//게시판 정보가져오기
	public BoardVo getBoard(int no) {
		System.out.println("[BoardService.getBoard]");
		//System.out.println(no);
		
		//조회수 올리기
		int count = boardDao.updateHit(no);
		
		//게시판정보 가져오기
		BoardVo boardVo = boardDao.selectBoard(no);
		//System.out.println(boardVo);
		
		return boardVo;
	}
	
}
