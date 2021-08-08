package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Service
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;
	
	//리스트
	public List<GalleryVo> list(){
		System.out.println("[GalleryService.list]");
		
		List<GalleryVo> galleryList = galleryDao.getGalleryList();
		System.out.println("GalleryService.list : " + galleryList.toString());
		
		
		return galleryList;
	}
	
	
	//이미지 등록
	public int upload(MultipartFile file, GalleryVo galleryVo) {
		System.out.println("[GalleryService.upload]");
		
		//파일 저장할 경로
		String saveDir = "C:\\javaStudy\\upload";
		
		//원래 파일 이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName : " + orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName : " + exName);
		
		//저장 파일 이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println("saveName : " + saveName);
		
		//파일경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath : " + filePath);
		
		//파일 사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize : " + fileSize);
		
		
		
		//파일 서버 하드에 저장
		try {
			byte[] fileData = file.getBytes();
			
			//빨대작업
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//위의값들 Vo에 넣어줌
		System.out.println("[GalleryService.upload] 입력 전: " + galleryVo);
		System.out.println("[GalleryService.upload] 입력 전: " + file.getOriginalFilename());
		
		galleryVo.setOrgName(orgName);
		galleryVo.setSaveName(saveName);
		galleryVo.setFilePath(filePath);
		galleryVo.setFileSize(fileSize);
		
		System.out.println("[GalleryService.upload] 입력 후 : " + galleryVo);
		System.out.println("[GalleryService.upload] 입력 후: " + file.getOriginalFilename());
		
		
		//db에 정보 저장
		int dbInsert = galleryDao.insert(galleryVo);
		
		return dbInsert;
	}
	
	
	
	//이미지 읽어오기
	public int read() {
		System.out.println("[GalleryService.read()]");
		
		return 1;
	}
	
}
