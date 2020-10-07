<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />
<jsp:include page="../checkfolder/admin_check.jsp" />
<c:set var="nowPage" value="${requestScope.page}" />
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
		<h2>음식 등록 페이지</h2>
		<form action="/EmartFoodCourt/foodAddPro.ad" method="post"
			enctype="multipart/form-data" name="boardform">
			<table>
				<tr>
					<td class="td_left"><label for="food_name">음식이름 </label></td>
					<td class="td_right"><input type="text" name="food_name"
						id="food_name" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="food_content">음식 종류</label></td>
					<td class="td_right"><select name="food_category">
							<c:forEach var="food" items="${food}">
								<option value="${food.food_category}">${food.food_category}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="td_left"><label for="jijum_name">지점 이름</label></td>
					<td class="td_right"><select name="jijum_name">
							<c:forEach var="jijum" items="${jijum}">
								<option value="${jijum.jijum_name}">${jijum.jijum_name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="td_left"><label for="food_image">음식 이미지</label></td>
					<td class="td_right"><input type="file" name="food_image"
						id="food_image" accept="image/*"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="con_price">음식 가격</label></td>
					<td class="td_right"><input name="con_price" type="number"
						id="con_price" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="food_content">음식 소개</label></td>
					<td><textarea id="food_content" name="food_content" cols="45"
							rows="15"></textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp; <input
					type="reset" value="다시쓰기" /> <a href="javascript:history.back()">이전목록으로</a>
			</section>
		</form>
	</section>
	<!-- 게시판 등록 -->
</body>
<!-- footer -->
<jsp:include page="../footer.jsp" />
<!-- //footer -->
</html>