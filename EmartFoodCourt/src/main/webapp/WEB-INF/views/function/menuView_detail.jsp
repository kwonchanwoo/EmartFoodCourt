<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp" %>

<meta charset="UTF-8">
<meta property="og:title" content="이마트 푸드코트 후기 사이트">
<meta property="og:url" content="july">
<meta property="og:description" content="내용">
<meta property="og:image"
	content="http://java.swq.co.kr/EmartFoodCourt/images/ch2.jpeg">
<title>Insert title here</title>

<link rel="stylesheet" href="/EmartFoodCourt/resources/css/menuView_detail.css">

<script async defer crossorigin="anonymous"
	src="https://connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v4.0"></script>
<script>
function sharing(connect_num){ /* 페이스북 자바스크립트 */
	window.open('http://www.facebook.com/sharer/sharer.php?u=http://java.swq.co.kr/EmartFoodCourt/');
}
</script>
<style>
#facebook {
	float: right;
}

#current {
	background: rgb(109, 109, 109);
	color: #cccccc;
}
</style>
</head>
<body>
	<%@ include file="../nav.jsp" %><br>


	<c:if test="${total!=null}">
		<c:forEach var="total" items="${total}">
			<div class="content_area">
				<div class="content_wrap">


					<div class="line"></div>
					<h1 id="logo">E-MART</h1>
					<p>${total.food_name}</p>
					<div id="food_img">
						<img src="/EmartFoodCourt/resources/images/${total.food_image}"
							width="150px" height="150px">
					</div>
					<div id="test2">
						음식 평점 :
						<c:choose>
							<c:when test="${total.avg_grade == null}">
							평가없음
						</c:when>
							<c:otherwise>
							${total.avg_grade}
						</c:otherwise>
						</c:choose>
						<div id="test3">
							좋아요수
							<c:choose>
								<c:when test="${total.count_rec==0}">
								평가없음
							</c:when>
								<c:otherwise>
								${total.count_rec}
							</c:otherwise>
							</c:choose>
						</div>
					</div>
					<p id="text1">
						지점 이름 : ${total.jijum_name} 음식 설명 :
						<c:choose>
							<c:when test="${fn:trim(total.food_content) eq ''}">
						내용없음
					</c:when>
							<c:otherwise>
					${total.food_content} 
					</c:otherwise>
						</c:choose>

						가격 : ${total.con_price} 음식 평점 :
						<c:choose>
							<c:when test="${total.avg_grade==null}">평가없음</c:when>
							<c:otherwise>${total.avg_grade}</c:otherwise>
						</c:choose>
						좋아요수
						<c:choose>
							<c:when test="${total.count_rec==0}">평가없음</c:when>
							<c:otherwise>${total.count_rec}</c:otherwise>
						</c:choose>
					</p>


					<form name="star"
						action="/EmartFoodCourt/avg_grade.fc?connect_num=${total.connect_num}"
						method="post">
						<select name="grade" id="grade">
							<option value="1">★</option>
							<option value="2">★★</option>
							<option value="3">★★★</option>
							<option value="4">★★★★</option>
							<option value="5">★★★★★</option>
						</select> <a href="javascript:star.submit()"><img
							src="/EmartFoodCourt/resources/images/btn_point.gif" id="grade_btn"></a>
					</form>
					<a
						href="/EmartFoodCourt/count_rec.fc?connect_num=${total.connect_num}"><img
						src="/EmartFoodCourt/resources/images/btn_good.gif" id="recom_btn"></a>
					<div id="menu_btn">
						<a href="#" onclick="sharing(${total.connect_num})"><img
							src="/EmartFoodCourt/resources/images/facebook.png" width="50px"
							height="50px" id="facebook"></a>
					</div>
					<br> <a href="/EmartFoodCourt/menuView.fc" id="list_go">목록으로
						가기</a>

					<h1 id="rev_title">
						후기게시판
						<c:if test="${sessionScope.id!=null}">
						</c:if>
					</h1>
					<a
						href="/EmartFoodCourt/Rev_Board_writeForm.fc?connect_num=${total.connect_num}&food_name=${total.food_name}&jijum_name=${total.jijum_name}"
						id="test4"> 작성하기</a>

					<div class="menubar" id="menubar-1">
						<ul>
							<li><input type="button" id="current" value="조회수"
								onClick="location.href='/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=1'" />
							</li>
							<li><input type="button" id="current" value="최신순"
								onclick="location.href='/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=2'" />
							</li>
							<li><input type="button" id="current" value="별점별보기">
								<ul>
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=3&star=1">★</a></li>
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=3&star=2">★★</a></li>
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=3&star=3">★★★</a></li>
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=3&star=4">★★★★</a></li>
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=3&star=5">★★★★★</a></li>
								</ul></li>
						</ul>
					</div>
					<div class="menubar" id="menubar-2">
						<ul>
							<li><input type="button" id="current" value="조회,최신">
								<ul id="current_ul">
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=1">조회수</a></li>
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=2">최신순</a>
									</li>
								</ul></li>
							<li><input type="button" id="current" value="별점보기">
								<ul id="current_ul">
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=3&star=1">★</a></li>
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=3&star=2">★★</a></li>
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=3&star=3">★★★</a></li>
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=3&star=4">★★★★</a></li>
									<li><a
										href="/EmartFoodCourt/menuView_detail.fc?connect_num=${total.connect_num}&choice=3&star=5">★★★★★</a></li>
								</ul></li>
						</ul>
					</div>

					<c:choose>
						<c:when test="${board!=null}">
							<div id="table">
								<div class="row-title">
									<div class="rev-title">후기상단
