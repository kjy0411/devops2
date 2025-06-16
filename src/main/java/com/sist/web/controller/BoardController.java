package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import com.sist.web.service.*;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	
	@GetMapping("/board/list")
	public String board_list(@RequestParam(name = "page",required = false) String page,Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		
		Map map=service.boardLsitData(curpage);
		
		model.addAttribute("list",map.get("list"));
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",map.get("totalpage"));
		model.addAttribute("startPage",map.get("startPage"));
		model.addAttribute("endPage",map.get("endPage"));
		model.addAttribute("main_html","board/list");
		return "index";
	}
	@GetMapping("/board/detail/{no}")
	public String board_detail(@PathVariable("no") int no,Model model) {
		BoardEntity vo=service.boardDetailData(no);
		model.addAttribute("main_html","board/detail");
		return "index";
	}
	@GetMapping("/board/insert")
	public String board_insert(Model model) {
		model.addAttribute("main_html","board/insert");
		return "index";
	}
}
