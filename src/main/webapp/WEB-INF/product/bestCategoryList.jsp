<%@page contentType="text/html; charset=utf-8" %>
<%-- <%@page import="java.util.*, model.*" %> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	@SuppressWarnings("unchecked") 
	List<User> userList = (List<User>)request.getAttribute("userList");
	String curUserId = (String)request.getAttribute("curUserId");
--%>
<html>

<head>
<title>제품</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
</script>
</head>
<body>
</head>

<body>

<table style="width:100%">
  <tr>
	<td width="20"></td>
	<td>
	<table>
		<tr>
		  <td class="title">&nbsp;&nbsp;<b>카테고리별 랭킹</b>&nbsp;&nbsp;</td>
		</tr>
	  </table>
 
	  <br>		  
	<table>
		<tr>
		<td><button type="button" id="btnSight" class="btn btn-raised btn-success">sight</button></td>
		<td><button type="button" id="btnDigestion" class="btn btn-raised btn-success">digestion</button></td>
		<td><button type="button" id="btnImmune" class="btn btn-raised btn-success">immune</button></td>
		<td><button type="button" id="btnFatigue" class="btn btn-raised btn-success">fatigue</button></td>
		<td><button type="button" id="btnJoint" class="btn btn-raised btn-success">joint</button></td>
		<td><button type="button" id="btnWoman" class="btn btn-raised btn-success">woman</button></td>
		<td><button type="button" id="btnMan" class="btn btn-raised btn-success">man</button></td>
		<td><button type="button" id="btnKids" class="btn btn-raised btn-success">kids</button></td>
		</tr>
	 </table>
	 
  	<div id ="sight" style="width:80%; height:50%; border:1px solid black; float:left;">
  	  <table> 
  	    <tr>
		  <!-- <td width="200" align="center" bgcolor="E6ECDE" height="22">커뮤니티 ID</td> -->
		  <th>순위</th>
		  <th>상품명</th>
		  <th>가격</th>
		</tr>
		<c:forEach var="i" items="${sightList}" varStatus="status"> 
  	  	<tr>
  	  		<td>${status.count}</td>
  			<td>
  			<a href="<c:url value='/product/view'>
					   <c:param name='productId' value='${i.productId}'/>
			 		 </c:url>">${i.name}</a>
			</td>
  			<td>${i.price}</td>
		</tr>
		</c:forEach>
  	  </table>
  	</div>
  	
  	<div id ="digestion" style="width:80%; height:50%; border:1px solid black; float:left;">
  	  <table> 
  	    <tr>
		  <!-- <td width="200" align="center" bgcolor="E6ECDE" height="22">커뮤니티 ID</td> -->
		  <th>순위</th>
		  <th>상품명</th>
		  <th>가격</th>
		</tr>
		<c:forEach var="i" items="${digestionList}" varStatus="status"> 
  	  	<tr>
  	  		<td>${status.count}</td>
  			<td>
  			<a href="<c:url value='/product/view'>
					   <c:param name='productId' value='${i.productId}'/>
			 		 </c:url>">${i.name}</a>
			</td>
  			<td>${i.price}</td>
		</tr>
		</c:forEach>
  	  </table>
  	</div>
  	
  	<div id ="immune" style="width:80%; height:50%; border:1px solid black; float:left;">
  	  <table> 
  	    <tr>
		  <!-- <td width="200" align="center" bgcolor="E6ECDE" height="22">커뮤니티 ID</td> -->
		  <th>순위</th>
		  <th>상품명</th>
		  <th>가격</th>
		</tr>
		<c:forEach var="i" items="${immuneList}" varStatus="status"> 
  	  	<tr>
  	  		<td>${status.count}</td>
  			<td>
  			<a href="<c:url value='/product/view'>
					   <c:param name='productId' value='${i.productId}'/>
			 		 </c:url>">${i.name}</a>
			</td>
  			<td>${i.price}</td>
		</tr>
		</c:forEach>
  	  </table>
  	</div>
  	
  	<div id ="fatigue" style="width:80%; height:50%; border:1px solid black; float:left;">
  	  <table> 
  	    <tr>
		  <!-- <td width="200" align="center" bgcolor="E6ECDE" height="22">커뮤니티 ID</td> -->
		  <th>순위</th>
		  <th>상품명</th>
		  <th>가격</th>
		</tr>
		<c:forEach var="i" items="${fatigueList}" varStatus="status"> 
  	  	<tr>
  	  		<td>${status.count}</td>
  			<td>
  			<a href="<c:url value='/product/view'>
					   <c:param name='productId' value='${i.productId}'/>
			 		 </c:url>">${i.name}</a>
			</td>
  			<td>${i.price}</td>
		</tr>
		</c:forEach>
  	  </table>
  	</div>
  	
  	<div id ="joint" style="width:80%; height:50%; border:1px solid black; float:left;">
  	  <table> 
  	    <tr>
		  <!-- <td width="200" align="center" bgcolor="E6ECDE" height="22">커뮤니티 ID</td> -->
		  <th>순위</th>
		  <th>상품명</th>
		  <th>가격</th>
		</tr>
		<c:forEach var="i" items="${jointList}" varStatus="status"> 
  	  	<tr>
  	  		<td>${status.count}</td>
  			<td>
  			<a href="<c:url value='/product/view'>
					   <c:param name='productId' value='${i.productId}'/>
			 		 </c:url>">${i.name}</a>
			</td>
  			<td>${i.price}</td>
		</tr>
		</c:forEach>
  	  </table>
  	</div>
  	
  	<div id ="woman" style="width:80%; height:50%; border:1px solid black; float:left;">
  	  <table> 
  	    <tr>
		  <!-- <td width="200" align="center" bgcolor="E6ECDE" height="22">커뮤니티 ID</td> -->
		  <th>순위</th>
		  <th>상품명</th>
		  <th>가격</th>
		</tr>
		<c:forEach var="i" items="${womanList}" varStatus="status"> 
  	  	<tr>
  	  		<td>${status.count}</td>
  			<td>
  			<a href="<c:url value='/product/view'>
					   <c:param name='productId' value='${i.productId}'/>
			 		 </c:url>">${i.name}</a>
			</td>
  			<td>${i.price}</td>
		</tr>
		</c:forEach>
  	  </table>
  	</div>
  	
  	<div id ="man" style="width:80%; height:50%; border:1px solid black; float:left;">
  	  <table> 
  	    <tr>
		  <!-- <td width="200" align="center" bgcolor="E6ECDE" height="22">커뮤니티 ID</td> -->
		  <th>순위</th>
		  <th>상품명</th>
		  <th>가격</th>
		</tr>
		<c:forEach var="i" items="${manList}" varStatus="status"> 
  	  	<tr>
  	  		<td>${status.count}</td>
  			<td>
  			<a href="<c:url value='/product/view'>
					   <c:param name='productId' value='${i.productId}'/>
			 		 </c:url>">${i.name}</a>
			</td>
  			<td>${i.price}</td>
		</tr>
		</c:forEach>
  	  </table>
  	</div>
  	
  	<div id ="kids" style="width:80%; height:50%; border:1px solid black; float:left;">
  	  <table> 
  	    <tr>
		  <!-- <td width="200" align="center" bgcolor="E6ECDE" height="22">커뮤니티 ID</td> -->
		  <th>순위</th>
		  <th>상품명</th>
		  <th>가격</th>
		</tr>
		<c:forEach var="i" items="${kidsList}" varStatus="status"> 
  	  	<tr>
  	  		<td>${status.count}</td>
  			<td>
  			<a href="<c:url value='/product/view'>
					   <c:param name='productId' value='${i.productId}'/>
			 		 </c:url>">${i.name}</a>
			</td>
  			<td>${i.price}</td>
		</tr>
		</c:forEach>
  	  </table>
  	</div>
  	
  	<script type="text/javascript">
        $(function() {
        	$('#digestion').hide();
        	$('#immune').hide();
        	$('#fatigue').hide();
        	$('#joint').hide();
        	$('#woman').hide();
        	$('#man').hide();
        	$('#kids').hide();
            $('#btnSight').click(function() { // 버튼 클릭시
            	$('#digestion').hide();
            	$('#immune').hide();
            	$('#fatigue').hide();
            	$('#joint').hide();
            	$('#woman').hide();
            	$('#man').hide();
            	$('#kids').hide();
            	$('#sight').show(); 
 			});
            $('#btnDigestion').click(function() { // 버튼 클릭시
            	$('#sight').hide();
            	$('#immune').hide();
            	$('#fatigue').hide();
            	$('#joint').hide();
            	$('#woman').hide();
            	$('#man').hide();
            	$('#kids').hide();
            	$('#digestion').show();

     		});
            $('#btnImmune').click(function() { // 버튼 클릭시
            	$('#sight').hide();
            	$('#digestion').hide();
            	$('#fatigue').hide();
            	$('#joint').hide();
            	$('#woman').hide();
            	$('#man').hide();
            	$('#kids').hide();
            	$('#immune').show();
 			});
            $('#btnFatigue').click(function() { // 버튼 클릭시
            	$('#sight').hide();
            	$('#digestion').hide();
            	$('#immune').hide();
            	$('#joint').hide();
            	$('#woman').hide();
            	$('#man').hide();
            	$('#kids').hide();
              	$('#fatigue').show();
            
     		});
            $('#btnJoint').click(function() { // 버튼 클릭시
            	$('#sight').hide();
            	$('#digestion').hide();
            	$('#immune').hide();
            	$('#fatigue').hide();
            	$('#woman').hide();
            	$('#man').hide();
            	$('#kids').hide();
            	$('#joint').show();
       
     		});
            $('#btnWoman').click(function() { // 버튼 클릭시
            	$('#sight').hide();
            	$('#digestion').hide();
            	$('#immune').hide();
            	$('#fatigue').hide();
            	$('#joint').hide();
            	$('#man').hide();
            	$('#kids').hide();
            	$('#woman').show();

     		});
            $('#btnMan').click(function() { // 버튼 클릭시
            	$('#sight').hide();
            	$('#digestion').hide();
            	$('#immune').hide();
            	$('#fatigue').hide();
            	$('#joint').hide();
            	$('#woman').hide();
            	$('#kids').hide();
            	$('#man').show();

     		});
            $('#btnKids').click(function() { // 버튼 클릭시
            	$('#sight').hide();
            	$('#digestion').hide();
            	$('#immune').hide();
            	$('#fatigue').hide();
            	$('#joint').hide();
            	$('#woman').hide();
            	$('#man').hide();
            	$('#kids').show();

     		});
    });

    </script>
  </tr>
</table>
</body>
</html>