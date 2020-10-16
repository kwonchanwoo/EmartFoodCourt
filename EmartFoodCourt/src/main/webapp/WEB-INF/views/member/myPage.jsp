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
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/EmartFoodCourt/resources/css/mypage.css">
<!--F12 키코드 막기-->

<script type="text/javascript">
function keydowncheck()
{
var result = true;
var keycode = event.keyCode;
if(123 == keycode) //F12 키코드
{
result = false;
}
return result;
}
</script>
<!--F12 키코드 막기 끝-->
<script>
	function chkForm(id, id_grade) {
		var retVal = confirm("정말 회원 탈퇴 하시겟습니까?");

		if (retVal == true) {
			location.href = "/EmartFoodCourt/memberDelete.ad?id=" + id
					+ "&grade=" + id_grade;
		} else {
			alert("취소되었습니다.");
		}
	}
</script>
</head>
<jsp:include page="/WEB-INF/views/checkfolder/common.jsp"/>
<body onkeydown="return keydowncheck();">
	<jsp:include page="../nav.jsp" />
	<div id="container">
		<!-- CONTENTS -->
		<!-- CONTENTS -->
		<div id="content" class="section_home">
			<div class="column">
				<c:choose>
					<c:when test="${total!=null}">
						<c:forEach var="total" items="${total}">
						
						<div class="sh_group">
							<div class="sh_header">
								<h2>나의 프로필</h2>

							</div>
							<div class="sh_content">
								<dl class="sh_lst">
									<dt class="blind">프로필 사진</dt>
									<dd class="pic_desc">
										<a href="#"> <img src="/EmartFoodCourt/resources/images/default.png"
											width="80" height="80" alt=""> <span
											class="spimg img_frame"></span>
										</a>
									</dd>
									<dt class="blind">&nbsp;</dt>
									<dd class="intro_desc">&nbsp;</dd>
									<dt class="nic_tit">등급</dt>
									<dd class="nic_desc">
										<c:choose>
											<c:when test="${total.grade==2}">
								관리자
							</c:when>
											<c:otherwise>
								일반회원
							</c:otherwise>
										</c:choose>
									</dd>
								</dl>
							</div>
							<p class="btn_area_btm">
								<a href="/EmartFoodCourt/memberInfo.ad?id=${total.id}"
									class="btn_model"><b class="btn2">내 정보 확인하기</b></a>
							</p>
						</div>

						
						<div class="sh_group">
							<div class="sh_header">
								<h2>
									관심 음식목록 보러가기 <i class="spico ico_new">NEW</i>
								</h2>
							</div>
							<c:choose>
								<c:when test="${total.crec_num>0}">
									<div class="sh_content">
										<p class="contxt">추천하신 음식들을 확인하실수있습니다.</p>
										총${total.crec_num}개 작성됨
										<p class="btn_area_btm">
											<a
												href="/EmartFoodCourt/recomstatus_rec.ad?id=${sessionScope.id}"
												class="btn_model"><b class="btn2">확인하기</b></a>
										</p>
									</div>
								</c:when>
								<c:otherwise>
									<div class="sh_content">
										<p class="contxt">본인이 추천한 음식이 없습니다!!!</p>
									</div>
								</c:otherwise>
							</c:choose>
						</div>

						
						<div class="sh_group">
							<div class="sh_header">
								<h2>본인이 작성한 자유게시판 확인하기</h2>
							</div>
							<c:choose>
								<c:when test="${total.cboard_num>0}">
									<div class="sh_content">

										<p class="contxt">
											내가최근에 작성한 자유게시판을 <br> 확인 할 수 있습니다.
										</p>
										총${total.cboard_num}개 작성됨
										<p class="btn_area_btm">
											<a href="/EmartFoodCourt/boardList.bd?category=writer&search=${sessionScope.id}"
												class="btn_model"><b class="btn2">확인하기</b></a>
										</p>
									</div>
								</c:when>
								<c:otherwise>
									<div class="sh_content">

										<p class="contxt">본인이 작성한 자유게시판이 없습니다!!</p>
										<p class="btn_area_btm">
											<a href="/EmartFoodCourt/boardList_writeForm.bd"
												class="btn_model"><b class="btn2">게시판 작성하러가기</b></a>
										</p>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
			</div>
			<div class="column">
				<!-- 일반아이디 연락처 -->
				<div class="sh_group ">
					<div class="sh_header">
						<h2>연락처</h2>


					</div>
					<div class="sh_content">
						<dl class="sh_lst2">
							<dt>연락처 이메일</dt>
							<dd>${total.email}</dd>
							<dt>휴대전화</dt>
							<dd>${total.phone}</dd>
						</dl>
					</div>
					<p class="btn_area_btm">
						<a href="/EmartFoodCourt/memberMod.ad?id=${id}&page=1"
							class="btn_model"><b class="btn2">수정</b></a>
					</p>
				</div>
				<!-- 지역 설정 -->
				<div class="sh_group">
					<c:choose>
						<c:when test="${total.crev_num>0}">
							<div class="sh_header">
								<h2>작성한 후기 확인하러가기</h2>

								<p id="p_region" class="contxt">본인이 작성한 후기를 확인하실수있습니다.</p>
								총${total.crev_num}개 작성됨
							</div>
							<p class="btn_area_btm">
								<a href="/EmartFoodCourt/recomstatus_rev.ad?id=${sessionScope.id}"
									class="btn_model"><b class="btn2">확인하기</b></a>
							</p>
						</c:when>
						<c:otherwise>
							<div class="sh_header">
								<h2>작성한 후기 확인하러가기</h2>

								<p id="p_region" class="contxt">본인이 작성한 후기가 없습니다!!!</p>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="sh_group">
					
							<div class="sh_header">
								<h2>이벤트 확인란</h2>

								<p id="p_region" class="contxt">이벤트에 당첨됫는지 확인하실수있습니다.</p>
								
							</div>
							<p class="btn_area_btm">
								<c:choose>
								<c:when test="${total.cevent_num>0}">
								<a href="/EmartFoodCourt/eventList.ev?id=${sessionScope.id}"
									class="btn_model"><b class="btn2">${total.cevent_num}개 확인하기</b></a>
								</c:when>
								<c:otherwise>
									<p id="p_region" class="contxt">당첨된 이벤트가 없습니다.</p>
								</c:otherwise>
								</c:choose>
								
							</p>
				</div>
			</div>
			<p class="desc_sub">
				로그아웃 <a href="/EmartFoodCourt/memberLogout.me" class="more"
					target="_blank">로그아웃</a>
			</p>
			<p class="desc_sub">
				회원 탈퇴를 원하신다면<a href="#" onclick="chkForm('${total.id}','${total.grade}')"
					class="more">회원탈퇴 바로가기</a>
			</p>
			</c:forEach>
			</c:when>
			<c:otherwise>
				<script>
		alert('잘못된 접근입니다!');
		location.href="/EmartFoodCourt/";
		</script>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- footer -->
<jsp:include page="../footer.jsp" />
<!-- //footer -->
</body>
</html>