<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />

<jsp:include page="../checkfolder/login_check.jsp" />
<c:set var="nowPage" value="${param.page}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 JSTL</title>
</head>
<style>

table {
	margin-top: 25px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
	width: 500px;
	border: 1px solid gray;
	text-align: center;
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
<body>
	<c:choose>
		<c:when test="${id_grade==2}">
			<jsp:include page="../admin/admin.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="../nav.jsp" />
		</c:otherwise>
	</c:choose>

	<table>
		<c:choose>
			<c:when test="${memberList != null }">
				<c:forEach var="memberList" items="${memberList}">
					<tr>
						<td>아이디</td>
						<td>${memberList.id}</td>
					</tr>
					<tr>
						<td>이름</td>
						<td>${memberList.name}</td>
					</tr>
					<tr>
						<td>나이 :</td>
						<td>${memberList.age}</td>
					</tr>
					<tr>
						<td>등급</td>
						<c:choose>
							<c:when test="${memberList.grade==1}">
								<td>일반회원</td>
							</c:when>
							<c:when test="${memberList.grade==2}">
								<td>관리자</td>
							</c:when>
						</c:choose>
		
					</tr>
					<tr>
						<td>성별</td>
						<td>${memberList.gender}</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td>${memberList.email}</td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td>${memberList.zip}</td>
					</tr>
					<tr>
						<td>상세주소</td>
						<td>${memberList.address}</td>
					</tr>
					<tr>
						<c:choose>
							<c:when test="${nowPage!=null && id_grade==2}">
								<td colspan="2"><a href="/EmartFoodCourt/memberList.ad?page=${nowPage}&choice=${param.choice}&search=${param.search}">리스트로
										돌아가기</a></td>
							</c:when>
							<c:when test="${id_grade==2}">
								<td colspan="2"><a href="javascript:history.back()">뒤로가기</a></td>
							</c:when>
							<c:otherwise>
								<td colspan="2"><a href="memberMyPage.me?id=${param.id}">뒤로가기</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p style="text-align:center; margin-top:30px;">존재하지 않는 사용자입니다.</p>
			</c:otherwise>
		</c:choose>
	</table>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
</body>
</html>