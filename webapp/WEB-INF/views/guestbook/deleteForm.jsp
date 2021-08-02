<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">


		<!-- //header  //nav -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		
		<div id="container" class="clearfix">
			<c:import url="/WEB-INF/views/includes/asideGuest.jsp"></c:import>
			<!-- //aside -->

			<div id="content">
			
				<div id="content-head">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="guestbook">
					<form action="${pageContext.request.contextPath }/guestbook/delete" method="get">
						<table id="guestDelete">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 25%;">
								<col style="width: 25%;">
							</colgroup>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="password"></td>
								<td class="text-left"><button type="submit">삭제</button></td>
								<td><a href="${pageContext.request.contextPath }/main">[메인으로 돌아가기]</a></td>
							</tr>
					
						</table>
						
							<input type = "hidden" name="no" value = ${param.no }>
							<br>
							<c:if test = "${param.result == 'fail' }">
								<p>비밀번호가 일치하지 않습니다.</p>
							</c:if>
					</form>
					
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

</html>
