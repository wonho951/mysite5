package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	
	//2021.08.06
	//게시판 페이징 연습용 리스트
	public Map<String, Object> getList2(int crtPage, String keyword){
		System.out.println("[BoardService.getList2]");
		//System.out.println(crtPage);
		
		
		
		////////////////////////////
		////리스트 가져오기 -> 최종목표는 리스트 가져오는거 -> 전체 다 가져오는거 아님. 특정 갯수만 가져옴.
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
		//값 ? 값 : 값 -> 위에 for문이랑 같음
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		
		//시작번호 계산하기
		int startRnum = (crtPage-1)*listCnt+1;
		//끝번호 계산하기
		int endRnum = (startRnum+listCnt)-1;	//startRnum*listCnt
		
		//()안에 페이징의 시작번호 끝번호를 줘야함
		List<BoardVo> boardList = boardDao.selectList2(startRnum, endRnum, keyword);
		
		
		////////////////////////////
		////페이징 계산 (밑에 12345)
		///////////////////////////
		
		//전체 글 갯수
		int totalCount = boardDao.selectTotalCnt(keyword);
		//전체갯수 찍어보기
		//System.out.println(totalCount);
		
		
		//페이지당 버튼 갯수 -> 버튼 몇개씩 보여줄거냐
		int pageBtnCount = 5;
		
		/////////마지막 버튼 번호 구하기///////////////////
		//현재 페이지가 1이면 1~5까지 나와야함.
		//현재 페이지가 2이면 1~5까지 나와야함. -> 5페이지까지 반복
		//현재 페이지가 6이면 6~10까지 나와야함.
		//현재 페이지가 7이면 6~10까지 나와야함.
		//현재 페이지가 10이면 6~10까지 나와야함.
		int endPageBtnNo = (int)Math.ceil((crtPage/(double)pageBtnCount)) * pageBtnCount;
							//1페이지/(double)5 -> 0*5 -> 0 * 5		-> 결과: 0 
								  //↳double 형으로 바꿔버림 그럼 5가 5.0이 됨.그래서 0.2*5가됨u
							//2페이지/5.0 -> 0.4*5 -> 0 * 5		-> 결과: 0 
							//3페이지/5.0 -> 0.6*5 -> 0 * 5		-> 결과: 0 
							//4페이지/5.0 -> 0.8*5 -> 0 * 5		-> 결과: 0 
							//5페이지/5.0 -> 1*5 -> 1 * 5		-> 결과: 5
											//	↓↓
							//1/5.0 -> 0.2 ->올림	 1.0		1.0*5->5.0
							//1/5.0 -> 0.4 ->올림	 1.0		1.0*5->5.0
							//1/5.0 -> 0.6 ->올림	 1.0		1.0*5->5.0
							//1/5.0 -> 0.8 ->올림	 1.0		1.0*5->5.0
							//1/5.0 -> 1.0 ->올림	 1.0		1.0*5->5.0
		
		
		//시작 버튼 번호
		int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
							//10		  -    4 -> 6
		
		//다음 화살표 표현 유무
		boolean next = false;
		if((endPageBtnNo * listCnt) < totalCount) {
			next = true;
		} else {
			//다음 화살표 버튼이 없을때 endPageBtnNo을 다시 계산해야 한다.
			//전체 글 갯수(127개) / 한페이지의 갯수(10개) -> 사람이 계산하면 12.7이 나오지만 자바가 계산하면 12로 나옴
			//우리가 필요한 숫자는 13임. 그래서 형변환 한 후에 올림처리함. 그리고 다시 정수형으로 형변환 시킴
			endPageBtnNo = (int)Math.ceil(totalCount / (double)listCnt);
		}								//127개     / 10.0  -> 12.7 -> 올림처리함.->13
		
		
		//127개 /10.0 페이지 --> 12.7:버림처리해서 -> 12로 계산 -> 120 + 7 -> 13페이지까지 나와야함
		
		
		
		
		//이전 화살표 표현 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		
		
		
		////////////////////////////
		////Map로 리턴하기
		///////////////////////////
		
		
		//4개를 다 보내려면 map 또는 vo를 만든다.
		   //↓
		//리턴하기 (map)사용	->	사실상 순서는 상관없긴함.
		Map<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("boardList", boardList);
		listMap.put("prev", prev);			//prev는 boolean형
		listMap.put("startPageBtnNo", startPageBtnNo);
		listMap.put("endPageBtnNo", endPageBtnNo);
		listMap.put("next", next);
		
		
		
		
		
		return listMap;
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
