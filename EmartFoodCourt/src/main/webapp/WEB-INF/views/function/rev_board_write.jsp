<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />
<jsp:include page="../checkfolder/login_check.jsp" />
<c:set var="nowPage" value="${requestScope.page}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp"%>
<script src="/EmartFoodCourt/resources/js/common_summernote.js"></script>
<meta charset="UTF-8">
<title></title>



<!-- include libraries(jQuery, bootstrap) -->
<link rel="stylesheet" href="../EmartFoodCourt/resources/css/common.css">
<link rel="stylesheet"
	href="../EmartFoodCourt/resources/css/summernote/summernote-lite.css">
<script src="/EmartFoodCourt/resources/js/summernote/summernote-lite.js"></script>
<script
	src="/EmartFoodCourt/resources/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet"
	href="../EmartFoodCourt/resources/css/rev_board_write.css">
<script src="/EmartFoodCourt/resources/js/rev_board_write.js"></script>

<style>
.file_image {
	background-size: 150px;
}
.note-editable {
            overflow-y: scroll;
    }

</style>


</head>
<body>

	<jsp:include page="../nav.jsp" />



	<section id="writeForm">
		<h2>후기등록</h2>
		<form action="/EmartFoodCourt/Rev_Board_writePro.fc" method="post"
			enctype="multipart/form-data" name="boardform">

			<div id="jb_table">
				<div class="row">
					<div class="cell col1">음식이름</div>
					<div class="cell col2">
						<input type="text" value="${param.food_name}" required="required"
							style="width: 50%;" readonly />
					</div>
				</div>
				<div class="row">
					<div class="cell col1">지점이름</div>
					<div class="cell col2">
						<input type="text" value="${param.jijum_name}" required="required"
							readonly />
					</div>
				</div>
				<div class="row">
					<div class="cell col1">
						<label for="rev_satisfaction">평가하기</label>
					</div>
					<div class="cell col2">
						<select name="rev_satisfaction" id="rev_satisfaction">
							<option value="1">★</option>
							<option value="2">★★</option>
							<option value="3">★★★</option>
							<option value="4">★★★★</option>
							<option value="5">★★★★★</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="cell col1">
						<label for="id">글쓴이</label>
					</div>
					<div class="cell col2">
						<input type="text" name="id" id="id" value="${id}"
							required="required" readonly>
					</div>
				</div>
				<div class="row">
					<div class="cell col1">
						<label for="rev_subject">제목</label>
					</div>
					<div class="cell col2">
						<input name="rev_subject" type="text" id="rev_subject"
							required="required" />
					</div>
				</div>
				<div class="row">
					<div class="cell col3">
						<div id="summernote" style=""></div>
						<textarea id="board_content" name="rev_content"></textarea>

					</div>
				</div>
				<!-- <div class="row">
						<div class="cell col1">
						<label for="rev_content">내용</label>
						</div>
						<div class="cell col2">
						<textarea id="BOARD_CONTENT" name="rev_content"
							style="width: 100%; height: 100%;"></textarea>
						</div>
				</div>
				<div class="row">
						<div class="cell col1">
						<label for="img_name">이미지 첨부</label>
						</div>
						<div class="cell col2">
						<input multiple="multiple" name="img_name" type="file"
						id="img_name" accept="image/*"
						onchange="setThumbnail(event);"  /><input type="button" onClick="image_plus()" value="+">
						</div>
				</div> -->
			</div>


			<div id="image_container"></div>
			<input type="hidden" name="connect_num" id="connect_num"
				value="${param.connect_num}" />


			<section id="commandCell">
				<input type="button" value="등록" onclick="formchk(boardform)">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기" /> <a
					href="javascript:history.back()">이전목록으로</a>
			</section>
		</form>
	</section>
	<!-- 게시판 등록 -->


</body>
<!-- footer -->
<jsp:include page="../footer.jsp" />
<!-- //footer -->
</html>