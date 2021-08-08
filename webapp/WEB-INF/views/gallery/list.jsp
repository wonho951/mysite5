<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<!-- 해더 네비 -->
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- //해더 네비 -->


		<div id="container" class="clearfix">
			<!-- 게시판 aside -->
			<c:import url="/WEB-INF/views/includes/asideGallery.jsp"></c:import>
			<!-- //게시판 aside -->

			<div id="content">

				<div id="content-head">
					<h3>갤러리</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>갤러리</li>
							<li class="last">갤러리</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->

				<div id="gallery">
					<div id="list">

					<c:if test = "${authUser.no != null }">
						<button id="btnImgUpload">이미지올리기</button>
						<div class="clear"></div>
					</c:if>

						<ul id="viewArea">

							<!-- 이미지반복영역 -->
							<c:forEach items = "${galleryList}" var = "galleryVo">
								<li id = "listDelete" data-no = "${galleryVo.no }">
									<div class="view">
										<img class="imgItem" src="${pageContext.request.contextPath }/upload/${galleryVo.saveName}">
										<div class="imgWriter">
											작성자: <strong>${galleryVo.name}</strong>
										</div>
									</div>
								</li>
							</c:forEach>
							<!-- 이미지반복영역 -->
							

						</ul>
					</div>
					<!-- //list -->
				</div>
				<!-- //gallery -->


			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->


		<!-- 푸터 -->
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		<!-- //푸터 -->
	</div>
	<!-- //wrap -->






	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form method="post" action="${pageContext.request.contextPath }/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg" src="">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

				</div>
				<form method="" action="">
					<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<c:if test = "${authUser != null }">
							<button type="button" class="btn btn-danger" id="btnDel" data-authuserno = "${authUser.no }">삭제</button>
							
						</c:if>
					</div>


				</form>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>


<script type="text/javascript">

	//등록 클릭할 때
	$("#btnImgUpload").on("click", function(){
		event.preventDefault();
		console.log("이미지 올리기 클릭")
		
		//모달창 초기화
		$("addModalContent").val("");
		$("file").val("");
		
		//모달창 보이기
		$("#addModal").modal();
		
	});
	
	
	//이미지 클릭할때
	$("#viewArea").on("click", "li", function(){
		console.log("이미지 클릭")

		var no = $(this).data("no");	
		console.log(no);
		
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/gallery/read",     		
			type : "post",
			//contentType : "application/json",
			data : {no: no},	//no는 int형이니까 ""사용 하지 말자... ppt복사 붙여넣기 해도 실수좀 제발..


			dataType : "json",
			success : function(galleryVo){
				/*성공시 처리해야될 코드 작성*/
				console.log("vo받기");
				console.log(galleryVo);
				
				//이미지 보여주기
				$("#viewModelImg").attr("src", "${pageContext.request.contextPath }/upload/" + galleryVo.saveName)
				
				//컨텐츠 보여주기
				$("#viewModelContent").text(galleryVo.content);
				
				//삭제 버튼 눌렀을때 no값 넘겨야함.
				$("#btnDel").val(no);
			
				//삭제버튼 숨김 or 보이기 -> 삭제 버튼에 data주는 방법
				var userNo = galleryVo.userNo;
				/*
				var authUserNo = $("#btnDel").data("authuserno");
				
				
				if(userNo != authUserNo) {
					console.log("삭제버튼 숨김")
					$("#btnDel").hide();
				} else {
					console.log("삭제버튼 보임")
					$("#btnDel").show();
				}*/
				
				//삭제버튼 보이기 숨기기 - 세션스코프 유저넘버 가져와도 됨.
				if(userNo != "${authUser.no}"){
					console.log("삭제버튼 숨김")
					$("#btnDel").hide();
				} else {
					console.log("삭제버튼 보임")
					$("#btnDel").show();
				}	
				
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});


		$("#viewModal").modal();
	});


	
	//삭제 클릭시 삭제하기
	$("#btnDel").on("click", function(){
		console.log("로긴 한 사람만 보임")
		
		var no = $("#btnDel").val();
		console.log(no);
		
		
		$.ajax({
			
			url : "${pageContext.request.contextPath }/api/gallery/delete",     		
			type : "post",
			//contentType : "application/json",
			data :  {no : no},
			

			dataType : "json",
			success : function(count){
				/*성공시 처리해야될 코드 작성*/
				
				
				if(count === 1){
					//삭제 후 모달창 닫기
					$("#viewModal").modal("hide");
					
					//리스트에서 지우기
					$("#listDelete").remove();
				} else {
					//모달창 닫기
					$("#viewModal").modal("hide");
				}
				
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
	
	
	
</script>




</html>

