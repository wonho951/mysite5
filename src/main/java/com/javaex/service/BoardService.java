package com.javaex.service;

import java.util.List;

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
	
	
	
	//게시판 리스트
	public List<BoardVo> list(String keyword){
		System.out.println("Service : 리스트");
		
		//컨트롤러에서 넘어온 keyword -> Dao로 보냄
		List<BoardVo> boardList = boardDao.boardList(keyword);
		System.out.println(boardList);
		
		//Dao에서 넘어온거 리턴
		return boardList;
	}
	
	
	//게시판 글 등록
	public int write(BoardVo boardVo) {
		System.out.println("Service : 글 등록");
		System.out.println(boardVo);
		
		
		return boardDao.insert(boardVo);
		
	}
	
	
	//게시판 글 삭제
	public int delete(int no) {
		System.out.println("Service : 글 삭제");
		
		return boardDao.delete(no);
	}
	
	
	
}
