package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import com.sist.web.service.*;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
@Controller
public class MainController {
	@Autowired
	private CampService service;
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("main_html","main/home");
		return "index";
	}
	@GetMapping("/camp/list")
	public String camp_list(@RequestParam(name = "page",required = false) String page,Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=service.campListData(curpage);
		
		model.addAttribute("list",map.get("list"));
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",map.get("totalpage"));
		model.addAttribute("startPage",map.get("startPage"));
		model.addAttribute("endPage",map.get("endPage"));
		model.addAttribute("main_html","camp/list");
		return "index";
	}
	@GetMapping("/camp/detail")
	public String camp_detail(@RequestParam("cno") int cno,Model model) {
		CampEntity vo=service.campDetailData(cno);
		model.addAttribute("vo",vo);
		model.addAttribute("main_html","camp/detail");
		return "index";
	}
	@GetMapping("/camp/find")
	public String camp_find(@RequestParam(name = "page",required = false) String page,Model model) {
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		Map map=service.campListData(curpage);
		
		model.addAttribute("list",map.get("list"));
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",map.get("totalpage"));
		model.addAttribute("startPage",map.get("startPage"));
		model.addAttribute("endPage",map.get("endPage"));
		model.addAttribute("main_html","camp/find");
		return "index";
	}
}
