<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/EmartFoodCourt/resources/css/bootstrap.css">
<link rel="stylesheet" href="/EmartFoodCourt/resources/css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="resources/css/free_board_nav.css">

<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
<div id="mask"></div>
<div class="window2">
				
					<a href="#" class="window2_close_button">X</a>
				
				<div id="login">
				<c:choose>
					<c:when test="${sessionScope.id==null}">
						<a href="/EmartFoodCourt/memberLogin.me" id="login_a" >
						로그인
					</a>
					</c:when>
					<c:otherwise>
						<a href="/EmartFoodCourt/memberMyPage.me?id=${sessionScope.id}" style="color:green">${id}님</a>
						<a href="memberLogout.me">로그아웃</a>
					</c:otherwise>
				</c:choose>
				</div>
				<hr>
				<div id="free_home">
					<a href="/EmartFoodCourt/boardList.bd?board_orderby=${board_orderby}&board_category=all" id="home_a">
						전체
					</a>
				</div>
				<hr>
				<div id="free_info">
					<div id="sub_title">
						
							정보
						
					</div>
					<div id="sub_menu">
						<a href="/EmartFoodCourt/boardList.bd?board_orderby=${board_orderby}&board_category=notice" id="home_a">
							공지사항
						</a>
					</div>
					<div id="sub_menu">
						<a href="/EmartFoodCourt/boardList.bd?board_orderby=${board_orderby}&board_category=event" id="home_a">
							이벤트 공지
						</a>
					</div>
					<hr>
				</div>
				
				<div id="free_community">
					<div id="sub_title">
						커뮤니티
					</div>
					<div id="sub_menu">
						<a href="/EmartFoodCourt/boardList.bd?board_orderby=${board_orderby}&board_category=free" id="home_a">
							자유
						</a>
					</div>
					<div id="sub_menu">
						<a href="/EmartFoodCourt/boardList.bd?board_orderby=${board_orderby}&board_category=humor" id="home_a">
						유머
						</a>
					</div>
					<div id="sub_menu">
						<a href="/EmartFoodCourt/boardList.bd?board_orderby=${board_orderby}&board_category=video" id="home_a">
							영상
						</a>
					</div>
				</div>
			
</div>
<div class="free_board_nav">
	<a href="/EmartFoodCourt/">
	<img src="/EmartFoodCourt/resources/images/logo.png" style="width:80px;">
	</a>
	<div class="free_board_nav_login">
		<c:choose>
			<c:when test="${id!=null}">
				<b>${id}</b>
				
				<ul class="menubar">
					<li><a href="/EmartFoodCourt/memberMyPage.me?id=${sessionScope.id}" style="color:white">마이페이지</a></li>
					<li><a href="memberLogout.me" style="color:white">로그아웃</a></li>
				</ul>  
			</c:when>
			<c:otherwise>
				<a href="memberLogin.me">
					로그인
				</a>
			</c:otherwise>
		</c:choose>
	</div>
	<a href="javascript:free_board_nav_list()">
	<img src="/EmartFoodCourt/resources/images/free_board_list_icon.PNG" class="free_board_list_icon">
	</a>
</div>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/free_board_nav.js"></script>








</body>
</html>