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
function customerModify() {
	if (updateFrm.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		updateFrm.password.focus();
		return false;
	}
	if (updateFrm.password.value != updateFrm.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		updateFrm.password2.focus();
		return false;
	}
	if (updateFrm.name.value == "") {
		alert("이름을 입력하십시오.");
		updateFrm.name.focus();
		return false;
	}
	if (updateFrm.phoneNum1.value == "") {
		alert("전화번호를 입력하십시오.");
		updateFrm.phoneNum1.focus();
		return false;
	}
	if (updateFrm.emailAddr1.value == "") {
		alert("이메일을 입력하십시오.");
		updateFrm.emailAddr1.focus();
		return false;
	}
	var emailServer = updateFrm.emailAddr2.value
	var emailExp = /^[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(!emailExp.test(emailServer)){
		alert("이메일 형식이 올바르지 않습니다");
		updateFrm.emailAddr2.focus();
		return false;
	}
	if (updateFrm.address.value == "") {
		alert("주소를 입력하십시오.");
		updateFrm.address.focus();
		return false;
	}
	updateFrm.submit();
}
</script>
</head>
<body>
<form name="updateFrm" method="POST" action="<c:url value='/customer/update' />">
<div style="width:600px; position:absolute; top:50%; left:50%; transform:translate(-50%,-50%);" align="center">
	<h3>회원정보변경</h3>
	<table style="border-collapse: separate; border-spacing:10px;">
		<tr>
			<td style="width:20%">아이디</td>
			<td><input type="text" readonly class="form-control-plaintext" name="customerId" value="${customer.customerId}"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input class="form-control" type="password" name="password" value="${customer.password}"></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input class="form-control" type="password" name="password2" value="${customer.password}"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input class="form-control" type="text" name="name" value="${customer.name}"></td>
		</tr>
		<tr>
			<td>휴대전화</td>
			<td class="d-flex">
				<input class="form-control" type="text" name="phoneNum1" value="${customer.phoneNumber.split('-')[0]}">
				&nbsp;-&nbsp;
				<input class="form-control" type="text" name="phoneNum2" value="${customer.phoneNumber.split('-')[1]}">
				&nbsp;-&nbsp;
				<input class="form-control" type="text" name="phoneNum3" value="${customer.phoneNumber.split('-')[2]}">
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td class="input-group">
				<input class="form-control" type="text" name="emailAddr1" value="${customer.emailAddr.split('@')[0]}">
				<span class="input-group-text">@</span>
				<input class="form-control" type="text" name="emailAddr2" value="${customer.emailAddr.split('@')[1]}">
			</td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input class="form-control" type="text" name="address" value="${customer.address}"></td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input class="btn btn-dark" style="color:#bbdb18;" type="button" value="수정" onClick="customerModify()">
				<a class="btn btn-dark" style="color:#bbdb18;" href="<c:url value='/customer/view' >
					     <c:param name='customerId' value='${customer.customerId}'/>
				</c:url>">뒤로 가기</a>
			</td>
		</tr>
	</table>
	<c:if test="${updateFailed || deleteFailed}">
		<h6><c:out value="${exception.getMessage()}"/></h6>
    </c:if>
</div>
</form>
</body>
</html>