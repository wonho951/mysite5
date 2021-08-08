--테이블삭제
drop table gallery;
--시퀀스 삭제
drop sequence seq_gallery_no;


--갤러리 테이블 생성
create table gallery(
    no number,
    user_no number,
    content varchar2(1000),
    filePath varchar2(500),
    orgName varchar2(200),
    saveName varChar2(500),
    fileSize number,
    primary key(no),
    constraint gallery_fk foreign key (user_no)
    references users(no)
);


--갤러리 시퀀스 생성
create sequence seq_gallery_no
increment by 1
start with 1
nocache;

select  *
from gallery
order by no desc;

--등록
insert into gallery
values ( seq_gallery_no.nextval
        ,1
        ,'콘텐츠'
        ,'저장경로'
        ,'오리지널네임'
        ,'저장네임'
        ,254
        );
        
        
--삭제
delete gallery
where no = 1;


--리스트
select   g.no
        ,g.user_no
        ,g.content
        ,g.filePath        
        ,g.orgName
        ,g.saveName
        ,g.fileSize
        ,u.name
from gallery g, users u
where g.user_no = u.no
order by g.no desc;


select *
from users;


--한개만 읽어오기
select   g.no
        ,g.user_no userNo
        ,g.content
        ,g.filepath
        ,g.orgname
        ,g.savename
        ,g.filesize
        ,u.name
from gallery g, users u
where g.user_no = u.no
and g.no = 3;


--삭제
delete from gallery
where no = 5;
