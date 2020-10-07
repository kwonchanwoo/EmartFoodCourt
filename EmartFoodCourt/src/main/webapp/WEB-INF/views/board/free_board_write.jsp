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
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp" %>

<link rel="stylesheet" href="resources/css/common_free_board.css">
<link rel="stylesheet"
	href="../EmartFoodCourt/resources/css/summernote/summernote-lite.css">


<style>
#board_sub2{
margin-right:20px;
}
#submit{
	margin:0 auto;
	text-align:center;
}
 #board_content{
	visibility:hidden;
}  
</style>
<script>

</script>
</head>
<body>
<jsp:include page="../board/free_board_nav.jsp" />
	<form action="/EmartFoodCourt/boardList_writePro.bd" method="post" enctype="multipart/form-data"  name="boardform">
	<div class="free_container">
			<jsp:include page="../board/sidebar.jsp"/>
			<div class="header">
				<div id="board_title">글쓰기</div>
				<div id="board_sub1">
					<select name="board_category" id="board_category">
						<c:if test="${id_grade==2}">
							<option value="notice">공지사항</option>
							<option value="event">이벤트 공지</option>
						</c:if>
						<option value="free">자유</option>
						<option value="humor">유머</option>
						<option value="video">영상</option>
						
					</select>
				</div>
				<div id="board_sub2">제목<input type="text" name="board_subject" id="board_subject">
			</div>
		</div>
			<div id="table">
				<div id="summernote"></div>
				<textarea id="board_content" name="board_content"></textarea>
			</div>
<!-- 			<div id="vote">
				파일등록<input type="file" name="board_file" id="board_file">	
			</div> -->
			<input type="hidden" name="id" value="${sessionScope.id}">
			<div id="submit">
				<input type="button" value="작성완료" onclick="formchk(boardform)">
				<input type="button" value="취소" onclick="cancel_confirm(this)">
			</div>
		</div>
		</form>
		<!-- footer -->
		<jsp:include page="../footer.jsp" />
		<!-- //footer -->
<script src="/EmartFoodCourt/resources/js/common_summernote.js"></script>
<script src="/EmartFoodCourt/resources/js/summernote/summernote-lite.js"></script>
<script
	src="/EmartFoodCourt/resources/js/summernote/lang/summernote-ko-KR.js"></script>
	<script src="/EmartFoodCourt/resources/js/free_board_nav.js"></script>
<script src="/EmartFoodCourt/resources/js/free_board_write.js"></script>
</body>
</html>