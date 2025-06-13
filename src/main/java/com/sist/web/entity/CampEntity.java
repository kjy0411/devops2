package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "camp")
@Data
public class CampEntity {
	@Id
	private int cno;
	private int gnrlsiteco,autositeco,glampsiteco,caravsiteco,indvdlcaravsiteco,hit,fcount,lcount,rcount,price,operdecl;
	private double mapx,mapy;
	private String title,intro,poster,managesttus,hvofbgnde,hvofenddle,operpdcl,induty,lctcl,addr,donm,caravacmpnyat,trleracmpnyat,animalcmgcl;
}
