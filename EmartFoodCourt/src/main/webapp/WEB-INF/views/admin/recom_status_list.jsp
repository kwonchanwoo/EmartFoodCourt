<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<fmt:requestEncoding value="utf-8" />

<jsp:include page="../checkfolder/admin_check.jsp" />

<c:if test="${pageInfo ne null }">
	<!-- request.setAttribute() // 굳이 안해도 바로 받아올 수 있음-->
	<c:set var="listCount" value="${pageInfo.listCount }" />
	<c:set var="nowPage" value="${pageInfo.page }" />
	<c:set var="maxPage" value="${pageInfo.maxPage }" />
	<c:set var="startPage" value="${pageInfo.startPage }" />
	<c:set var="endPage" value="${pageInfo.endPage }" />
</c:if>

<html>
<head>
<title>음식 추천 현황 관리자모드(음식 추천 현황 보기) JSTL</title>
<style>
table {
	margin: auto;
	width: 100%;
	border: 1px solid gray;
	text-align: center;
}

.td_title {
	font-weight: bold;
	font-size: x-large;
}

#pageList {
	margin: auto;
	width: 100%;
	text-align: center;
}

#emptyArea {
	margin: auto;
	width: 500px;
	text-align: center;
}
#search{
	position:relative;
	width:100%;
	height:50px;
	top:30px;
	text-align:center;
	margin-top:0 auto;
}
</style>
<script>
	
</script>
</head>
<body>
	<jsp:include page="../admin/admin.jsp" />
	<table>
		<tr>
			<td colspan=6 class="td_title">음식 추천 현황 목록</td>
		</tr>
		<tr>
			<td bgcolor="green"><font color="white">아이디</font></td>
			<td bgcolor="green"><font color="white">등급</font></td>
			<td bgcolor="green"><font color="white">추천</font></td>
			<td bgcolor="green"><font color="white">평점</font></td>
			<td bgcolor="green"><font color="white">후기게시판</font></td>
			<td bgcolor="green"><font color="white">합계</font></td>
		</tr>
		<c:choose>
			<c:when test="${totalList ne null }">
				<c:forEach var="list" items="${totalList}">
					<tr>
						<td><a href="memberInfo.ad?id=${list.id}">${list.id}</a></td>
						<c:choose>
							<c:when test="${list.grade==1}">
								<td>회원</td>
							</c:when>
							<c:otherwise>
								<td>관리</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${list.crec_num!=0}">
								<td><a
									href="/EmartFoodCourt/recomstatus_rec.ad?id=${list.id}&page=${nowPage}">${list.crec_num}</a></td>
							</c:when>
							<c:otherwise>
								<td>${list.crec_num}</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${list.cgrade_num!=0}">
								<td><a
									href="/EmartFoodCourt/recomstatus_grade.ad?id=${list.id}&page=${nowPage}">${list.cgrade_num}</a></td>
							</c:when>
							<c:otherwise>
								<td>${list.cgrade_num}</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${list.crev_num!=0}">
								<td><a
									href="/EmartFoodCourt/recomstatus_rev.ad?id=${list.id}&page=${nowPage}">${list.crev_num}</a></td>
							</c:when>
							<c:otherwise>
								<td>${list.crev_num}</td>
							</c:otherwise>
						</c:choose>
						<td>${list.recom_status_total}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td align="center"><section id="emptyArea">등록된
							추첨,평점,후기가 없습니다.</section></td>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>

	<section id="pageList">
		<c:choose>
			<c:when test="${nowPage <= 1 }">
			[이전]&nbsp;
		</c:when>
			<c:otherwise>
				<a href="/EmartFoodCourt/recomstatusList.ad?page=${nowPage-1}&choice=${param.choice}&search=${param.search}">[이전]</a>&nbsp;
		</c:otherwise>
		</c:choose>
		<c:forEach var="a" begin="${startPage }" end="${endPage }" step="1">
			<c:choose>
				<c:when test="${a == nowPage }">
				[${a}]
			</c:when>
				<c:otherwise>
					<a href="/EmartFoodCourt/recomstatusList.ad?page=${a}&choice=${param.choice}&search=${param.search}">[${a}]</a>&nbsp;
			</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${nowPage >= maxPage }">
			[다음]
		</c:when>
			<c:otherwise>
				<a href="/EmartFoodCourt/recomstatusList.ad?page=${nowPage +1}&choice=${param.choice}&search=${param.search}">[다음]</a>
			</c:otherwise>
		</c:choose>
		<br> <a href="/EmartFoodCourt/index.jsp">메인화면으로</a>
	</section>
	<div id="search">
	<form method="post" action="/EmartFoodCourt/recomstatusList.ad">
		<select name="choice">
			<option value="id">아이디</option>
		</select>
		<input type="text" name="search" size="15">
		<input type="submit" value="검색">
		</form>
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
</body>
</html>
