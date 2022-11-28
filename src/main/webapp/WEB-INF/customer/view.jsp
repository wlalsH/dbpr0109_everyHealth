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
function customerRemove() {
	return confirm("정말 탈퇴하시겠습니까?");		
}
</script>
</head>
<body>
<div style="width:500px; position:absolute; top:50%; left:50%; transform:translate(-50%,-50%);" align="center">
	<h3>회원정보확인</h3>
	<table class="table table-bordered table-striped-columns" style="margin-top:20px;">
		<tr>
			<td width="20%">아이디</td>
			<td>${customer.customerId}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${customer.name}</td>
		</tr>
		<tr>
			<td>휴대전화</td>
			<td>${customer.phoneNumber}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${customer.emailAddr}</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${customer.address}</td>
		</tr>
	</table>
	
	<a class="btn btn-dark" style="color:#bbdb18;" href="<c:url value='/customer/update' >
		<c:param name='customerId' value='${customer.customerId}'/>
	</c:url>">정보 수정</a>&nbsp;
	<a class="btn btn-dark" style="color:#bbdb18;" href="<c:url value='/customer/delete' >
		<c:param name='customerId' value='${customer.customerId}'/>
	</c:url>" onclick="return customerRemove();">탈퇴</a>&nbsp;
	<a class="btn btn-dark" style="color:#bbdb18;" href="<c:url value='/customer/myPage' >
		<c:param name='customerId' value='${customer.customerId}'/>
	</c:url>">뒤로 가기</a>
	
	<c:if test="${updateFailed || deleteFailed}">
		<h6><c:out value="${exception.getMessage()}"/></h6>
    </c:if>
</div>
</body>
</html>