<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%-- <%@ page import="vo.PageInfo"%> --%>
<%-- <%@ page import="function.vo.Total"%> --%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<fmt:requestEncoding value="utf-8" />
<%

/* 	ArrayList<Total> totalList = (ArrayList<Total>) request.getAttribute("menuView"); */
	/* String[] menu_view = (String[]) request.getAttribute("menu_view"); */

/* 	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo"); */
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp" %>
<%@ include file="../nav.jsp" %>
 <link rel="stylesheet" href="/EmartFoodCourt/resources/css/bootstrap.css">

<meta charset="UTF-8">
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="keywords"
	content="Food Blog Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" /> 
<meta charset="UTF-8">
<title>메뉴 소개</title>

<style>
@import
	url('https://fonts.googleapis.com/css?family=Dancing+Script&display=swap')
	;

/* .roundArea {
	width: 100%;
	padding: 11px 15px;
	background: url(resources/images/common/bg_middle_round.gif) 0 0
		repeat-y;
	border: 1px solid black;
} */

.roundTop {
	height: 3px;
	width: 754px;
	margin: 11px 0 0 0;
	font-size: 0;
	background: url(resources/images/common/bg_top_round.gif) no-repeat;
}

.roundBottom {
	height: 3px;
	width: 754px;
	margin: 0 0 17px 0;
	font-size: 0;
	background: url(resources/images/common/bg_bottom_round.gif) no-repeat;
}

ul li {
	list-style: none;
}

h1:hover {
	color: yellow;
}

#menu_icon:hover {
	content: url('/EmartFoodCourt/resources/images/menu_view_after.jpg');
}

#back {
	margin: 0 auto;
	text-align: center;
}

#back:hover {
	background-color: yellow;
}

.ghost_btn {
	background-color: #f1f1f1;
	padding: 10px;
	color: #333;
	border: 1px solid black;
	border-radius: 10px;
	font-size: 20px;
	font-family: 'Dancing Script', cursive;
}

.ghost_btn:hover {
	background-color: #333;
	color: #fff;
}

#search_menu a {
	color: #555;
}

.menu_subject {
	float: left;
	width: 100%;
	text-align: center;
	margin-auto: 0;
	font-size: 40px;
}
</style>


