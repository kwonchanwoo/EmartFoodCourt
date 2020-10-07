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
<title>Insert title here</title>
</head>
<body>
	<form name="f" action="/EmartFoodCourt/mailSend" method="post">
		<input type="text" name="sender" value="hellperslayer@gmail.com"
			hidden="hidden"> <input type="text" name="receiver"
			value="${email}" hidden="hidden" /> <input type="text"
			name="subject" value="문외하신 비밀번호 찾기 결과입니다." hidden="hidden" />
		<div id="myarea" style="visibility: hidden">
			<textarea name="content" cols="40" rows="20">${id}님의  임시 비밀번호는 ${pass}입니다.</textarea>
		</div>
	</form>
	<script>
		f.submit();
	</script>
</body>
</html>

