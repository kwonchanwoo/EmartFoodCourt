<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/checkfolder/common.jsp"%>

<script
	src="http://dmaps.daum.net/map_js_init/postcode.v2.js?autoload=false"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>회원가입</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="/EmartFoodCourt/resources/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="/EmartFoodCourt/resources/css/bootstrap-theme.min.css">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css"> -->

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
  
  var chkId = false;
	var idcheck;
	/* var newURL = window.location.protocol + "//" + window.location.host + "/" + window.location.pathname; */
  $(function() {
    $("#idcheck").click(function() {
      var str = $("#joinform").serialize();

      $.ajax({
        type:"POST",
        url:"/EmartFoodCourt/memberIdCheck.me",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: str,
        datatype:"json",
        success: function(data) {
        	
        	if(data.result=='1'){
        		document.getElementById('idcheck_p').innerHTML = '사용가능한 아이디입니다.';
	            chkId = true;
	            idcheck=document.getElementById("id").value;
            }else if(data.result=='0'){
            	document.getElementById('idcheck_p').innerHTML = '아이디가 중복되었습니다.';
            	chkId=false;
            }		
        },
        error: function(e) {
          alert("에러발생");
        }			
      });
    });
  });

	function chkForm(f) {
		if (!chkId || idcheck != f.id.value.trim()) {
			document.getElementById('idcheck_p').innerHTML = '아이디는 중복검사를 해주세요.';
			return false;
		}

		if (f.pass.value.trim() == "") {
			alert("비밀번호가 일치하지 않습니다.");
			f.pass.focus();

			return false;
		}

		if (f.pass.value.trim() != f.passChk.value.trim()) {
			document.getElementById('pwsame').innerHTML = '비밀번호가 틀렸습니다. 다시 입력해 주세요';
			alert("비밀번호가 틀렸습니다. 다시 입력해 주세요");
			return false;
		}
		if (f.phone.value.trim() == "") {
			 document.getElementById('phonechk').innerHTML = '전화번호를 입력해주세요.';
			 alert("전화번호를 입력해주세요.");
			f.phone.focus();
			return false;
		}
		if ( f.phone.value.length!=11) {
            document.getElementById('phonechk').innerHTML = '전화번호 길이가 잘못되었습니다.';
            alert("전화번호 길이가 잘못되었습니다.");
            return false;
        }
		if (f.name.value.trim() == "") {
			alert("이름을 입력해주세요.");
			f.name.focus();
			return false;
		}
		if (f.email.value.trim() == "") {
			alert("이메일 주소를 입력해주세요.");
			f.email.focus();
			return false;
		}
		if (f.addr1.value.trim() == "") {
			alert("주소를 입력해주세요.");
			f.email.focus();
			return false;
		}
		if (f.addr2.value.trim() == "") {
			alert("상세 주소를 입력해주세요.");
			f.email.focus();
			return false;
		}
		if (f.zip.value.trim() == "") {
			alert("우편 번호를 입력해주세요.");
			f.zip.focus();
			return false;
		}
		if(f.zip.value.length!=5){
			alert("우편 번호를 길이는 5자리입니다.");
			f.zip.focus();
			return false;
		}
		
		var regex= /[^0-9]/g;
		if(regex.test(f.zip.value)){
			alert("우편 번호는 숫자만 가능합니다.");
			f.zip.focus();
			return false;
		}
		
		var check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
		if(check.test(f.id.value)){
			alert("아이디는 한글로 만들수 없습니다.");
			document.getElementById('idcheck_p').innerHTML = '아이디는 한글로 만들수 없습니다.';
			return false;
		}
		if (f.pass.value.length<4 || f.pass.value.length>12) {
			alert("비밀번호를 4~12자까지 입력해주세요.");
            document.getElementById('pwsame').innerHTML = '비밀번호를 4~12자까지 입력해주세요.';
            return false;
        }
		 f.submit();
	}
	</script>
<script>
function keyevent(){
	
	document.getElementsByClassName('underline')[0].innerHTML=' ';
	document.getElementsByClassName('underline')[1].innerHTML=' ';
	document.getElementsByClassName('underline')[2].innerHTML=' ';
  	}
</script>

<!--autoload=false 파라미터를 이용하여 자동으로 로딩되는 것을 막습니다.-->

<script>
    //load함수를 이용하여 core스크립트의 로딩이 완료된 후, 우편번호 서비스를 실행합니다.
    daum.postcode.load(function(){
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                // 예제를 참고하여 다양한 활용법을 확인해 보세요.
            }
        })
    });