</head>
<body>


	<form name="menuView" action="/EmartFoodCourt/menuView.fc"
		method="post" id="menu_form">
		<h4>지점별 · 카테고리별 모든 메뉴를 만나보세요</h4>
		<div class="roundArea">

			<table>
				<tr>
					<td><select name="jijum_name">

							<option value="all" style="background: #5cb85c; color: #fff;">전체선택</option>
							<c:forEach var="list" items="${jijum_category}">
								<c:choose>
									<c:when test="${list.jijum_name==param.jijum_name}">
										<option value="${list.jijum_name}" selected
											style="background: red; color: #fff;">${list.jijum_name}</option>
									</c:when>
									<c:otherwise>
										<option value="${list.jijum_name}"
											style="background: #5cb85c; color: #fff;">${list.jijum_name}</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
					</select></td>
				<tr>
			</table>

			<h4>종류별 검색</h4>
			<table>
				<tr>
					<c:forEach var="list" items="${food_category}">
						<td>
							<h4>
								<input type="checkbox" name="food_category" id="food_category"
									value="${list.food_category}" /><label for="food_category">${list.food_category}</label>
							</h4>
						</td>
					</c:forEach>
				</tr>
			</table>
			<a href="#" class="searchBtn" onclick="menuView.submit()"><img
				src="../EmartFoodCourt/resources/images/btn_search_all.gif"
				alt="검색하기" /></a> 
		</div>
	</form>



	<!-- more -->
	<section class="blog_w3ls py-5" id="more">
		<div class="menu_subject">메뉴판</div>
		<div class="container py-xl- py-lg-5 py-md-5 py-sm-5 py-xs-5">
			<c:choose>
				<c:when test="${fn:length(menu_view)==1}">

					<h3 class="tittle mb-sm-5 mb-4" id="search_menu">
						<c:forEach var="list" items="${menu_view}">
							<h1>${list.jijum_name }</h1>
						</c:forEach>
						<%--  <a href="/EmartFoodCourt/menuView.fc?jijum_name=${menu_view.jijum_name}">${menu_view.jijum_name}</a>  --%>
					</h3>

					<div class="row">
						<c:forEach var="list" items="${totalList}">


							<!-- blog grid -->
							<div class="col-lg-4 col-md-6 mt-4">
								<div class="med-blog">
									<div class="blog-header">
										<a
											href="/EmartFoodCourt/menuView_detail.fc?connect_num=${list.connect_num}">
											<img width="150px" height="150px"
											src="/EmartFoodCourt/resources/images/${list.food_image}" />
										</a> <a
											href="/EmartFoodCourt/menuView_detail.fc?connect_num=${list.connect_num}">
											<input type="button" value="Read More" class="ghost_btn">
										</a>


										<div class="blog-body bg-wh p-4">
											<a
												href="/EmartFoodCourt/menuView_detail.fc?connect_num=${list.connect_num}">${list.food_name}
												<c:if test="${list.rev_count>0}">
													[<font color="green"> ${list.rev_count} </font>]
												</c:if>
											</a> &nbsp;
											<c:choose>
												<c:when test="${list.avg_grade == null }">
													<font color="#088A85">평점 미등록</font>&nbsp;
												</c:when>
												<c:otherwise>
													<font color="#088A85">평점 ${list.avg_grade}</font>&nbsp;
												</c:otherwise>
											</c:choose>
											<font
												color="#AEB404">좋아요 ${list.count_rec}<br></font> 지점
											${list.jijum_name}&nbsp;${list.food_category} <br>
										</div>
									</div>
								</div>
							</div>


						</c:forEach>
					</div>

				</c:when>
				<c:when test="${fn:length(menu_view) > 1}">
					<c:forEach var="list" items="${menu_view}">
						<div class="col-lg-12 col-md-12 mt-12">
							<hr style="border: solid 3px red;">
							<h3>
								<a
									href="/EmartFoodCourt/menuView.fc?jijum_name=${list.jijum_name }">${list.jijum_name }</a>
							</h3>
							<c:set var="count" value="0" />

							<div class="row">
								<c:set var="doneLoop" value="false" />

								<c:forEach var="tlist" items="${totalList}">

									<c:if test="${count==3 }">
										<c:set var="doneLoop" value="true" />
									</c:if>
									<c:if test="${not doneLoop }">

										<c:if test="${list.jijum_name eq tlist.jijum_name }">
											<c:set var="count" value="${count+1 }" />
											<!-- blog grid -->
											<div class="col-lg-4 col-md-6 mt-4">
												<div class="med-blog">
													<div class="blog-header">
														<a
															href="/EmartFoodCourt/menuView_detail.fc?connect_num=${tlist.connect_num}">
															<img width="150px" height="150px"
															src="/EmartFoodCourt/resources/images/${tlist.food_image}" />
														</a> <a
															href="/EmartFoodCourt/menuView_detail.fc?connect_num=${tlist.connect_num}">
															<input type="button" value="Read More" class="ghost_btn">
														</a>
														<div class="blog-body bg-wh p-4">
															<a
																href="/EmartFoodCourt/menuView_detail.fc?connect_num=${tlist.connect_num}">${tlist.food_name}</a>
															${tlist.jijum_name}${tlist.food_category} <br>
														</div>
													</div>
												</div>
											</div>
										</c:if>


									</c:if>
								</c:forEach>

							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<h1 id="search_menu">출력값이 없습니다!</h1>
				</c:otherwise>
			</c:choose>
		</div>
	</section>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
   <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>   
</body>
</html>