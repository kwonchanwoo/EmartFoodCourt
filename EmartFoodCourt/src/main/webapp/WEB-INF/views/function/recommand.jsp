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
<link rel="stylesheet" href="../EmartFoodCourt/resources/css/main.css">

</head>
<body>
	<%@ include file="../nav.jsp" %>
	<form name="recom" action="/EmartFoodCourt/recom.fc" method=post>
		<input type="hidden" name="recom_check" id="recom_check" value="false" />

		<div class="content_area">
			<div class="content_wrap">
				<div class="line"></div>
				<div id="title">E-MART</div>
				<div class="chk_wrap">
					<select name="jijum_name" id="btn">
						<c:forEach var="list" items="${jijum_menu}">
							<c:choose>
								<c:when test="${list.jijum_name==param.jijum_name}">
									<option value="${list.jijum_name}" selected>${list.jijum_name}</option>
								</c:when>
								<c:otherwise>
									<option value="${list.jijum_name}">${list.jijum_name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> 
					<select name="food_category" id="btn">
						<c:forEach var="list" items="${food_menu}">
							<c:choose>
								<c:when test="${list.food_category==param.food_category}">
									<option value="${list.food_category}" selected>${list.food_category}</option>
								</c:when>
								<c:otherwise>
									<option value="${list.food_category}">${list.food_category}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> 
					<select name="choice" id="btn">
						<c:choose>
							<c:when test="${param.choice=='평점순'}">
								<option value="평점순" selected>평점순</option>
							</c:when>
							<c:otherwise>
								<option value="평점순">평점순</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${param.choice=='추천수'}">
								<option value="추천수" selected>추천수</option>
							</c:when>
							<c:otherwise>
								<option value="추천수">추천수</option>
							</c:otherwise>
						</c:choose>

					</select>
					<button id="btn">확인</button>
				</div>
	</form>
	<c:if test="${recom_check==true}">
		<p>왼쪽부터 지점명, 음식 종류, 평점순/추첨수를 선택 할 수 있습니다.</p>
		<p>방문하실 지점과 원하는 음식 종류를 골라주세요!</p>
	</c:if>
	<c:choose>
		<c:when test="${totalList != null}">
			<div id="title">추천 목록</div>
			<div id="food_img">
				<c:forEach var="list" items="${totalList}">
					
					<div id="content_1">
					
						<a
							href="/EmartFoodCourt/menuView_detail.fc?connect_num=${list.connect_num}">
							<img src="/EmartFoodCourt/resources/images/${list.food_image}"
							width="250px" height="250px" alt=""/>
						</a>이름 : <font color="green">${list.food_name}</font><br> 종류 :
						${list.food_category}<br> 평점 :
							
							
						 <c:choose>
							<c:when test="${list.avg_grade == null}">
									 평가없음<br>
								 </c:when>
							<c:otherwise>
								<font color="red">${list.avg_grade}</font><br>
							</c:otherwise>
						</c:choose> 

						좋아요수:
						<c:choose>
							<c:when test="${list.count_rec==0}">
							 	평가없음<br>
							 </c:when>
							<c:otherwise>
								<font color="blue">${list.count_rec}</font><br>
							</c:otherwise>
						</c:choose>	
					</div>

					<div id="content_2">
						<a
							href="/EmartFoodCourt/menuView_detail.fc?connect_num=${list.connect_num}">
							<img src="/EmartFoodCourt/resources/images/${list.food_image}"
							width="100px" height="100px" alt="" id="content2_img" />
						</a>
						<p>
							이름 : <font color="green">${list.food_name}</font>
						</p>
						<p>종류 : ${list.food_category}</p>
						<p>
							평점 :
							<c:choose>
								<c:when test="${list.avg_grade == null}">
							 평가없음
						 </c:when>
								<c:otherwise>
									<font color="red">${list.avg_grade}</font>
								</c:otherwise>
							</c:choose>
						</p>
						<p>
							좋아요수:
							<c:choose>
								<c:when test="${list.count_rec==0}">
						 	평가업음
						 </c:when>
								<c:otherwise>
									<font color="blue">${list.count_rec}</font>
								</c:otherwise>
							</c:choose>
						</p>
					</div>
				</c:forEach>
			</div>
		</c:when>
		<c:when test="${recom_check==false}">
			<div class="main_cont_wrap">아직 등록된 음식이없습니다.</div>
		</c:when>
	</c:choose>
	</div>
	</div>

	<h1>방문하실 지점과 원하는 음식 종류를 골라주세요~!</h1>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
</body>
</html>