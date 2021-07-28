package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;	//얘는 DB관련 HTTPSession은 메모리에 올라가는애
	
	//조회수 올리기
	public int updateHit(int no) {
		System.out.println("BoardDao.updateHit[]");
		//System.out.println(no);
		
		
		//dao는 쿼리문 하나만 실행해야한다
		int count = sqlSession.update("board.updateHit", no);
		//System.out.println(count);
		
		return count;
	}
	
	
	//게시판 1개 정보 가져오기
	public BoardVo selectBoard(int no) {
		System.out.println("BoardDao.selectBoard[]");
		System.out.println(no);
		
		//쿼리문으로 넘김
		//BoardVo boardVo = sqlSession.selectOne("board.selectBoard", no);
		//System.out.println(boardVo);
		
		//바로 리턴값으로 넘겨도 됨
		return sqlSession.selectOne("board.selectBoard", no);
	}
	
	
	
	//게시판 리스트
	public List<BoardVo> boardList(String keyword){
		System.out.println("BoardDao.selectList[]");
		
		//서비스에서 넘어온 리스트 -> sqlSession사용
		List<BoardVo> boardList = sqlSession.selectList("board.boardList", keyword);
		System.out.println(boardList);
		
		//xml정보 넘김
		return boardList;
		
	}
	
	
	//게시판 글 등록
	public int insert(BoardVo boardVo) {
		System.out.println("Dao : 글 등록");
		System.out.println(boardVo);
		
		return sqlSession.insert("board.boardInsert", boardVo);
	}
	
	

	
}
