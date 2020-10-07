<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html>
<style>
.sidebar{
	position:fixed;
	top:50px;
	float:left;
	width:200px;
	background-color:white;
	border:3px solid grey;
	margin-bottom:10px;
}
#login{
	margin-top:10px;
	margin-left:10px;
	width:180px;
	height:50px;
	border-radius:8px;
	text-align:center;
	padding-top:10px;
	border-color: #46cfa7;
    background-color: #46cfa7;
   
}
#login_a{
	 color: #fff
}
#login:hover{
	background-color:#04B486;
}
#free_home{
	margin-top:10px;
	margin-left:10px;
	width:180px;
	height:30px;
	border-radius:8px;
	padding-top:10px;
	padding-left:15px;
	color:#04B486;
}
#free_home:hover{
	background-color:#F2F2F2;
}
#home_a{
	color:#04B486;
}
#free_info{
	margin-top:10px;
	margin-left:10px;
	width:180px;
	padding-top:10px;
	padding-left:15px;
	
}
#sub_title{
	position:relative;
	width:100%;
	height:30px;
	font-size:10px;
	margin-bottom:5px;
}
#sub_menu{
	position:relative;
	width:100%;
	height:30px;
	font-weight:bold;
	font-size:15px;
	margin-bottom:5px;
	color:#04B486;
}
#sub_menu:hover{
	border-radius:8px;
	background-color:#F2F2F2;
}
#free_community{
	margin-top:10px;
	margin-left:10px;
	top:160px;
	width:180px;
	font-size:10px;
	padding-top:10px;
	padding-left:15px;
}
@media(max-width:1066px){
	.sidebar{
		display:none;
	}
}

</style>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="sidebar">
				<div id="login">
				<c:choose>
					<c:when test="${sessionScope.id==null}">
						<a href="/EmartFoodCourt/memberLogin.me" id="login_a" >
						로그인
					</a>
					</c:when>
					<c:otherwise>
						${id}님
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
</body>
</html>