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

#btn:hover {
	content: url('/EmartFoodCourt/resources/images/backbutton_after.jpg');
}
</style>
</head>
<body>
	<jsp:include page="../admin/admin.jsp" /><br>
	<section class="blog_w3ls py-5" id="more">
		<div class="container py-xl-5 py-lg-2">
			<div id="command">
				<c:if test="${total!=null}">
				<c:forEach var="total" items="${total}">
						<h1 class="tittle mb-sm-5 mb-4" id="search_menu">
							${total.food_name}<img
								src="/EmartFoodCourt/resources/images/${total.food_image}" width="150px"
								height="150px">
						</h1>
						<div class="col-lg-12 col-md-12 col-sm-12 mt-12">
							<div class="med-blog">
								<div class="blog-header">
									음식 설명 : ${total.food_content} 가격 : ${total.con_price} 음식 평점 :
									<c:choose>
										<c:when test="${total.avg_grade==0}">평가없음</c:when>
										<c:otherwise>${total.avg_grade}</c:otherwise>
									</c:choose>
									좋아요수
									<c:choose>
										<c:when test="${total.count_rec==0}">평가없음</c:when>
										<c:otherwise>${total.count_rec}</c:otherwise>
									</c:choose>
									<br> <a href="/EmartFoodCourt/foodList.ad?page=${nowPage}&choice=${param.choice}&search=${param.search}"><img
										src="/EmartFoodCourt/resources/images/backbutton_before.jpg" width="50px"
										height="50" id="btn"></a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</section>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
</body>
</html>