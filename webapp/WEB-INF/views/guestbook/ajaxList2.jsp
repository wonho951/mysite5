<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 리스트</title>

<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">	<!-- 부트스트랩 링크 -->

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src = "${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src = "${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>
</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">
		<c:import url="/WEB-INF/views/includes/asideGuest.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form action="${pageContext.request.contextPath }" method="get">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label>
									</th>
									<td>
										<input id="input-uname" type="text" name="name">
									</td>
									<th><label class="form-text" for="input-pass">패스워드</label>
									</th>
									<td>
										<input id="input-pass" type="password" name="pass">
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<textarea name="content" cols="72" rows="5"></textarea>
									</td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center">
										<button id = "btnSubmit" type="submit">등록</button>
									</td>
								</tr>
							</tbody>

						</table>
						<!-- //guestWrite -->
						<input type="hidden" name="action" value="add">

					</form>

					
					<div id="listArea">
						<!-- jquery 그리는 영역, 이거 없고 리스트 출력 시킬때 아무것도 안해주면 리스트 안나옴 -->
					</div>
					
				</div>
				<!-- //guestbook -->

			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url = "/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //footer -->
	</div>
	<!-- //wrap -->

</body>

<script type="text/javascript">

//화면이 로딩되기 직전 -> DOM생성(웹 페이지를 스크립트 또는 프로그래밍 언어들에서 사용될 수 있게 연결시켜주는 역할)
$(document).ready(function(){	//ready(function(){}) -> 괄호 헷갈리니 주의.
	console.log("화면 로딩 직전");
	
	//ajax로 요청하기
	$.ajax({
		
		/*****요청을 보내는 부분*********/
		url : "${pageContext.request.contextPath }/api/guestbook/list2",
		type : "post",
		//contentType : "application/json",
		//data : {name: ”홍길동"},

		
		
		/*****보낸 요청을 받는 부분*********/
		//dataType : "json",
		success : function(guestList){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestList);
			
			//화면에 그리기 -> 반복해서 그려야 하니 for문 사용
			for(var i = 0; i < guestList.length; i++){
				render(guestList[i]);	//render는 리스트 웹 브라우저에 그려주는 함수 이름임.
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});



//그리는 문법임
//방명록 1개씩 랜더링
function render(guestbookVo, type){
    var str = "";
    str += '<table id = "t-' + guestbookVo.no + '" class="guestRead">';	//id값은 테이블이라 t-이고 각 테이블마다 vo의 no값이 있음
    str += '   <colgroup>';
    str += '      <col style="width: 10%;">';
    str += '      <col style="width: 40%;">';
    str += '      <col style="width: 40%;">';
    str += '      <col style="width: 10%;">';
    str += '   </colgroup>';
    str += '   <tr>';
    str += '      <td>' + guestbookVo.no  + '</td> ';
    str += '      <td>' + guestbookVo.name  + '</td>';
    str += '      <td>' + guestbookVo.regDate  + '</td>';
    str += '      <td><button class = "btnDel">[삭제]</button></td>';	 
    str += '   </tr> ';
    str += '   <tr> ';
    str += '      <td colspan=4 class="text-left">' + guestbookVo.content + '</td> ';
    str += '   </tr>';
    str += '</table> ';
    
    $("#listArea").append(str);
    
    if(type === 'down'){
    	$("#listArea").append(str); 	//리스트 나오게 하는 거임. 백날 위에꺼 코딩 해놓고 아 왜 안나와 하면 안나옴ㅋ.ㅋ            	
    } else if(type === 'up'){
    	$("#listArea").prepend(str);
    } else {
    	console.log("방향을 지정해 주세요");
    }
    
};


//화면 로딩 끝난 후
//등록 버튼 클릭할때
$("#btnSubmit").on("click", function(){
	
	//원래 html body에 작성한 form에 action 작동 못하게 하는거
	event.preventDefault();	
	
	//등록 눌렀을때 작동 되는지 확인
	console.log("등록 버튼 클릭")
	
	//등록 하려면 name, password, content 값이 입력 되야함
	//name값 읽어오기
	var userName = $("#input-uname").val();	//val은 form 태그의 값을 읽어오거나 입력할때 사용
	console.log(userName);
	
	//password값 읽어오기
	var password = $("#input-pass").val();	//#은 id를 나타낼때 사용.
	console.log(password);
	
	//content값 읽어오기
	var content = $("[name = 'content'").val();	//[]는 속성값을 읽어 올때 사용하고 as = '' 로 나타내줘야함.
	console.log(content);
	
	var guestbookVo = {
		name : $("#input-uname").val(),			//변수명을 지정해 줬지만 굳이 변수명을 지정해주지 않고 바로 사용 가능.
		password : $("#input-pass").val(),
		content : $("[name = 'content'").val()
	}
	
	
	//데이터를 ajax방식으로 서버에 전송
	$.ajax({
		
		url : "${pageContext.request.contextPath }/api/guestbook/write2",     		
		type : "get",
		//contentType : "application/json",
		data : guestbookVo,
		//data : {name: userName, password : password, content : content},
		

		dataType : "json",
		success : function(guestbookVo){
			/*성공시 처리해야될 코드 작성*/
			console.log(guestbookVo);
			render(guestbookVo, "up");
			
			//입력폼 초기화
			$("#input-uname").val("");	//()안에 ""있으면 값 비워줌
			$("#input-pass").val("");
			$("[name = 'content']").val("");
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});

});


</script>

</html>