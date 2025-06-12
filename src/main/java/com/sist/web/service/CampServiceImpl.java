package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
@Service
public class CampServiceImpl implements CampService{
	@Autowired
	private CampRepository cDao;

	@Override
	public Map campListData(int curpage) {
		Map map=new HashMap();
		int rowSize=12;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		
		List<CampListVO> list=cDao.campListData(start, end);
		int count=(int)cDao.count();
		int totalpage=(int)(Math.ceil(count/(double)rowSize));
		
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		map.put("list", list);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}

	@Override
	public CampEntity campDetailData(int cno) {
		return cDao.findByCno(cno);
	}
	
}
