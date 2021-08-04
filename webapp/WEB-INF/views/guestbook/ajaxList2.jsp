<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
						<!-- jquery 그리는 영역 -->
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
$(document).ready(function)(){
	console.log("화면 로딩 직전")
}



</script>

</html>