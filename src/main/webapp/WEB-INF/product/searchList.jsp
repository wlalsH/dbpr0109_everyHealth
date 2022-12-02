<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.util.*, model.*" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>상품 상세</title> 
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<html>
<head>
<title>제품</title>
</head>

<body>
<%@include file="/WEB-INF/basicBar.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="/WEB-INF/category.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<table style="width:100%">
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr>
	<td width="20"></td>
	<td>
	  <table>
		<tr>
		  <td class="title">&nbsp;&nbsp;검색 결과&nbsp;&nbsp;</td>
		</tr>
	  </table>  
	  <c:forEach var="product" items="${searchList}">  
  		<span style="width:15%; height:200px; border:1px solid black; float:left;">
  		<table>
  			<tr>
  				<td>
				<a href="<c:url value='/product/view'>
					   <c:param name='productId' value='${product.productId}'/>
			 		 </c:url>"><img src="<c:url value='${product.image}'/>" width="150" height="100"/></a></td>
  			</tr>
  			<tr>
  				<td>
  				<a href="<c:url value='/product/view'>
					   <c:param name='productId' value='${product.productId}'/>
			 		 </c:url>">
			 		 ${product.name}</a>
				</td>
  			</tr>
  			<tr>
  				<td>${product.price}</td>
  			</tr>
  		 </table> 
  		</span>
	  </c:forEach>
	  </td>
  </tr>
</table>
</body>
</html>