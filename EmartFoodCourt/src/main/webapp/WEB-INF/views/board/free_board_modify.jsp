<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp" %>
<link rel="stylesheet" href="resources/css/common.css">

<link rel="stylesheet" href="resources/css/free_board_info.css">
<link rel="stylesheet"
	href="../EmartFoodCourt/resources/css/summernote/summernote-lite.css">
<link rel="stylesheet" href="resources/css/common_free_board.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#submit {
	text-align: center;
	margin: 0 auto;
}

#file_check {
	text-align: center;
	margin: 0 auto;
}

</style>
</head>
<body>
	<jsp:include page="../board/free_board_nav.jsp" />
	<c:forEach var="free_board" items="${free_board}"> 
	<form action="/EmartFoodCourt/boardList_modPro.bd" method="post"
		enctype="multipart/form-data" name="boardform">
		<div class="free_container">
			<jsp:include page="../board/sidebar.jsp" />
			<div class="header">
				<div id="board_title">게시판 글수정</div>
				<div id="board_sub1">
					주제 <select name="board_category" id="board_category">
						<c:if test="${id_grade==2}">
							<c:choose>
								<c:when test="${free_board.board_category=='notice'}">
									<option value="notice" selected>공지사항</option>
								</c:when>
								<c:otherwise>
									<option value="notice" selected>공지사항</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
							<c:when test="${free_board.board_category=='event'}">
								<option value="event" selected>이벤트 공지</option>
							</c:when>
							<c:otherwise>
								<option value="event">이벤트 공지</option>
							</c:otherwise>
						</c:choose>
						</c:if>
						<c:choose>
							<c:when test="${free_board.board_category=='free'}">
								<option value="free" selected>자유</option>
							</c:when>
							<c:otherwise>
								<option value="free">자유</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${free_board.board_category=='humor'}">
								<option value="humor" selected>자유</option>
							</c:when>
							<c:otherwise>
								<option value="humor">유머</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${free_board.board_category=='video'}">
								<option value="video" selected>영상</option>
							</c:when>
							<c:otherwise>
								<option value="video">영상</option>
							</c:otherwise>
						</c:choose>
						

					</select>
				</div>
				<div id="board_sub2">
					제목<input type="text" name="board_subject" id="board_subject"
						value="${free_board.board_subject}">
				</div>
			</div>
			<div id="table">
				<div id="summernote">${free_board.board_content}</div>
				<textarea id="board_content" name="board_content"></textarea>
				
			</div>
			
			<input type="hidden" name="id" value="${free_board.id}"> 
			<input type="hidden" name="board_num" value="${free_board.board_num}">
			<div id="submit">
				<input type="button" onClick="chkForm(boardform);" value="수정하기"> 
				<input type="button" value="취소" onclick="cancel_confirm(this)">
			</div>
		</div>
	</form>
	</c:forEach>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
<script src="/EmartFoodCourt/resources/js/common_summernote.js"></script>
<script src="/EmartFoodCourt/resources/js/summernote/summernote-lite.js"></script>
<script
	src="/EmartFoodCourt/resources/js/summernote/lang/summernote-ko-KR.js"></script>
<script src="/EmartFoodCourt/resources/js/free_board_modify.js"></script>
</body>
</html>