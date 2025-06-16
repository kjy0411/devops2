package com.sist.web.service;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
public interface BoardService {
	public BoardEntity boardDetailData(int no);
	public Map boardLsitData(int page);
	public void boardInsert(BoardEntity vo);
}
