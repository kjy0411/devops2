package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
@Repository
public interface CampRepository extends JpaRepository<CampEntity, Integer>{
	@Query(value = "SELECT cno,poster,title,intro,hit,rcount,num "
			+ "FROM (SELECT cno,poster,title,intro,hit,rcount,rownum as num "
			+ "FROM (SELECT cno,poster,title,intro,hit,rcount "
			+ "FROM camp WHERE poster IS NOT null ORDER BY cno)) "
			+ "WHERE num BETWEEN :start AND :end",nativeQuery = true)
	public List<CampListVO> campListData(@Param("start") Integer start,@Param("end") Integer end);
	
	public CampEntity findByCno(int cno);
}
