<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />

<jsp:include page="../checkfolder/admin_check.jsp" />

<html>
<head>
<title>회원관리 시스템 관리자모드(회원 목록 보기) JSTL</title>

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
	function chkForm(id, id_grade) {
		var retVal = confirm("정말 삭제하시겟습니까?");

		if (retVal == true) {
			alert("삭제되었습니다.")
			location.href = "/EmartFoodCourt/memberDelete.ad?id=" + id
					+ "&grade=" + id_grade;
		} else {
			alert("취소되었습니다.");
		}
	}
	function search_form(){
		var form_class = document.getElementsByClassName("search_form")[0];
		 if(form_class.search.value.trim() == ""){
				alert("값을 입력해주세요!");
				return false;
			}
			form_class.submit(); 
		
		}
</script>
</head>
<body>
	<jsp:include page="../admin/admin.jsp" />
	<table>
		<tr>
			<td colspan=3 class="td_title">회원 목록</td>
		</tr>
		<tr>
			<td bgcolor="green"><font color="white">아이디</font></td>
			<td bgcolor="green"><font color="white">변경</font></td>
			<td bgcolor="green"><font color="white">등급</font></td>
		</tr>
		<c:choose>
			<c:when test="${memberList ne null }">
				<c:forEach var="list" items="${memberList }">
					<tr>
						<td><a
							href="/EmartFoodCourt/memberInfo.ad?id=${list.id}&page=${nowPage}&choice=${param.choice}&search=${param.search}">${list.id}</a>
						</td>
						<td><a
							href="/EmartFoodCourt/memberMod.ad?id=${list.id}&page=${nowPage}">수정</a>
						</td>
						<td><c:choose>
								<c:when test="${list.grade==2}">
							관리자 등급
						</c:when>
								<c:otherwise>
									<input type="button"
										onclick="chkForm('${list.id}','${list.grade}')" value="삭제">
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td align="center"><section id="emptyArea">등록된 회원이
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
				<a href="/EmartFoodCourt/memberList.ad?page=${nowPage-1}&choice=${param.choice}&search=${param.search}">[이전]</a>&nbsp;
		</c:otherwise>
		</c:choose>
		<c:forEach var="a" begin="${startPage}" end="${endPage}" step="1">
			<c:choose>
				<c:when test="${a == nowPage }">
				[${a}]
			</c:when>
				<c:otherwise>
					<a href="/EmartFoodCourt/memberList.ad?page=${a}&choice=${param.choice}&search=${param.search}">[${a}]</a>&nbsp;
			</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${nowPage >= maxPage }">
			[다음]
		</c:when>
			<c:otherwise>
				<a href="memberList.ad?page=${nowPage +1}&choice=${param.choice}&search=${param.search}">[다음]</a>
			</c:otherwise>
		</c:choose>
	</section>
	<div id="search">
	<form method="post" action="/EmartFoodCourt/memberList.ad" class="search_form">
		<select name="choice">
			<option value="id">아이디</option>
		</select>
		<input type="text" name="search" size="15">
		<input type="button" onclick="javascript:search_form();" value="검색">
		</form>
	</div>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->

</body>
</html>
