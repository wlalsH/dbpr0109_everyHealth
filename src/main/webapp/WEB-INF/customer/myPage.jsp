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
</head>
<body>
<div align = "center">
	<br>
	<h3><b>My Page</b></h3>
	<br>
	<font style="vertical-align:middle;">${customer.name}님 안녕하세요</font>
	<a class="btn btn-dark btn-sm" style="color:#bbdb18; font-size:xx-small;" href="<c:url value='/customer/view' >
	    <c:param name='customerId' value='${customer.customerId}'/>
	</c:url>">내 정보 확인하기</a><br>
	적립금: ${customer.point}원
	<br><br>
	<table class="table" style="width:900px;">
	<caption style="caption-side:top;">최근 주문 정보</caption>
		<thead class="table-secondary">
			<tr>
				<th width="15%">주문일자</th>
				<th>상품명 (수량)</th>
				<th width="15%">배송상태</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="order" items="${customer.orders}">
				<tr>
					<td>${order.orderDate}</td>
					<td>
					<c:forEach var="item" items="${order.items}">
						${item.productName} (${item.quantity})<br>
					</c:forEach>
					</td>
					<td>${order.orderStatus}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>