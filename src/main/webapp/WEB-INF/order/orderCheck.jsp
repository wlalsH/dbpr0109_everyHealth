<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 확인</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div>
		<b style="font-size: 40px">주문이 완료되었습니다.</b>
	</div>
	<div class="container">
  		<div class="row">
    		<div class="col">
     		상품
    		</div>
   			<div class="col">
      		상품 이름
    		</div>
    		<div class="col">
      		개수
    		</div>
  		</div>
	</div>
	<c:forEach var="item" items="${items}">
		<div class="container">
  			<div class="row">
    			<div class="col">
     			<img src="${item.image}" width="100px" />
    			</div>
   				<div class="col">
      			${item.productName}
    			</div>
    			<div class="col">
      			${item.quantity}
    			</div>
  			</div>
		</div>
	</c:forEach>
</body>
</html>