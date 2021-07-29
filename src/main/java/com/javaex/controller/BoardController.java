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
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("컨트롤러 수정폼");
		
		BoardVo boardVo = boardService.modifyForm(no);
		
		
		model.addAttribute("boardVo", boardVo);
		
		return "board/modifyForm";
	}

	
	//수정
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("컨트롤러 수정");
		System.out.println(boardVo);
		
		boardService.modifyForm(boardVo);
		
		return null;
	}
	
}
