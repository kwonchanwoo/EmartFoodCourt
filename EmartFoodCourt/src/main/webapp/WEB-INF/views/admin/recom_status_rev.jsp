<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<c:choose>
<c:when test="${id_grade==2}">
<jsp:include page="../checkfolder/admin_check.jsp" />
</c:when>
<c:otherwise>
<jsp:include page="../checkfolder/login_check.jsp" />
</c:otherwise>
</c:choose>
<c:set var="nowPage" value="${param.page}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#command {
	text-align: center;
}
#btn:hover {
	content: url('/EmartFoodCourt/images/backbutton_after.jpg');
}

table {
	margin: auto;
	width: 100%;
	border: 1px solid gray;
	text-align: center;
}

.td_title {
	font-weight: bold;
	font-size: x-large;
}

#pageList {
	margin: auto;
	width: 100%;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 500px;
	text-align: center;
}
@media ( max-width : 819px) {
	#rev_content1{
	display:none;
	}
	#rev_content2{
	display:"";
	}
	.container {
		max-width: 540px;
	}
}
@media(min-width: 819px){
	#rev_content1{
		display:"";
	}
	#rev_content2{
		display:none;
	}
}
</style>
</head>
<body>
	<c:choose>
	<c:when test="${id_grade==2}">
		<jsp:include page="../admin/admin.jsp" />
	</c:when>
	<c:otherwise>
		<jsp:include page="../nav.jsp"/>
	</c:otherwise>
	</c:choose>
	<div id="command">
		<c:if test="${totalList!=null}">
			<div id="rev_content1">
				<table>
					<tr>
						<td colspan=8 class="td_title">후기 게시판 세부사항</td>
					</tr>
					<tr>
						<td bgcolor="green"><font color="white">아이디</font></td>
						<td bgcolor="green"><font color="white">게시판 제목</font></td>
						<td bgcolor="green"><font color="white">음식 이름</font></td>
						<td bgcolor="green"><font color="white">지점 이름</font></td>
						<td bgcolor="green"><font color="white">게시판 내용</font></td>
						<td bgcolor="green"><font color="white">게시판 평점</font></td>
						<td bgcolor="green"><font color="white">게시판 조회수</font></td>
						<td bgcolor="green"><font color="white">게시판 등록날짜</font></td>
					</tr>
					<c:choose>
						<c:when test="${totalList ne null }">
							<c:forEach var="list" items="${totalList}">
								<tr>
									<td>${list.id}</td>
									<td>${list.rev_subject}</td>
									<td><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${list.connect_num}">${list.food_name}</a></td>
									<td>${list.jijum_name}</td>
									<td>${fn:substring(list.rev_content,0,8)}...</td>
									<td>${list.rev_satisfaction}</td>
									<td>${list.rev_readcount}</td>
									<td>${list.rev_date}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<script>
								location.href = "/EmartFoodCourt/index.jsp";
							</script>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			<div id="rev_content2">
				<table>
					<tr>
						<td colspan=8 class="td_title">후기 게시판 세부사항</td>
					</tr>
					<tr>
						<td bgcolor="green"><font color="white">아이디</font></td>
						<td bgcolor="green"><font color="white">제목</font></td>
						<td bgcolor="green"><font color="white">음식이름</font></td>
						<td bgcolor="green"><font color="white">지점이름</font></td>
						<td bgcolor="green"><font color="white">내용</font></td>
						<td bgcolor="green"><font color="white">평점</font></td>
					</tr>
					<c:choose>
						<c:when test="${totalList ne null }">
							<c:forEach var="list" items="${totalList}">
								<tr>
									<td>${list.id}</td>
									<td>${fn:substring(list.rev_subject,0,3)}...</td>
									<td><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${list.connect_num}">${list.food_name}</a></td>
									<td>${list.jijum_name}</td>
									<td>${fn:substring(list.rev_content,0,5)}...</td>
									<td>${list.rev_satisfaction}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<script>
								location.href = "/EmartFoodCourt/index.jsp";
							</script>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			<br>
			<a href="javascript:history.back();"><img
				src="/EmartFoodCourt/images/backbutton_before.jpg" width="50px"
				height="50px" id="btn"></a>
			<br>

		</c:if>
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
</body>
</html>