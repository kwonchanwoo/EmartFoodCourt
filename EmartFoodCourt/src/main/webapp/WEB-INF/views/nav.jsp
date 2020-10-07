<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

<script>
$( document ).ready( function() {
    var jbOffset = $( '#menubar' ).offset();
    var banner_id = $('#banner_id').offset();
   
    $( window ).scroll( function() {
      if ( $( document ).scrollTop() > banner_id.top ) {
    	  
    		  $( '#menubar' ).addClass( 'jbFixed' );
    	  
      }
      else {
        $( '#menubar' ).removeClass( 'jbFixed' );
        
      }
    });
  } );
</script>

   
<!--  <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>    -->
<script src="resources/js/bootstrap.min.js"></script>

<meta charset="UTF-8">
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="keywords"
	content="Food Blog Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />


<!-- //Meta tag Keywords -->


<!-- Custom-Files -->
<link rel="stylesheet" href="/EmartFoodCourt/resources/css/bootstrap.css">
<link rel="stylesheet" href="/EmartFoodCourt/resources/css/style.css" type="text/css" media="all" />
<!-- Bootstrap-CSS -->
<link href="/EmartFoodCourt/resources/css/css_slider.css" type="text/css"
	rel="stylesheet" media="all">
<!-- banner slider -->

<!-- Style-CSS -->
<!-- <link href="/EmartFoodCourt/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all"> -->
<!-- Font-Awesome-Icons-CSS -->
<!-- //Custom-Files -->

<!-- Web-Fonts -->
<link
	href="//fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&amp;subset=devanagari,latin-ext"
	rel="stylesheet">
<link
	href="//fonts.googleapis.com/css?family=Hind:300,400,500,600,700&amp;subset=devanagari,latin-ext"
	rel="stylesheet">
<!-- //Web-Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Black+Han+Sans|Dancing+Script|Dokdo&display=swap"
	rel="stylesheet">
<style>
nav a {
	font-family: 'Dancing Script', cursive;
	font-size: 20px;
}

nav a:hover {
	color: #2E9AFE;
}
.drop-text {
	font-family: 'Dancing Script', cursive;
	color: green;
}

.menu {
	top: 20px;
}
.jbFixed {
        position: fixed;
        top: 0px;
}
#menubar{

background-color:#2E2E2E;

}



</style>
</head>
<body>
	
	<!-- main banner -->
	<div class="main-top" id="home">
		<!-- header -->
		<header>
			<div class="container-fluid" id="menubar">
				<div
					class="header d-md-flex justify-content-between align-items-center py-3 pl-2">
					<!-- logo -->
					<div id="logo">
						<h1>
							<a href="/EmartFoodCourt/"> <img
								src="/EmartFoodCourt/resources/images/logo.png" width="100px" />
							</a>
						</h1>
					</div>
					<!-- //logo -->
					<!-- nav -->
					<div class="nav_w3ls">
						<nav>
							<label for="drop" class="toggle">Menu</label> <input
								type="checkbox" id="drop" />
							<ul class="menu">
								<li><a href="/EmartFoodCourt/"
									class="nav-style" id="menu">Home</a></li>
								<li><a href="/EmartFoodCourt/recom.fc?recom_check=true"
									class="nav-style">음식 추천</a></li>
								<li><a href="/EmartFoodCourt/menuView.fc" class="nav-style">메뉴소개</a></li>
								 <li><a href="/EmartFoodCourt/boardList.bd" class="nav-style">자유게시판</a></li>

								<li><c:choose>
										<c:when test="${id ne null }">
											<a
												href="/EmartFoodCourt/memberMyPage.me?id=${sessionScope.id}"
												class="nav-style">${id}
												<span class="fa fa-angle-down" aria-hidden="true"></span>
											</a>
											<ul>
												<li><a href="/EmartFoodCourt/memberLogout.me"
													class="drop-text">로그아웃</a></li>
												<li><a
													href="/EmartFoodCourt/memberMyPage.me?id=${sessionScope.id}"
													class="drop-text">내 정보 보기</a></li>
											</ul>
										</c:when>
										<c:otherwise>

											<a href="/EmartFoodCourt/memberLogin.me" class="nav-style">로그인</a>
										</c:otherwise>
									</c:choose></li>


								<li><c:if test="${id_grade == 2 }">
										<a href="/EmartFoodCourt/admin.ad" class="nav-style">관리자
											<span class="fa fa-angle-down" aria-hidden="true"></span>
										</a>

										<ul>
											<li><a href="/EmartFoodCourt/memberList.ad"
												class="drop-text">회원관리</a></li>
											<li><a href="/EmartFoodCourt/foodList.ad"
												class="drop-text">음식관리</a></li>
											<li><a href="/EmartFoodCourt/jijumList.ad"
												class="drop-text">지점관리</a></li>
											<li><a href="/EmartFoodCourt/recomstatusList.ad"
												class="drop-text">음식추천현황</a></li>
											<li><a href="/EmartFoodCourt/eventList.ev"
												class="drop-text">이벤트 관리</a></li>
										</ul>
									</c:if> <!-- First Tier Drop Down --> <!-- 
									<label for="drop-2" class="toggle toogle-2 nav-style">관리자메뉴 
									<span class="fa fa-angle-down" aria-hidden="true"></span>
									</label>
								 --></li>
							</ul>
						</nav>
					</div>
					<!-- //nav -->
				</div>
			</div>
		</header>
		<!-- //header -->

		<!-- banner -->
		<div class="banner_w3lspvt" id="banner_id">
			<div class="csslider infinity" id="slider1">
				<input type="radio" name="slides" checked="checked" id="slides_1" />
				<input type="radio" name="slides" id="slides_2" />
				<ul class="banner_slide_bg">
					<li class="slider-style">
						<div class="container-fluid">
							<div class="w3ls_banner_txt">
								<!-- <p style="font-size:25px;">E-MART FOOD COURT</p>
								<h4 class="w3ls_pvt-title text-wh let mb-3">
									방문해 주셔서 <br>감사합니다.
								</h4> -->
								<!-- rated icons -->

								<!-- //rated icons -->
							</div>
						</div>
					</li>
					<li class="slider-style">
						<div class="container-fluid">
							<div class="w3ls_banner_txt">
								

							</div>
						</div>
					</li>
				</ul>
				<div class="arrows">
					<label for="slides_1"></label> <label for="slides_2"></label>
				</div>
			</div>
		</div>
		<!-- //banner -->
	</div>
	<!-- //main banner -->

	<!-- content -->

</body>
</html>