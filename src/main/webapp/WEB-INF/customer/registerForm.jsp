<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title></title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function customerCreate() {
	if (registerFrm.customerId.value == "") {
		alert("아이디를 입력하십시오.");
		registerFrm.customerId.focus();
		return false;
	}
	if (registerFrm.idCheck.disable == false) {
		alert("아이디 중복체크를 하십시오.");
		registerFrm.customerId.focus();
		return false;
	}
	if (registerFrm.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		registerFrm.password.focus();
		return false;
	}
	if (registerFrm.password.value != registerFrm.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		registerFrm.password2.focus();
		return false;
	}
	if (registerFrm.name.value == "") {
		alert("이름을 입력하십시오.");
		registerFrm.name.focus();
		return false;
	}
	if (registerFrm.phoneNum1.value == "") {
		alert("전화번호를 입력하십시오.");
		registerFrm.phoneNum1.focus();
		return false;
	}
	if (registerFrm.emailAddr1.value == "") {
		alert("이메일을 입력하십시오.");
		registerFrm.emailAddr1.focus();
		return false;
	}
	var emailServer = registerFrm.emailAddr2.value;
	var emailExp = /^[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(!emailExp.test(emailServer)){
		alert("이메일 형식이 올바르지 않습니다");
		registerFrm.emailAddr2.focus();
		return false;
	}
	if (registerFrm.address.value == "") {
		alert("주소를 입력하십시오.");
		registerFrm.address.focus();
		return false;
	}
	registerFrm.submit();
}

function checkId() {
	var customerId = registerFrm.customerId.value;
	if(customerId.length == 0 || customerId == "") {
		alert("아이디를 입력하십시오.");
		registerFrm.customerId.focus();
	}
	else {
		window.open("${pageContext.request.contextPath}/customer/idCheck?customerId="+customerId,"","width=500, height=300");
	}
}

</script>
</head>
<body>
<form name="registerFrm" method="POST" action="<c:url value='/customer/register'/>">
<div style="width:600px; position:absolute; top:50%; left:50%; transform:translate(-50%,-50%);" align="center">
	<h3>회원가입</h3>
	<table style="border-collapse: separate; border-spacing:10px;">
		<tr>
			<td style="width:20%;" >*아이디</td>
			<td class="input-group">
				<input class="form-control" type="text" name="customerId">
				<input class="btn btn-dark" style="color:#bbdb18;" type="button" name="idCheck" value="중복확인" onClick="checkId()">
			</td>
		</tr>
		<tr>
			<td>*비밀번호</td>
			<td><input class="form-control" type="password" name="password"></td>
		</tr>
		<tr>
			<td>*비밀번호 확인</td>
			<td><input class="form-control" type="password" name="password2"></td>
		</tr>
		<tr>
			<td>*이름</td>
			<td><input class="form-control" type="text" name="name"></td>
		</tr>
		<tr>
			<td>*휴대전화</td>
			<td class="d-flex">
				<input class="form-control" type="text" name="phoneNum1">
				&nbsp;-&nbsp;
				<input class="form-control" type="text" name="phoneNum2">
				&nbsp;-&nbsp;
				<input class="form-control" type="text" name="phoneNum3">
			</td>
		</tr>
		<tr>
			<td>*이메일</td>
			<td class="input-group">
				<input class="form-control" type="text" name="emailAddr1">
				<span class="input-group-text">@</span>
				<input class="form-control" type="text" name="emailAddr2">
			</td>
		</tr>
		<tr>
			<td>*주소</td>
			<td><input class="form-control" type="text" name="address"></td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input class="btn btn-dark" style="color:#bbdb18;" type="button" value="회원가입" onClick="customerCreate()">
				<input class="btn btn-dark" style="color:#bbdb18;" type="button" value="뒤로 가기" onClick="history.back()">
			</td>
		</tr>
	</table>
</div>
</form>
</body>
</html>