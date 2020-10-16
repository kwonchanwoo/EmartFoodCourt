<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />

<jsp:include page="../checkfolder/admin_check.jsp" />


<html>
<head>
<title>음식관리 시스템 관리자모드(음식 목록 보기) JSTL</title>
<style>
table {
	margin-top: 25px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
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
	function chkForm(jijum_name) {
		var retVal = confirm("정말 삭제하시겟습니까?");

		if (retVal == true) {
			location.href = "/EmartFoodCourt/jijumDelete.ad?jijum_name="
					+ jijum_name;
		} else {
			alert("취소되었습니다.");
		}
	}
</script>
</head>
<body>
	<jsp:include page="../admin/admin.jsp" />
	<table>
		<tr>
			<td colspan=3 class="td_title">지점 목록</td>
		</tr>
		<tr>

			<td bgcolor="green"><font color="white">지점명</font></td>
			<td bgcolor="green"><font color="white">수정</font></td>
			<td bgcolor="green"><font color="white">삭제</font></td>
		</tr>
		<c:choose>
			<c:when test="${jijumList ne null }">
				<c:forEach var="list" items="${jijumList}">
				
					<tr>
						<td><a
							href="/EmartFoodCourt/jijumInfo.ad?jijum_name=${list.jijum_name}&page=${nowPage}&choice=${param.choice}&search=${param.search}">${list.jijum_name}</a>
						</td>
						<td><a
							href="/EmartFoodCourt/jijumModForm.ad?jijum_name=${list.jijum_name}&page=${nowPage}">수정</a>
						</td>
						<td><input type="button"
							onclick="chkForm('${list.jijum_name}')" value="삭제"></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td align="center"><section id="emptyArea">등록된 음식이
							없습니다.</section></td>
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
				<a href="/EmartFoodCourt/jijumList.ad?page=${nowPage-1}&choice=${param.choice}&search=${param.search}">[이전]</a>&nbsp;
		</c:otherwise>
		</c:choose>
		<c:forEach var="a" begin="${startPage }" end="${endPage }" step="1">
			<c:choose>
				<c:when test="${a == nowPage }">
				[${a}]
			</c:when>
				<c:otherwise>
					<a href="/EmartFoodCourt/jijumList.ad?page=${a}&choice=${param.choice}&search=${param.search}">[${a}]</a>&nbsp;
			</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${nowPage >= maxPage }">
			[다음]
		</c:when>
			<c:otherwise>
				<a href="/EmartFoodCourt/jijumList.ad?page=${nowPage +1}&choice=${param.choice}&search=${param.search}">[다음]</a>
			</c:otherwise>
		</c:choose>
		<br> <a href="/EmartFoodCourt/index.jsp">메인화면으로</a>&nbsp;&nbsp;<a
			href="/EmartFoodCourt/jijumAddForm.ad">등록하기</a>
	</section>
	<div id="search">
	<form method="post" action="/EmartFoodCourt/jijumList.ad" class="search_form">
		<select name="choice">
			<option value="jijum_name">지점이름</option>
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
