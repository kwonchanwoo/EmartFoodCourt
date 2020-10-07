<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp" %>
<meta charset="UTF-8">
<title>MVC 게시판 JSTL</title>
<link rel="stylesheet" href="../EmartFoodCourt/resources/css/rev_board_detail.css">
</head>
<body>


	<%@ include file="../nav.jsp" %><br>
	<c:choose>
		<c:when test="${rev_board != null }">
			<c:forEach var="rev_board" items="${rev_board}">
				<section id="articleForm">
					<h2>글 내용 싱세보기</h2>
					<section id="basicInfoArea">
						제목 : ${rev_board.rev_subject} <br> 작성자: ${rev_board.id}<br>	
						<c:if test="${rev_board_image != null }">
						<c:forEach var="rev_board_image" items="${rev_board_image}">
							<img src="/EmartFoodCourt/resources/images/${rev_board_image.img_name}" style="width:auto; height:auto; max-width:100%; max-height:100%;"><br>
						</c:forEach>
						</c:if>
					</section>
					<section id="articleContextArea">${rev_board.rev_content}
					</section>
				</section>
				<section id="commandList">
					<c:if test="${sessionScope.id!=null}">
						<c:if
							test="${rev_board.id == sessionScope.id || sessionScope.id_grade==2}">
							 <a
								href="Rev_Board_modifyForm.fc?rev_num=${rev_board.rev_num}&connect_num=${connect_num}&page=${nowPage}">[수정]</a> 
							
							<input type="button" onclick="chkForm()" value="삭제">
						</c:if>
					</c:if>
					<c:choose>
						<c:when test="${connect_num!=null}">
							<a
								href="/EmartFoodCourt/menuView_detail.fc?connect_num=${connect_num}&page=${nowPage}">[목록]</a>
			&nbsp;&nbsp;
			</c:when>
						<c:otherwise>
							<a
								href="/EmartFoodCourt/menuView_detail.fc?connect_num=1&page=${nowPage}">[목록]</a>
			&nbsp;&nbsp;
			</c:otherwise>
					</c:choose>
				</section>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<script>
				alert("잘못된 설정입니다!");
				location.href = "/EmartFoodCourt/memberLogin.me";
			</script>
		</c:otherwise>
	</c:choose>
	<script>
		function chkForm() {
			var retVal = confirm("정말 삭제하시겟습니까?");

			if (retVal == true) {
				
				location.href = "/EmartFoodCourt/Rev_Board_delete.fc?rev_num=${rev_num}&connect_num=${connect_num}&page=${nowPage}";
			} else {
				alert("취소되었습니다.");
			}
		}
	</script>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
</body>
</html>