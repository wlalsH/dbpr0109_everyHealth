<%@page contentType="text/html; charset=utf-8" %>
<%-- <%@page import="java.util.*, model.*" %> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script>
function search() {
	if (form.keyword.value == "") {
		alert("검색어를 입력하세요");
		form.keyword.focus();
		return false;
	} 
	form.submit();
}
</script>
<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    background-color: #333;
}
ul:after{
    content:'';
    display: block;
    clear:both;
}
li {
    float: left;
}
li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}
li a:hover:not(.active) {
    background-color: #111;
}

</style>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
 <ul>
      <li><a href="sight">sight</a></li>
      <li><a href="#digestion">digestion</a></li>
      <li><a href="#immune"> immune</a></li>
      <li><a href="#fatigue">fatigue</a></li>
      <li><a class="joint">joint</a></li>
      <li><a href="woman">woman</a></li>
      <li><a href="#man"> man</a></li>
      <li><a href="#kids">kids</a></li>
   	  <table align="right">
      	<tr>
 	   		<td><input type="text" style="width:240" name="keyword"></td>
       		<td><button onclick="search()" class="btn btn-outline-success" type="submit">Search</button></td>
  		</tr>
  		</table>
    </ul>
  
</body>
</html>