</div>
								</div>
								<c:forEach var="list" items="${board}">
									<div class="row">
										<div class="cell col1">
											<img src="/EmartFoodCourt/resources/images/icon-vote-up.png"
												id="img">${list.rev_readcount}</div>
										<div class="cell col2">
											<a
												href="/EmartFoodCourt/Rev_Board_detail.fc?rev_num=${list.rev_num}&connect_num=${list.connect_num}&page=${nowPage}">
												${list.rev_subject}
												<%-- <c:choose>
													<c:when test="${!empty list.rev_subject}">
														<c:choose>
															<c:when test="${list.count_img==0}">
															${list.rev_subject}
															<!-- 
													${fn:substring(list.rev_subject,0,3)}.. -->
															</c:when>
															<c:otherwise>
															${list.rev_subject}<img
																	src="/EmartFoodCourt/resources/images/icon-picture@2x.png"
																	width="20px" height="20px">
															<!-- 
													${fn:substring(list.rev_subject,0,3)}..<img
																	src="/EmartFoodCourt/resources/images/icon-picture@2x.png"
																	width="20px" height="20px"> -->
															</c:otherwise>
														</c:choose>

													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${list.img_name=='logo.png'}">
													${list.rev_subject}
												</c:when>
															<c:otherwise>
															</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose> --%>
											</a>
										</div>
										<div class="cell col3">${list.rev_date}</div>
										<div class="cell col4">${list.id}</div>
										<div class="cell col5">
											<c:forEach begin="1" end="${list.rev_satisfaction}">★</c:forEach>
										</div>
									</div>
								</c:forEach>
							</div>
							<section id="pageList">
								<c:choose>
									<c:when test="${nowPage <= 1}">
	 	[이전]&nbsp;
	 	</c:when>
									<c:otherwise>
										<a
											href="/EmartFoodCourt/menuView_detail.fc?connect_num=${connect_num}&page=${nowPage-1}&choice=${choice}&star=${star}">[이전]</a>&nbsp;
	 	</c:otherwise>
								</c:choose>
								<c:forEach var="a" begin="${startPage}" end="${endPage}"
									step="1">
									<c:choose>
										<c:when test="${a==nowPage}">
			[${a}]
		</c:when>
										<c:otherwise>
											<a
												href="/EmartFoodCourt/menuView_detail.fc?connect_num=${connect_num}&page=${a}&choice=${choice}&star=${star}">[${a}]</a>&nbsp;
		</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${nowPage>=maxPage}">
				[다음]
			</c:when>
									<c:otherwise>
										<a
											href="/EmartFoodCourt/menuView_detail.fc?connect_num=${connect_num}&page=${nowPage+1}&choice=${choice}&star=${star}">[다음]</a>
									</c:otherwise>
								</c:choose>
							</section>
						</c:when>
						<c:otherwise>
							<p id="blank">아직 등록된 후기가 없습니다.</p>
							<p id="blank_sm" style="color: red;">후기가 없습니다.</p>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</c:forEach>
	</c:if>


	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->

</body>
</html>