<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.text_left{
	margin-right:10px;
	width:50%;
	display:inline;
}
.form_result{
	left:
}
</style>
</head>
<body>
	 <form action="/sample/test/registPro" method="post"  name="boardform">
		<div class="t1">
			<div class=text_left>
				<label for="subject">
					제목
				</label>
			</div>
			<input type="text" id="subject" name="subject">
		</div>
		<div class="t2">
			<div class=text_left>
				<label for="content">
					내용
				</label>
			</div>
			<input type="text" id="content" name="content">
		</div>
		
		<div class="form_result"><input type="submit" value="등록"><input type="reset" value="초기화"></div>
	</form>
</body>
</html>