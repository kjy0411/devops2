package com.sist.web.service;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
public interface CampService {
	public Map campListData(int page);
	public CampEntity campDetailData(int cno);
	public Map campFindData(int page,String fd);
}
