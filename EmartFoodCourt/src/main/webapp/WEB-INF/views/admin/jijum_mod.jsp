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
<title>MVC 게시판 JSTL</title>
<style type="text/css">
#registForm {
	width: 500px;
	height: 610px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 450px;
}

.td_left {
	width: 150px;
	background: orange;
}

.td_right {
	width: 300px;
	background: skyblue;
}

#commandCell {
	text-align: center;
}
</style>

</head>
<body>
	<!-- 게시판 등록 -->
	<jsp:include page="../admin/admin.jsp" /><br>
	<section id="writeForm">
		<h2>지점 수정 페이지</h2>
		<form action="/EmartFoodCourt/jijumModPro.ad" method="post" name="boardform">
			<c:forEach var="total" items="${total}">
				<table>
					<tr>
						<td class="td_right"><input type="hidden" name="oldjijum_name"
							id="oldjijum_name" value="${total.jijum_name}" readonly /></td>
					</tr>
					<tr>
						<td class="td_left"><label for="jijum_name">지점이름 </label></td>
						<td class="td_right">
							<input type="text" name="jijum_name" id="jijum_name" value="${total.jijum_name}" required="required" />	
						</td>
					</tr>
					<tr>
						<td class="td_left"><label for="jijum_content">지점 주소</label></td>
						<td class="td_right"><input type="text" name="jijum_content"
							id="jijum_content" value="${total.jijum_content}"
							required="required" /></td>
					</tr>
					<tr>
						<td class="td_left"><label for="jijum_intro">지점 소개</label></td>
						<td><textarea id="jijum_intro" name="jijum_intro" cols="45"
								rows="15" required="required">${total.jijum_intro}</textarea></td>
					</tr>
				</table>
			</c:forEach>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp; <input
					type="reset" value="다시쓰기" /> <a href="javascript:history.back()">이전목록으로</a>
			</section>
			<input type="hidden" name="page" id="page" value="${nowPage}">
			<input type="hidden" name="img_check" id="img_check" value="true">
		</form>
	</section>
	<!-- 게시판 등록 -->
</body>
<!-- footer -->
<jsp:include page="../footer.jsp" />
<!-- //footer -->
</html>