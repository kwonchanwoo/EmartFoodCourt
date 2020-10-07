<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
<!-- 테이블 테두리 css -->
table {
	border : 1px solid black;
}

table th, td {
	border : 1px solid black;
}
</style>
</head>
<body>
	<!-- controller에서 설정한 str변수 -->
<%-- 	${str }
	${tb }
	${cnt } --%>
	<table>
		<c:forEach var="list" items="${list }">
			<tr>
				<td>
					번호 : ${list.num }
				</td>
				<td>
					제목 : ${list.subject }
				</td>
				<td><a href="#">삭제하기</a></td>
				<td><a href="#">수정하기</a></td>
			</tr>
			<tr>
				<td>
					내용 : ${list.content }
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>