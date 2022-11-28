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
<script>
function login() {
	if (loginFrm.customerId.value == "") {
		alert("ID를 입력하십시오.");
		loginFrm.customerId.focus();
		return false;
	} 
	if (loginFrm.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		loginFrm.password.focus();
		return false;
	}		
	loginFrm.submit();
}
</script>
</head>
<body>
<form name="loginFrm" method="POST" action="<c:url value='/customer/login'/>">

<div style="position:absolute; top:50%; left:50%; transform:translate(-50%,-50%);" align="center">
	<h3>로그인</h3>
	<table style="width:300px; text-align:center; border-collapse: separate; border-spacing:10px;">
		<tr>
			<td><input class="form-control" type="text" name="customerId" placeholder="아이디"></td>
		</tr>
		<tr>
			<td><input class="form-control" type="password" name="password" placeholder="비밀번호"></td>
		</tr>
		<tr>
			<td class="d-grid gap">
				<input class="btn btn-dark" style="color:#bbdb18;" type="button" value="로그인" onClick="login()">
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="<c:url value='/customer/search' />">아이디/비밀번호 찾기</a>&nbsp;&nbsp;
				<a href="<c:url value='/customer/register' />">회원가입 </a>
			</td>
		</tr>
	</table>
</div>
</form>
</body>
</html>