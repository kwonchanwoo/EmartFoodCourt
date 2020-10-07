<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />
<c:set var="IdCheck" value="${true}" />
<c:set var="PwdCheck" value="${true}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp" %>
<meta charset="UTF-8">
<title>회원 관리 시스템 로그인 페이지</title>
<style>
table {
	margin: auto;
	width: 320px;
	border: 1px solid gray;
	text-align: center;
}

.td_title {
	font-weight: bold;
	font-size: x-large;
}

#logForm {
	height: 600px;
}
#logoutForm{
	text-align:center;
	margin:auto;
}
</style>
</head>
<body>
	<%@ include file="../nav.jsp" %>
	<c:choose>
		<c:when test="${sessionScope.id==null}">
			<div id="logForm">
				<form name="loginform" action="/EmartFoodCourt/memberLoginAction.me"
					method="post">
					<table>
						<tr>
							<td colspan="2" class="td_title">로그인 페이지</td>
						</tr>
						<tr>
							<td><label for="id">아이디</label></td>
							<td><input type="text" name="id" id="id" size="15"></td>
						</tr>
						<tr>
							<td><label for="pass">비밀번호</label></td>
							<td><input type="password" name="pass" id="pass" size="15"></td>
						</tr>
						<tr>
							<td colspan="2"><a href="javascript:loginform.submit()"
								id="col">로그인</a>&nbsp;&nbsp; <a href="#"
								onclick="window.open('/EmartFoodCourt/memberJoinForm.me?openInit=true','','width=500,height=600')"
								class="nav-style" id="col">회원가입</a></td>
						</tr>
						<tr>
							<td colspan="2">
								<button type="button" id="search_id">아이디 찾기</button>
								<button type="button" id="search_pwd">비밀번호 찾기</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</c:when>
		<c:otherwise>
				<div id="logoutForm">
					${sessionScope.id}님 환영합니다! <a href="/EmartFoodCourt/memberLogout.me">로그아웃</a>
				</div>
		</c:otherwise>
	</c:choose>
	<jsp:include page="../footer.jsp" />
</body>
<script>
var search_id = document.querySelector('#search_id');
var search_pwd = document.querySelector('#search_pwd');

search_id.onclick = function(){
	window.open("/EmartFoodCourt/search_id.me?IdCheck="+${IdCheck}+"",'','width=500,height=200');
}
search_pwd.onclick =function(){
	window.open("/EmartFoodCourt/search_pwd.me?PwdCheck="+${PwdCheck}+"",'','width=500,height=200');
}
</script>

</html>