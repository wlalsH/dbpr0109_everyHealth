<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ID 중복 확인</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function sendCheckValue() {
	var openRegisterFrm = opener.window.document.registerFrm;
	
	if ($('input[name=chResult]').val()=="N") {
		alert("다른 아이디를 입력해주세요.");
		openRegisterFrm.customerId.focus();
		window.close();
	}
	else {
		$('input[name=idCheck]', opener.document).attr("disabled", true);
		window.close();
	}
}
</script>
</head>
<body>
<div style="position:absolute; top:50%; left:50%; transform:translate(-50%,-50%);" align="center">
	<h4>ID 중복 확인</h4>
	<form name="checkIdFrm">
		<input class="form-control" style="width:250px; text-align:center;" type="text" name="customerid" value="${param.customerId}" disabled>
			
		<c:choose>
			<c:when test="${idCheckResult==true}">
				<p style="color: red">이미 사용 중인 아이디입니다.</p>
				<input type="hidden" name="chResult" value="N"/>
			</c:when>
			<c:when test="${idCheckResult==false}">
				<p style="color: green">사용가능한 아이디입니다.</p>
				<input type="hidden" name="chResult" value="Y"/>
			</c:when>
			<c:otherwise>
				<p>오류 발생(-1)</p>
				<input type="hidden" name="chResult" value="N"/>
			</c:otherwise>
		</c:choose>

		<input class="btn btn-dark" style="color:#bbdb18;" type="button" onclick="window.close()" value="취소"/>
		<input class="btn btn-dark" style="color:#bbdb18;" type="button" onclick="sendCheckValue()" value="사용하기"/>
	</form>
</div>
</body>
</html>