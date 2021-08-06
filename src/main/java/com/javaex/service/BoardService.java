package com.javaex.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	//2021.08.06
	//게시판 페이징 연습용 리스트
	public List<BoardVo> getList2(int crtPage){
		System.out.println("[BoardService.getList2]");
		System.out.println(crtPage);
		////////////////////////////
		////리스트 가져오기 -> 최종목표는 리스트 가져오는거
		///////////////////////////
		
		//미리 정의		
		int listCnt = 10;
		
		/*
		//crtPage rPtks(-값일때 1page 처리)
		if(crtPage > 0) {
			//굳이 건드릴 필요가 없음.
			//crtPage = crtPage;
		} else {
			crtPage = 1;
		}*/
		
		//삼항연산자
		//값 ? : 값 : 값 -> 위에 for문이랑 같음
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		
		//시작번호 계산하기
		int startRnum = (crtPage-1)*listCnt+1;
		//끝번호 계산하기
		int endRnum = (startRnum+listCnt)-1;	//startRnum*listCnt
		
		//()안에 페이징의 시작번호 끝번호를 줘야함
		List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum);
		
		return boardList;
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
		
		
		//페이징 때문에 일부러 많이 등록함.
		for(int i = 0; i < 127; i++) {
			boardVo.setTitle(i + "번째 제목입니다.");
			boardVo.setContent(i + "번째 내용입니다.");
			boardDao.insert(boardVo);
		}
		
		
		return 1;
		//return boardDao.insert(boardVo);
		
	}
	
	
	//게시판 글 삭제
	public int delete(int no) {
		System.out.println("Service : 글 삭제");
		
		return boardDao.delete(no);
	}
	
	
	//글 수정폼
	public BoardVo modifyForm(int no) {
		System.out.println("Service : 글 수정폼");
		

		BoardVo boardVo = boardDao.modifyForm(no);
		
		return boardVo;
	}
	
	
	//글 수정
	public int modify(BoardVo boardVo) {
		System.out.println("Service : 글 수정");
		
		return boardDao.modify(boardVo);
	}
	
}
