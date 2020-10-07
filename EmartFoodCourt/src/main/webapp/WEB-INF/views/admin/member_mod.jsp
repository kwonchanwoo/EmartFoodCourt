<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />
<c:choose>
	<c:when test="${id_grade==2}">
		<jsp:include page="../checkfolder/admin_check.jsp" />
	</c:when>
	<c:otherwise>
		<jsp:include page="../checkfolder/login_check.jsp" />
	</c:otherwise>
</c:choose>

<c:set var="nowPage" value="${param.page}" />

<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>회원가입</title>
<style>
h2{
text-align:center;
}
#submit_btn{
text-align:center;
margin:0 auto;
}
@media ( min-width : 409px) {
	th{
	display:"";
	}
}
	
@media ( max-width : 409px) {
	th{
	display:none;
	}
}
</style>
<meta charset="UTF-8">
<title>회원정보 수정 페이지</title>
<script>
function chkForm(f) {
	
	
	if(f.pass.value.trim() == "") {
		alert("비밀번호가 일치하지 않습니다.");
		f.pass.focus();
		
		return false;
	}
	
	if(f.pass.value.trim() != f.passChk.value.trim()) {
		document.getElementById('pwsame').innerHTML = '비밀번호가 틀렸습니다. 다시 입력해 주세요';
		return false;	
	}
	
	var fi = document.getElementById('fd_img');
	 if(fi.style.display=='none'){
		f.submit();
	} else {
		var valueDate = document.getElementById('datefield').value;
		if ( valueDate== null || valueDate== '')
		{
		    alert('Date is empty');
		    return false;
		}else{
			f.submit();	
		}
	}
}
</script>


</head>
<body>
	<c:choose>
		<c:when test="${id_grade==2}">
			<jsp:include page="../admin/admin.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="../nav.jsp" />
		</c:otherwise>
	</c:choose>
	<c:if test="${memberList != null }">
		<c:forEach var="memberList" items="${memberList}">
			<div class="container">
				
							<h2>회원정보 수정</h2>
							<form
								action="/EmartFoodCourt/memberModifyProcess.ad?page=${nowPage}"
								name="modifyform" method="post" onsubmit="return chkForm(this)">
								<table class="table table-boardered">
	
	
									<tr>
										<th>아이디</th>
										<td align="left">${memberList.id}<input type="hidden"
											value="${memberList.id}" name="id" id="id"
											class="form-control1" required readonly />
										</td>
									</tr>
	
									<tr>
										<th>비밀번호</th>
										<td><input type="password" class="form-control" id="pass"
											name="pass" placeholder="비밀번호" value="${memberList.pass}"></td>
									</tr>
	
									<tr>
										<th>비밀번호확인</th>
										<td><input type="password" class="form-control"
											id="passChk" name="passChk" placeholder="비밀번호확인"
											value="${memberList.pass}">
											<p id="pwsame" style="color: red;" /></td>
	
									</tr>
	
									<tr>
										<th>이름</th>
										<td><input type="text" class="form-control" id="name"
											name="name" placeholder="이름" value="${memberList.name}">
										</td>
									</tr>
	
									<tr>
										<th>이메일</th>
										<td><input type="email" size="20" class="form-control"
											id="email" name="email" placeholder="hong12@naver.com"
											value="${memberList.email}"></td>
									</tr>
	
									<tr>
										<th>전화번호</th>
										<td><input type="tel" class="form-control" id="phone"
											name="phone" placeholder="'-'를 제외하고 입력하세요"
											value="${memberList.phone}"></td>
									</tr>
	
									<tr>
										<th>성별</th>
										<td><c:choose>
												<c:when test="${memberList.gender eq 'm'}">
													<input type="radio" name="gender" value="m" checked>남자 &nbsp;&nbsp;
						                <input type="radio" name="gender" value="w">여자&nbsp;&nbsp;
					                	</c:when>
												<c:otherwise>
													<input type="radio" name="gender" value="m">남자 &nbsp;&nbsp;
						                <input type="radio" name="gender" value="w" checked>여자&nbsp;&nbsp;
					                	</c:otherwise>
											</c:choose></td>
									</tr>
	
									<c:if test="${id_grade==2}">
										<tr>
											<th>등급</th>
											<td><c:choose>
													<c:when test="${memberList.grade==1}">
														<input type="radio" name="grade" value="1" checked>일반회원 &nbsp;&nbsp;
						                <input type="radio" name="grade" value="2">관리자&nbsp;&nbsp;
					                </c:when>
													<c:otherwise>
														<input type="radio" name="grade" value="1">일반회원 &nbsp;&nbsp;
						                <input type="radio" name="grade" value="2" checked>관리자&nbsp;&nbsp;
					                </c:otherwise>
												</c:choose></td>
										</tr>
									</c:if>
									<tr>
										<th>나이 변경 여부</th>
										<td class="td_right"><label for="image_modify">O<input
												type="radio" name="radio_btn" onclick="radio_On('fd_img')"></label>
											<label for="image_disabled">X<input type="radio"
												name="radio_btn" onclick="radio_Off('fd_img')" checked></label>
												나이 변경
										</td>
									</tr>
									<tr id="fd_img" style="display: none">
										<th>당신의 생일은</th>
	
										<td class="td_right"><input type="date" id="datefield"
											name="datefield" min="1920" max="2019-09-06"></td>
									</tr>
									<tr id="fd_img2">
	
										<th>나이</th>
										<td><input type="text" id="age" name="age"
											value="${memberList.age}" readonly></td>
									</tr>
									<tr>
										<td colspan="2">
										<div id="submit_btn">
										<input type="submit"
											class="btn btn-primary" value="수정하기"> <input
											type="reset" class="btn btn-danger" value="초기화"> <a
											href="javascript:history.back()">돌아가기 </a>
										</div>
										</td>
									</tr>
								</table>
							</form>
						</div>
				
	
		</c:forEach>
	</c:if>
	<!-- footer -->
	<jsp:include page="../footer.jsp" />
	<!-- //footer -->
	<script>
function radio_On(id)
{
	if(id=="fd_img")
	{
		document.all["fd_img"].style.display="";
		document.all["fd_img2"].style.display='none';
		
		
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth()+1;
		var yyyy = today.getFullYear();
		if(dd<10){
			 dd='0'+dd
		}
		if(mm<10){
			 mm='0'+mm
		}
		today = yyyy+'-'+mm+'-'+dd;
		document.getElementById('datefield').setAttribute('max',today);
	}
}
function radio_Off(id)
	{
	if(id=="fd_img")
		{
		document.all["fd_img"].style.display='none';
		document.all["fd_img2"].style.display="";
		
		}
	}
</script>
</body>
</html>
