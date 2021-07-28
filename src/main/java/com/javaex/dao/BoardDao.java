package com.javaex.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {

	
	public int updateHit(int no) {
		System.out.println("BoardDao.updateHit[]");
		System.out.println(no);
		
		
		return 1;
	}
	
}
