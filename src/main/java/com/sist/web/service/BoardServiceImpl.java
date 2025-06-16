package com.sist.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

import com.sist.web.dao.BoardRepository;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
/*
	DAO => 데이터베이스 연동
	Service => 로직 처리 ===> BI
	Controller => Service에서 결과값을 받아서 브라우저에 전송하는 역할
	
	CI/CD => DevOps
														| => Docker
	Git ===========> Repository에 저장 =====> jar ===========> AWS에 전송 .jar
		commit/push		감지					실행파일로 변경			|
											Git Action			jar파일이 들어오는 경우 실행
											deploy.yml			java -jar de....jar
 */
// boot에서 웹 개발 => 프레임워크를 그대로 사용 : 어노테이션 사용이 가능
@Service
public class BoardServiceImpl implements BoardService{
	// 주소 읽기 => 스프링 (메모리 할당)
	@Autowired
	private BoardRepository bDao;

	@Override
	public BoardEntity boardDetailData(int no) {
		BoardEntity vo=bDao.findByNo(no);
		// 조회수 증가
		vo.setHit(vo.getHit()+1);
		bDao.save(vo);
		vo=bDao.findByNo(no);
		return vo;
	}

	@Override
	public Map boardLsitData(int page) {
		Map map=new HashMap();
		int rowSize=10;
		int start=(page*rowSize)-(rowSize-1);
		int end=page*rowSize;
		
		List<BoardVO> list=bDao.boardListData(start, end);
		int count=(int)bDao.count();
		int totalpage=(int)(Math.ceil(count/(double)rowSize));
		
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		map.put("list", list);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		return map;
	}

	@Override
	public void boardInsert(BoardEntity vo) {
		vo.setHit(0);
		vo.setRegdate(new Date());
		
		bDao.save(vo);
	}
	
	public int maxNo() {
		return bDao.maxNo();
	}
}
