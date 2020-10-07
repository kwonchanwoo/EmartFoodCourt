<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../checkfolder/admin_check.jsp" />
<html>
<head>
<style>
.jbFixed {
	position: fixed;
	top: 0px;
}

#menubar {
	background-color: #2E2E2E;
}

</style>
<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
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
<meta charset="UTF-8">
<!-- Meta tag Keywords -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8" />
<meta name="keywords"
	content="Food Blog Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />

<!-- //Meta tag Keywords -->

<!-- Custom-Files -->
<link rel="stylesheet" href="/EmartFoodCourt/resources/css/bootstrap.css">
<!-- Bootstrap-CSS -->
<link href="/EmartFoodCourt/resources/css/css_slider.css" type="text/css"
	rel="stylesheet" media="all">
<!-- banner slider -->
<link rel="stylesheet" href="/EmartFoodCourt/resources/css/style.css"
	type="text/css" media="all" />
<!-- Style-CSS -->
<!-- 	<link href="/EmartFoodCourt/css/font-awesome.min.css" rel="stylesheet"> -->
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
	color: #0088CC;
}



.menu {
	top: 20px;
}

.csslider>ul>li:first-child {
	background: url(/EmartFoodCourt/resources/images/admin_img.png) no-repeat top;
	background-size: 100% 100%;
	-webkit-background-size: 100% 100%;
	-moz-background-size: 100% 100%;
	-o-background-size: 100% 100%;
	-ms-background-size: 100% 100%;
}
h2{
font-weight:bold;
text-align:center;
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
							<label for="drop" class="toggle">관리자 Menu</label> <input
								type="checkbox" id="drop" />
							<ul class="menu">
								<li><a href="/EmartFoodCourt/" class="nav-style">메인으로</a></li>
								<li><a href="/EmartFoodCourt/memberList.ad"
									class="nav-style">회원 관리</a></li>
								<li><a href="/EmartFoodCourt/foodList.ad" class="nav-style">음식
										관리</a></li>
								<li><a href="/EmartFoodCourt/jijumList.ad"
									class="nav-style">지점 관리</a></li>
								<li><a href="/EmartFoodCourt/recomstatusList.ad"
									class="drop-text">추천현황</a></li>
								<li><a href="/EmartFoodCourt/eventList.ev"
									class="drop-text">이벤트 관리</a></li>

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
							<!-- <div class="w3ls_banner_txt">
								<h3 class="w3ls_pvt-title text-wh let mb-3">
									<font color="purple">관리자 화면</font>
								</h3>
								rated icons

								//rated icons
							</div> -->
						</div>
					</li>
					<li class="slider-style">
						<div class="container-fluid">
							<div class="w3ls_banner_txt">

								<!-- rated icons -->
								<!-- 
								<div class="rating-w3 mt-5 pt-5">
									<ul class="list-unstyled">
										<li class="text-wh mr-4 let">Most Rated :</li>
										<li>
											<a href="#">
												<span class="fa fa-star"></span>
											</a>
										</li>
										<li>
											<a href="#">
												<span class="fa fa-star"></span>
											</a>
										</li>
										<li>
											<a href="#">
												<span class="fa fa-star"></span>
											</a>
										</li>
										<li>
											<a href="#">
												<span class="fa fa-star"></span>
											</a>
										</li>
										<li>
											<a href="#">
												<span class="fa fa-star"></span>
											</a>
										</li>
									</ul>
								</div> -->
								<!-- //rated icons -->
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
		<h2>관리자 화면 입니다.</h2>
	</div>
	<!-- //main banner -->

	<!-- content -->

</body>
</html>