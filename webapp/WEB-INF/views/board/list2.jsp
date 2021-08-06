<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="container" class="clearfix">
			
			<!-- 게시판 aside -->
			<c:import url="/WEB-INF/views/includes/asideBoard.jsp"></c:import>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="board">
					<div id="list">
						<form action="${pageContext.request.contextPath }/board/list" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							
							<c:forEach items = "${requestScope.listMap.boardList}" var = "boardList">
								<tbody>
									<tr>
										<td>${boardList.no }</td>
										<td class="text-left">
											<a href="${pageContext.request.contextPath }/board/read?no=${boardList.no}">${boardList.title }</a>
										</td>
										<td>${boardList.name }</td>
										<td>${boardList.hit }</td>
										<td>${boardList.regDate }</td>
										
										<c:if test="${authUser.no == boardList.userNo }">
											<td>
												<a href="${pageContext.request.contextPath }/board/delete?no=${boardList.no}">[삭제]</a>
											</td>
										</c:if>
									</tr>
								</tbody>
							</c:forEach>
						</table>

						<div id="paging">
							<ul>
								<c:if test = "${listMap.prev == true}">	<!-- 괄호 주의 -->
									<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${listMap.startPageBtnNo-1}">◀</a></li>
								</c:if>
								
								<!-- 반복문으로 돌림 -->
								<!-- for문 안에 세트는 사이에 주석 넣지 말기 -->
								<c:forEach begin = "${listMap.startPageBtnNo}" end = "${listMap.endPageBtnNo}" step = "1" var = "page"> <!-- var은 시작 페이지 변수명 만들면됨 -->
									<c:choose>
										<c:when test = "${param.crtPage eq page}">
											<li class = "active"><a href="${pageContext.request.contextPath}/board/list2?crtPage=${page}">${page }</a></li>
										</c:when>
										<c:otherwise>
										<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${page}">${page }</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								<c:if test = "${listMap.next == true}">
									<li><a href="${pageContext.request.contextPath}/board/list2?crtPage=${listMap.endPageBtnNo+1}">▶</a></li>
								</c:if>
							</ul>


							<div class="clear"></div>
						</div>
						
						<c:if test="${!empty authUser }">
							<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
						</c:if>
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
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
