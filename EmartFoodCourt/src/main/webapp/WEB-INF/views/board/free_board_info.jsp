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

<link rel="stylesheet" href="resources/css/common_free_board.css">
<link rel="stylesheet" href="resources/css/free_board_info.css">



<meta charset="UTF-8">
<title>Insert title here</title>



</head>
<body>
	<jsp:include page="../board/free_board_nav.jsp"/>
	
	<div class="free_container">
		<jsp:include page="../board/sidebar.jsp" />
		<c:forEach var="free_board" items="${free_board}">
			<div class="header">
				<div id="board_title">${free_board.board_subject}</div>
				<div id="board_sub1">${free_board.board_category}|
					${free_board.board_date} | ${free_board.id}</div>
				<div id="board_sub2">조회 ${free_board.board_readcount } | 댓글
					${free_board.ct} | 추천 ${free_board.vote_up}</div>
			</div>

			<div id="table">

				<div class="wrap">${free_board.board_content}</div>
			</div>
			<div id="vote">
				
					<a
						href="/EmartFoodCourt/boardList_vote_up.bd?board_num=${free_board.board_num}&id=${free_board.id}"><img
						src="/EmartFoodCourt/resources/images/icon-vote-up.png">${free_board.vote_up}</a>
				
				
					<a
						href="/EmartFoodCourt/boardList_vote_down.bd?board_num=${free_board.board_num}&id=${free_board.id}"><img
						src="/EmartFoodCourt/resources/images/icon-vote-up.png"
						id="vote_down_img">${free_board.vote_down}</a>
				

			</div>
			<c:if test="${id_grade==2 || free_board.id==sessionScope.id}">
				<div id="etc_form">
					<a
						href="/EmartFoodCourt/boardList_modForm.bd?board_num=${free_board.board_num}&id=${free_board.id}">수정하기</a>
					<a href="#"
						onclick="board_delete_check('${free_board.board_num}','${free_board.id}')">삭제하기</a>

				</div>
			</c:if>
			<div id="reply">
				<div class="reply_header">
					<div id="reply_font">댓글</div>
					<div id="reply_count">${free_board.ct}</div>
					개
					<div id="reply_fresh"></div>
					<a href="javascript:document.location.reload();"><img
						src="/EmartFoodCourt/resources/images/icon-refresh@2x.png"
						width="20px" height="20px">새로고침</a>
				</div>
			</div>
			<!-- <div id="reply_header2">
			인기순&nbsp; 최신순
			</div> -->



			<c:if test="${sessionScope.id!=null}">
				<div class="reply_write_form">
					<form action="/EmartFoodCourt/boardList_reply_write.bd"
						enctype="multipart/form-data" method="post" id="reply_form">
						<input type="hidden" name="id" value="${sessionScope.id}" readonly><br>
						<input type="hidden" name="board_num"
							value="${free_board.board_num}">
						<div id="reply_content_div">
							<input type="text" name="reply_content" placeholder="댓글을 입력해주세요."
								size="66" style="height: 40px;" maxlength="1000">
						</div>
						
						<div class="reply_picture">
							<input type="file" name="reply_file" value="사진" accept="image/*">
						</div>
						<div class="reply_submit">
							<input type="button" onclick="upload();" value="등록">
						</div>
					</form>
				</div>
			</c:if>

			<div id="reply_content_form">
				<c:choose>
					<c:when test="${free_board_reply!=null}">
						<c:set var="count" value="-1" />

						<c:forEach var="list" items="${free_board_reply}"
							varStatus="index">
							<c:set var="count" value="${count + 1}" />
							<div class="reply_content">
								<div class="reply_content_1">

									<c:if test="${list.reply_re_id!=null}">
										<img src="/EmartFoodCourt/resources/images/korean.png">
									</c:if>
									<c:choose>
										<c:when test="${list.reply_re_lev==2}">
											<font color="#FC400A">${list.id}</font>
										</c:when>
										<c:otherwise>
											<b>${list.id}</b>
										</c:otherwise>
									</c:choose>
									&nbsp;|&nbsp;${list.reply_date}
								</div>
								<div class="reply_content_2">
									<div class="reply_img">

										<c:choose>
											<c:when
												test="${list.reply_file!=null && fn:trim(list.reply_file)!=''}">
												<img
													src="/EmartFoodCourt/resources/images/${list.reply_file}"
													width="300px" height="300px">
											</c:when>
										</c:choose>
									</div>
									<c:if test="${list.reply_re_id!=null}">
										<div id="bg3">${list.reply_re_id}</div>
									</c:if>
									${list.reply_content}
								</div>
								<c:if test="${sessionScope.id!=null}">
									<div class="reply_add">
										<a href="javascript:reply_write_create(${count},1,'${list.reply_content}');">답글쓰기</a>&nbsp;

										<c:if test="${list.id==sessionScope.id || id_grade==2}">
											<a href="javascript:reply_write_create(${count},2,'${list.reply_content}');">수정</a>&nbsp;
													<c:choose>
												<c:when test="${list.reply_re_ck==0 }">
													<a
														href="javascript:reply_delete('${list.id}',${list.reply_num},${list.board_num});">삭제</a>
												</c:when>

											</c:choose>

										</c:if>


										<div class="reply_write_form_hidden" style="display: none">
											<form action="/EmartFoodCourt/boardList_reply_write.bd"
												enctype="multipart/form-data" method="post"
												class="new_reply_form">
												<input type="hidden" name="id" id=""
													value="${sessionScope.id}" readonly> <input
													type="hidden" name="board_num" id="board_num"
													value="${free_board.board_num}" readonly>
												
													<input type="text" name="reply_content" class="reply_content_class"
														placeholder="댓글을 입력해주세요." size="66" style="height: 40px;"
														maxlength="1000">
												
												
												<input type="hidden" name="reply_re_id" value="${list.id}"
													readonly> <input type="hidden" name="reply_re_ref"
													value="${list.reply_re_ref}" readonly> <input
													type="hidden" name="reply_re_seq"
													value="${list.reply_re_seq}" readonly> <input
													type="hidden" name="reply_num" id="reply_num"
													value="${list.reply_num}" readonly>
												<div class="reply_picture">
													<input type="file" name="reply_file" value="사진"
														accept="image/*">
												</div>
												<div class="reply_submit_2">
													<input type="button" onclick="reply_upload(${count});"
														value="등록">
												</div>
												<div class="reply_modify">
													<input type="button" onclick="reply_modify(${count});"
														value="수정">
												</div>
											</form>
										</div>
									</div>
								</c:if>
								<c:if test="${!index.last}">
									<hr>
								</c:if>
							</div>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</c:forEach>
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->

	<script type="text/javascript" src="resources/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.form.min.js"></script>
	<script type="text/javascript" src="resources/js/free_board_info.js"></script>
 



</body>
</html>