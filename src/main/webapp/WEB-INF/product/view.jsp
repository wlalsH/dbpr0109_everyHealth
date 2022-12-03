<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*, model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Product product = (Product)request.getAttribute("product");
	
	@SuppressWarnings("unchecked") 
	List<Product> productList = (List<Product>)request.getAttribute("productList");
	String productId = (String)request.getAttribute("productId");
%>
<html>
<head>
<title>상품 상세</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
function payment() {
	
}

function cartCreate(targetUri) {  //수정 필요
	form.action = targetUri;
	form.method="GET";		// register form 요청
	form.submit();
}
</script>
</head>

<body>
<%@include file="/WEB-INF/basicBar.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/category.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<table style="width:100%">
    <tr>
	  <td width="20"></td>
	  <td>
	    <table>
		  <tr>
			<td class="title">&nbsp;&nbsp;<b>상품 상세 정보</b>&nbsp;&nbsp;</td>
		  </tr>
	    </table>  
	    <br>	  	    
	  	<table style="background-color: YellowGreen">
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">상품 이미지</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				<img src=${product.image} style="width:300px; height:300px;">
			</td>
		  </tr>
	  	  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">상품명</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${product.name}
			</td>
		  </tr>
		  <tr>  
			<td width="120" align="center" bgcolor="E6ECDE" height="22">가격</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${product.price}
			</td>
		  </tr>
		  <tr>
			<td width="120" align="center" bgcolor="E6ECDE" height="22">상세설명</td>
			<td width="470" bgcolor="ffffff" style="padding-left: 10">
				${product.description}
			</td>
		  </tr>
	 	</table>
	 	<table style="width:100%">
		  <tr>
			<td align=left>
			<input type="button" value="구매하기" onClick="location.href='<c:url value="/order/form?productid=${product.productId }"/>'"> &nbsp;
			<input type="button" value="장바구니 담기" onClick="createCart(
								'<c:url value='/shop/cart'/>')">  <!--수정해야 함. -->
			</td>		 				
		  </tr> 
	    </table>
	 </td>
	 </tr>
</table>
	 	
</body>
</html>