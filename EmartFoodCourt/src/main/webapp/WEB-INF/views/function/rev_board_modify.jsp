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
<title>MVC 게시판 JSTL</title>
<link rel="stylesheet"
	href="../EmartFoodCourt/resources/css/summernote/summernote-lite.css">
<script src="/EmartFoodCourt/resources/js/summernote/summernote-lite.js"></script>
<script
	src="/EmartFoodCourt/resources/js/summernote/lang/summernote-ko-KR.js"></script>

<link rel="stylesheet" href="../EmartFoodCourt/resources/css/common.css">
<link rel="stylesheet"
	href="../EmartFoodCourt/resources/css/rev_board_modify.css">

<script src="/EmartFoodCourt/resources/js/rev_board_modify.js"></script>

<style>
.note-editable {
	overflow-y: scroll;
}
</style>

</head>
<body>

	<%@ include file="../nav.jsp"%><br>
	<section id="writeForm">
		<h2>후기수정</h2>
		<form name="boardform"
			action="/EmartFoodCourt/Rev_Board_modifyPro.fc?page=${nowPage}"
			method="post" enctype="multipart/form-data">
			<c:forEach var="rev_board" items="${rev_board}">


				<div id="jb_table">
					<div class="row">
						<div class="cell col1">음식이름</div>
						<div class="cell col2">
							<input type="text" value="${rev_board.food_name}"
								required="required" readonly />
						</div>
					</div>
					<div class="row">
						<div class="cell col1">지점이름</div>
						<div class="cell col2">
							<input type="text" value="${rev_board.jijum_name}"
								required="required" readonly />
						</div>
					</div>
					<div class="row">
						<div class="cell col1">
							<label for="rev_satisfaction">평가하기</label>
						</div>
						<div class="cell col2">
							<select name="rev_satisfaction" id="rev_satisfaction">
								<c:forEach var="a" begin="1" end="5" step="1">
									<c:choose>
										<c:when test="${rev_board.rev_satisfaction==a}">
											<option value="${a}" selected>
										</c:when>
										<c:otherwise>
											<option value="${a}">
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${a==1}">★
								</c:when>
										<c:when test="${a==2}">★★
								</c:when>
										<c:when test="${a==3}">★★★
								</c:when>
										<c:when test="${a==4}">★★★★
								</c:when>
										<c:when test="${a==5}">★★★★★
								</c:when>
									</c:choose>

								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="cell col1">글쓴이</div>
						<div class="cell col2">
							<input type="text" name="id" id="id" value="${rev_board.id}"
								required="required" readonly>
						</div>
					</div>
					<div class="row">
						<div class="cell col1">
							<label for="rev_subject">제목</label>
						</div>
						<div class="cell col2">
							<input name="rev_subject" type="text" id="rev_subject"
								value="${rev_board.rev_subject}" required="required" />
						</div>
					</div>
					<div class="row">
						<div class="cell col3">
							<div id="summernote" style="">${rev_board.rev_content}</div>
							<textarea id="board_content" name="rev_content"></textarea>

						</div>
					</div>
				</div>




				<input type="hidden" name="rev_num" id="rev_num"
					value="${rev_board.rev_num}">
				<input type="hidden" name="connect_num" id="connect_num"
					value="${param.connect_num}" />
				<section id="commandCell">
					<input type="button" value="등록" onclick="formchk(boardform)">&nbsp;&nbsp;
					<input type="reset" value="다시쓰기" />
					<c:choose>
						<c:when test="${id_grade==2}">
							<a
								href="Rev_Board_detail.fc?rev_num=${rev_board.rev_num}&connect_num=${param.connect_num}&page=${nowPage}">이전목록으로</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:history.back()">이전목록으로</a>
						</c:otherwise>
					</c:choose>

				</section>
			</c:forEach>
		</form>

	</section>
	<!-- 게시판 등록 -->
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
</body>
</html>