package com.javaex.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileupService {

   
   //파일 업로드 처리
   public void restore(MultipartFile file) {
      System.out.println("FileupController.restore()");
      
      //System.out.println(file.getOriginalFilename());
      //System.out.println(file.getSize());
      
      //파일 서버하드디스크에 저장
      //파일정보를 db에 저장
      
      //원파일 이름
      String orgName = file.getOriginalFilename();
      System.out.println(orgName);
      
      //확장자
      String exName = file.getOriginalFilename().substring(   file.getOriginalFilename().lastIndexOf(".")    );	//kang.jpg (substring은 .기준(원파일 이름)으로 앞부분 날리고 뒷부분(확장자)구해라 
      System.out.println(exName);	//.jpg(확장자 나옴)
      
      //저장파일 이름(관리때문에 겹치지 않는 새이름 부여)
      //이름 절대 안겹쳐야함.
      //이 조합으로 함 (시간 기준 + 랜덤 아이디 + 확장자)로 조합해서 안겹치게 만든다.(생각해낸 사람 대단하네)
      String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;	//얘는 tostring 까지 써야함. -> 이름 안겹치게 랜덤으로 만들어줌(근데 재수나쁘면 같은애 나올 수도 있음. 이론적으로는 로또당첨보다 힘들긴함;)
      
      System.out.println(saveName);
      
      
      //파일패스
      
      //파일 사이즈
      
   }
   
}