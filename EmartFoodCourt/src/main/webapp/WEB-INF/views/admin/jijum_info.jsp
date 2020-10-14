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

table {
	margin-top: 25px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
	width: 400px;
	border: 1px solid gray;
	text-align: center;
}

#btn:hover {
	content: url('/EmartFoodCourt/resources/images/backbutton_after.jpg');
}
@media ( max-width : 505px) {
	table {
	margin-top: 25px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
	width: 100%;
	border: 1px solid gray;
	text-align: center;
}
}
</style>
</head>
<body>
	<jsp:include page="../admin/admin.jsp" /><br>
	<div id="command">
		<c:if test="${total!=null}">
			<c:forEach var="total" items="${total}">
			<h1>${total.jijum_name}</h1>

			<table>
				<tr>
					<th>지점 주소</th>
					<th>한식</th>
					<th>중식</th>
					<th>일식</th>
					<th>양식</th>
					<th>합계</th>
				</tr>
				<tr>
					<td>${total.jijum_content}</td>
					<td><c:choose>
							<c:when test="${total.count_kr!=0}">
								<a
									href="/EmartFoodCourt/menuView.fc?jijum_name=${total.jijum_name}&food_category=한식">${total.count_kr}</a>
							</c:when>
							<c:otherwise>
				${total.count_kr}
			</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${total.count_ch!=0}">
								<a
									href="/EmartFoodCourt/menuView.fc?jijum_name=${total.jijum_name}&food_category=중식">${total.count_ch}</a>
							</c:when>
							<c:otherwise>
			${total.count_ch}
		</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${total.count_jp!=0}">
								<a
									href="/EmartFoodCourt/menuView.fc?jijum_name=${total.jijum_name}&food_category=일식">${total.count_jp}</a>
							</c:when>
							<c:otherwise>
			${total.count_jp}
		</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${total.count_en!=0}">
								<a
									href="/EmartFoodCourt/menuView.fc?jijum_name=${total.jijum_name}&food_category=양식">${total.count_en}</a>
							</c:when>
							<c:otherwise>
			${total.count_en}
		</c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${total.count_total!=0}">
								<a href="/EmartFoodCourt/menuView.fc?jijum_name=${total.jijum_name}">${total.count_total}</a>
							</c:when>
							<c:otherwise>
			${total.count_total}
		</c:otherwise>
						</c:choose></td>

				</tr>
			</table>
			<br>
			<h3>
				<font color="green">지점 소개 </font>
			</h3> ${total.jijum_intro}<br>
			<a href="/EmartFoodCourt/jijumList.ad?page=${nowPage}&choice=${param.choice}&search=${param.search}"><img
				src="/EmartFoodCourt/resources/images/backbutton_before.jpg" width="50px"
				height="50px" id="btn"></a>
			<br>
			</c:forEach>
		</c:if>
		
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
</body>
</html>