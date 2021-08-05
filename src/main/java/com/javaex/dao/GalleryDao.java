package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	//리스트
	public List<GalleryVo> getGalleryList(){
		System.out.println("[GalleryDao.list]");
		
		List<GalleryVo> getGalleryList = sqlSession.selectList("gallery.galleryList");
		
		return getGalleryList;
	}
}