</script>
<script>
	function execDaumPostcode(){
    new daum.Postcode({
        oncomplete: function(data) {
        	var fullRoadAddr = data.roadAddress;
        	var extraRoadAddr = '';

        	if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
        		extraRoadAddr += data.bname;
        	}
        	if(data.buildingName !== '' && data.apartment ==='Y'){
        		extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        	}
        	if(extraRoadAddr !== ''){
        		extraRoadAddr = ' (' + extraRoadAddr + ')';
        	}
        	if(fullRoadAddr !== ''){
        		fullRoadAddr += extraRoadAddr;
        	}
        	document.getElementById('zip').value=data.zonecode;
        	document.getElementById('addr1').value = fullRoadAddr;
        	document.getElementById('addr2').focus();
        	}
    }).open();
 }
</script>
<style>
@media ( min-width : 409px) {
	th {
		display: "";
	}
}

@media ( max-width : 409px) {
	th {
		display: none;
	}
	#id {
		width: 50%;
	}
}

#submit_btn {
	text-align: center;
	margin: 0 auto;
}
</style>
</head>

<body>
	<div class="container">



		<h2>회원가입</h2>
		<form id="joinform" name="joinform"
			action="/EmartFoodCourt/memberJoinPro.me" method="post">
			<table class="table table-boardered">
				<tr>
					<th>아이디</th>
					<td><input type="text" style="ime-mode: disabled"
						class="form-control1" id="id" name="id" placeholder="아이디"
						maxlength="12" onkeydown="keyevent()"> <input
						type="button" value="중복검사" id="idcheck">
						<p class="underline" id="idcheck_p" style="color: red;" /> <a
						id="test12" href="/EmartFoodCourt/memberJoinPro.me"></a></td>
				</tr>

				<tr>
					<th>비밀번호</th>
					<td><input type="password" class="form-control" id="pass"
						name="pass" placeholder="비밀번호" maxlength="16"></td>
				</tr>

				<tr>
					<th>비밀번호확인</th>
					<td><input type="password" class="form-control" id="passChk"
						name="passChk" placeholder="비밀번호확인" maxlength="16"
						onkeydown="keyevent()">
						<p class="underline" id="pwsame" style="color: red;" /></td>

				</tr>

				<tr>
					<th>이름</th>
					<td><input type="text" class="form-control" id="name"
						name="name" placeholder="이름" maxlength="6"></td>
				</tr>

				<tr>
					<th>이메일</th>
					<td><input type="email" size="20" class="form-control"
						id="email" name="email" placeholder="hong12@naver.com" maxlength="30"></td>
				</tr>

				<tr>
					<th>전화번호</th>
					<td><input type="tel" class="form-control" id="phone"
						name="phone" placeholder="'-'를 제외하고 입력하세요" maxlength="11"
						onkeydown="keyevent()">
						<p class="underline" id="phonechk" style="color: red;" /></td>

				</tr>

				<tr>
					<th>성별</th>
					<td><input type="radio" name="gender" value="m" checked>남자
						&nbsp;&nbsp; <input type="radio" name="gender" value="w">여자&nbsp;&nbsp;
					</td>
				</tr>

				<tr>
					<th>당신의 생일은</th>
					<td><input type="date" id="datefield" name="datefield"
						min="1920" max="2019-09-06" required></td>
				</tr>
				<tr>
					<th>우편번호</th>
					<td><input type="text" name="zip" id="zip" placeholder="우편번호"
						maxlength="5" required size="4">&nbsp;<input type="button"
						class="btn-primary box" onclick="execDaumPostcode()"
						value="우편번호 찾기" required></td>
				</tr>
				<tr>
					<th>도로명주소</th>
					<td><input type="text" name="addr1" class="form-control"
						id="addr1" placeholder="도로명주소" size="7" required></td>
				</tr>
				<tr>
					<th>상세주소</th>
					<td><input type="text" name="addr2" class="form-control"
						id="addr2" placeholder="상세주소주소" required>
					<td>
				<tr>
					<td colspan="2">
						<div id="submit_btn">
							<input type="button" class="btn btn-primary" value="전송"
								onClick="chkForm(joinform)"> <input type="reset"
								class="btn btn-danger" value="취소"
								onClick="javascript:window.close()">
						</div>
					</td>
				</tr>
			</table>

		</form>
	</div>

	<script type="text/javascript" language="javascript">
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
</script>
</body>
</html>