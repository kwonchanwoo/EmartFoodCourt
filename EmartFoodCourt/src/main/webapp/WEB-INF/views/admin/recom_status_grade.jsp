<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />
<jsp:include page="../checkfolder/admin_check.jsp" />
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
</style>
</head>
<body>
	<jsp:include page="../admin/admin.jsp" /><br>
	<div id="command">
		<c:if test="${totalList!=null}">

			<table>
				<tr>
					<td colspan=5 class="td_title">음식 평점 세부사항</td>
				</tr>
				<tr>
					<td bgcolor="green"><font color="white">아이디</font></td>
					<td bgcolor="green"><font color="white">평균</font></td>
					<td bgcolor="green"><font color="white">음식이름</font></td>
					<td bgcolor="green"><font color="white">지점이름</font></td>
					<td bgcolor="green"><font color="white">등록 날짜</font></td>
				</tr>

				<c:forEach var="list" items="${totalList}">
					<tr>
						<td>${list.id}</td>
						<td>${list.grade_grade}</td>
						<td><a
							href="/EmartFoodCourt/menuView_detail.fc?connect_num=${list.connect_num}">${list.food_name}</a></td>
						<td>${list.jijum_name}</td>
						<td>${list.grade_date}</td>
					</tr>
				</c:forEach>

			</table>
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