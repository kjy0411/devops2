package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	//상세보기
	public BoardEntity findByNo(int no);
	//목록
	@Query(value = "SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
			+ "FROM (SELECT no,name,subject,content,regdate,hit,rownum as num "
			+ "FROM (SELECT no,name,subject,content,regdate,hit "
			+ "FROM kjy_board ORDER BY no DESC)) "
			+ "WHERE num BETWEEN :start AND :end",nativeQuery = true)
	public List<BoardVO> boardListData(@Param("start") Integer start, @Param("end") Integer end);
	// Sequence 
	@Query(value="SELECT NVL(MAX(no)+1,1) FROM kjy_board",nativeQuery = true)
	public int maxNo();
}
