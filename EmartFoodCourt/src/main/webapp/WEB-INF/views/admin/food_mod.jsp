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
		<h2>음식 수정 페이지</h2>
		<form action="/EmartFoodCourt/foodModPro.ad" method="post"
			enctype="multipart/form-data" name="boardform">
			<table>
				<tr>
					<td><input type="hidden" name="connect_num" id="connect_num"
						value="${total.connect_num}"></td>
					<td><input type="hidden" name="old_food_name"
						id="old_food_name" value="${total.food_name}"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="food_name">음식이름 </label></td>
					<td class="td_right"><input type="text" name="food_name"
						id="food_name" value="${total.food_name}" required="required" /></td>
				</tr>
				<tr>
					<td class="td_left"><label for="food_content">음식 종류</label></td>
					<td class="td_right"><select name="food_category">
							<c:forEach var="food" items="${food}">
								<c:choose>
									<c:when test="${total.food_category == food.food_category}">
										<option value="${food.food_category}" selected>${food.food_category}</option>
									</c:when>
									<c:otherwise>
										<option value="${food.food_category}">${food.food_category}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="td_left"><label for="jijum_name">지점 이름</label></td>
					<td class="td_right"><select name="jijum_name">
							<c:forEach var="jijum" items="${jijum}">
								<c:choose>
									<c:when test="${total.jijum_name == jijum.jijum_name}">
										<option value="${jijum.jijum_name}" selected>${jijum.jijum_name}</option>
									</c:when>
									<c:otherwise>
										<option value="${jijum.jijum_name}">${jijum.jijum_name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>


				<tr>
					<td class="td_left">이미지 변경 여부</td>
					<td class="td_right"><label for="image_modify">O<input
							type="radio" name="radio_btn" onclick="radio_On('fd_img')"></label>
						<label for="image_disabled">X<input type="radio"
							name="radio_btn" onclick="radio_Off('fd_img')" checked></label></td>
				</tr>
				<tr id="fd_img" style="display: none">
					<td class="td_left"><label for="food_image">음식 이미지</label></td>
					<td class="td_right"><input type="file" name="food_image"
						id="food_image" accept="image/*">
				</tr>
				<tr id="fd_img2">
					<td class="td_left"><label for="food_image">음식 이미지 제목</label></td>
					<td class="td_right"><input type="text" name="food_image"
						id="food_image" value="${total.food_image}" readonly></td>
				</tr>

				<tr>
					<td class="td_left"><label for="con_price">음식 가격</label></td>
					<td class="td_right"><input name="con_price" type="number"
						id="con_price" value="${total.con_price}" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="food_content">음식 소개</label></td>
					<td><textarea id="food_content" name="food_content" cols="45"
							rows="15" required="required">${total.food_content}</textarea></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp; <input
					type="reset" value="다시쓰기" /> <a href="javascript:history.back()">이전목록으로</a>
			</section>
			<input type="hidden" name="page" id="page" value="${nowPage}">
			<input type="hidden" name="img_check" id="img_check" value="true">
		</form>
	</section>
	<!-- 게시판 등록 -->
	<script>
		function radio_On(id) {
			if (id == "fd_img") {
				document.all["fd_img"].style.display = "";
				document.all["fd_img2"].style.display = 'none';
				document.all["img_check"].value = "false";
			}
		}
		function radio_Off(id) {
			if (id == "fd_img") {
				document.all["fd_img"].style.display = 'none';
				document.all["fd_img2"].style.display = "";
				document.all["img_check"].value = "true";
			}
		}
	</script>
</body>
<!-- footer -->
<jsp:include page="../footer.jsp" />
<!-- //footer -->
</html>