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
	
	
	
	//등록 이미지파일 업로드
	public int insert(GalleryVo galleryVo) {
		System.out.println("[GalleryDao.insert]");
		
		System.out.println("가기전 Dao : " + galleryVo);
		 int count = sqlSession.insert("gallery.galleryInsert", galleryVo);
		System.out.println("갔다온 Dao : " + galleryVo);
		
		return count;
	}
}
