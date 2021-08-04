<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src = "${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
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
						
					</div>
					
					
					<!-- 
					<table class="guestRead">
						<colgroup>
							<col style="width: 10%;">
							<col style="width: 40%;">
							<col style="width: 40%;">
							<col style="width: 10%;">
						</colgroup>
						<tr>
							<td>1234555</td>
							<td>이정재</td>
							<td>2020-03-03 12:12:12</td>
							<td>
								<a href="">[삭제]</a>
							</td>
						</tr>
						<tr>
							<td colspan=4 class="text-left">방명록 글입니다. 방명록 글입니다.</td>
						</tr>
					</table>
					<!-- //guestRead -->

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
		//화면이 로딩되기 직전 -> DOM생성
		$(document).ready(function(){
			console.log("화면 로딩 직전");
			//ajax로 요청하기
			$.ajax({
				
				url : "${pageContext.request.contextPath }/api/guestbook/list",		
				type : "post",
				//contentType : "application/json",
				//data : {name: ”홍길동"},

				//dataType : "json",
				success : function(guestList){
					/*성공시 처리해야될 코드 작성*/
					console.log(guestList);
					
					//화면에 그리기
		         for(var i = 0; i < guestList.length; i++) {
		             render(guestList[i], "down");	//방명록 글 1개씩 추가하기(그리기). down은 밑으로 붙으라고 하는거.
		          }
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
			
		});

		
		//로딩이 끝난후
		//등록버튼 클릭할 때
		$("#btnSubmit").on("click", function(){
			//원래 폼에 액션 작동 안하게 하는거
			event.preventDefault();	//원래 action 기능 사용 못하게함
			console.log("등록버튼 클릭")
			
			
			//name값 읽어오기
			var userName = $("#input-uname").val();
			console.log(userName);
			
			//password값 읽어오기
			var password = $("#input-pass").val();
			console.log(password);
			
			//content값 읽어오기
			var content = $("[name = 'content']").val();
			console.log(content)
			
			
			var guestbookVo = {
				name: userName,	//$("#input-uname").val(); -> 바로 사용 가능
				password : password,	//$("#input-pass").val();
				content: content	//$("[name = 'content']").val();
				
			};
			
			//데이터 ajax방식으로 서버에 전송
			$.ajax({
				
				//url : "${pageContext.request.contextPath }/api/guestbook/write?name=" + userName + "&password=" + password + "&content=" + content,		
				url : "${pageContext.request.contextPath }/api/guestbook/write",
				type : "get",
				//contentType : "application/json",
				//data : {name: userName, password: password, content: content},
				data : guestbookVo,
				
				//dataType : "json",
				success : function(guestbookVo){
					/*성공시 처리해야될 코드 작성*/
					console.log(guestbookVo);
					render(guestbookVo, "up");
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});

		});
		
		
		//삭제 버튼을 클릭할때
		$("#listArea").on("click", ".btnDel", function(){	//직접주지말고 부모한테 먹임. 그리고 btnDel에게 일시킨다.
			console.log("삭제버튼 클릭");
			
			var tag = $(this);
			console.log(tag);
			
			
		});
		
		
		
		//방명록 1개씩 랜더링
		function render(guestbookVo, type){
            var str = "";
            str += '<table class="guestRead">';
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
            str += '      <td><button class = "btnDel">[삭제]</button></td>';	 //링크가 아니고 다른창을 띄울거기 때문에 a링크 사용 안함. 누르면 모달창(팝업창 같은거) 띄움
            str += '   </tr> ';
            str += '   <tr> ';
            str += '      <td colspan=4 class="text-left">' + guestbookVo.content  + '</td> ';
            str += '   </tr>';
            str += '</table> ';
            
            if(type === 'down'){
            	$("#listArea").append(str);            	
            } else if(type === 'up'){
            	$("#listArea").prepend(str);
            } else {
            	console.log("방향을 지정해 주세요");
            }
            
            
		}
		
	
	</script>






</html>