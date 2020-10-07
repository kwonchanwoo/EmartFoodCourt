<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String count = request.getParameter("count");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp" %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form name="search_form" action="/EmartFoodCourt/search_pwd.me"
		method="post">
		<div id="search">
			<label for="id">아이디를 입력해주세요!</label><input type="text" name="id" /> <input
				type="text" name="PwdCheck" value="false" hidden="hidden"  maxlength="12">
			<!-- <a href="#" onclick="chkForm(document.search_form);">확인</a> -->
			<button type="button" onclick="chkForm(document.search_form)">확인</button>
		</div>
	</form>

	<script>
		function chkForm(f) {
			if (f.id.value.trim() == "") {
				alert('값을 입력해주세요!');
				return false;
			}
			f.submit();
		}
	</script>
	<!-- var search_id = document.querySelector('#search_id');
var search_pwd = document.querySelector('#search_pwd');

search_id.onclick = function(){
	window.open('search_id.jsp','','width=500,height=200');
}
search_pwd.onclick =function(){
	window.open('search_pwd.jsp','','width=300,height=200');
} -->

</body>

</html>