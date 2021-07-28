package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	//글읽기
	@RequestMapping(value = "/board/read", method = {RequestMethod.GET, RequestMethod.POST})
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
	@RequestMapping(value = "/board/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model, @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) {
		System.out.println("리스트");
		
		List<BoardVo> boardList = boardService.list(keyword);
		//System.out.println(boardList);
		
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	
	
}
