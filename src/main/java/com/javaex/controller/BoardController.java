package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	//게시판 페이징 연습용 리스트
	@RequestMapping(value = "/list2", method = {RequestMethod.GET, RequestMethod.POST})
	public String list2(Model model, 
						@RequestParam(value = "crtPage", required = false, defaultValue = "1") int crtPage) {
		System.out.println("[BoardController.list2()]");
		//System.out.println(crtPage);
		
		//리스트 가져오기
		List<BoardVo> boardList = boardService.getList2(crtPage);
		//잘 넘어왔는지 확인용
		//System.out.println(boardList);
		
		//가져온거 담기
		model.addAttribute("boardList", boardList);
		
		return "board/list2";
	};
	
	
	
	
	//글읽기
	@RequestMapping(value = "/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(Model model, @RequestParam("no") int no) {
		System.out.println("[BoardController.read()]");
		//System.out.println(no);
		
		BoardVo boardVo = boardService.getBoard(no);
		//System.out.println(boardVo);
		
		model.addAttribute("boardVo", boardVo);
		System.out.println(model);
		
		return "board/read";
	}
	
	
	
	//리스트
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
		System.out.println("컨트롤러 리스트");
		
		//사용자가 list 요청 -> 서비스로 보냄
		List<BoardVo> boardList = boardService.list(keyword);
		//System.out.println(boardList);
		
		//jsp로 보내야됨 -> 리턴해서 넘어온 boardList를 어트리뷰트에 담아줌
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	
	//글쓰기 폼
	@RequestMapping(value = "/writeForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String wirteForm() {
		System.out.println("컨트롤러 글쓰기폼");
		
		return "board/writeForm";
	}
	
	
	//글 등록
	@RequestMapping(value = "/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("컨트롤러 글등록");
		
		//세션에서 정보 가져옴
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		//vo에 세션에서 가져온 no 담기
		int no = authUser.getNo();
		boardVo.setUserNo(no);
		
		boardService.write(boardVo);
		
		System.out.println(boardVo);
		return "redirect:/board/list";
	}
	
	
	//글삭제
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("컨트롤러 삭제");
		System.out.println(no);
		
		boardService.delete(no);
		
		
		return "redirect:/board/list";
	}
	

	//수정폼
	@RequestMapping(value = "/modifyForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam("no") int no, Model model, HttpSession session) {
		System.out.println("컨트롤러 수정폼");
		
		//이건 수업시간에 해주신거 -> 주소알면 뚫리니까 방지
		//현재글의 작성자(번호) == 세션(로그인한 사용자)의 번호 --> 정상인경우
		//현재글의 작성자(번호) != 세션(로그인한 사용자)의 번호 --> 정상이 아닌 경우
		BoardVo boardVo = boardService.modifyForm(no);
		
		
		//세션에서 유저 정보 불러옴
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		//로그인 안한경우 ->접속 불가능하게 해야함 -> 메인으로 보냄
		if(authUser == null) {
			System.out.println("로그인 안한 경우");

			//포워드 하기전에 모델에 담아줌
			model.addAttribute("boardVo", boardVo);
			return "redirect:/main";
		}
		
		//로그인한 사용자 == 글작성자
		if(authUser.getNo() == boardVo.getUserNo()) {
			System.out.println("자신의 글인 경우 수정폼 포워드");
			return "board/modifyForm";
		}else {
			System.out.println("다른 사람의 글인 경우");
			return "redirect:/board/list";
		}
		
		/* 1의 경우
		//현재글의 작성자(번호)
		int userNo = boardVo.getUserNo();
		System.out.println("userNo =" + userNo);
		
		
		//세션(로그인한 사용자)의 번호
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		int loginNo = authUser.getNo();
		System.out.println("loginNo= " + loginNo);
		
		if(userNo == loginNo) {
			model.addAttribute("boardVo", boardVo);
			return "board/modifyForm";
		}else {
			return "redirect:/board/list";
		}*/
		
		
		//여긴 내가 한거
		/*
		model.addAttribute("boardVo", boardVo);
		return "board/modifyForm";
		*/
	}

	
	//수정
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo, HttpSession session) {
		System.out.println("컨트롤러 수정");
		System.out.println(boardVo);
		
		//로그인한 사용자만 수정 가능하게끔
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		boardVo.setUserNo(authUser.getNo());
		
		boardService.modify(boardVo);
		
		/* 제목 본문 로그인 사용자 번호 넘김 */
		return "redirect:/board/list";
	}
	
}
