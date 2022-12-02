<%@page contentType="text/html; charset=utf-8" %>
<%-- <%@page import="java.util.*, model.*" %> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<script>
function changeLogout()  {
	const btnElement = 
		document.getElementById('btn');
	btnElement.value = "로그아웃";
}
</script>
<head>
<style>
a {
	text-decoration: none;
}
</style>
<script>
</script>
<title>Insert title here</title>
</head>
<body>
	<div style="background-color: #333">
		<table>
			<tr>
				<th align="left">
				<a href="<c:url value='/main'>
			 		 </c:url>"><img style="max-width: 10%; height: auto;" src="https://lh3.googleusercontent.com/fife/AAbDypBmp_8yg2IZZNtOPh0AVxGsfA76PcPV_gmydZVZzNBx3UAcpKjqbrsqxTMu7qGnxdb_ICqnTqixR1_8bXeka3NCf1pU1QjqrRE92aQ0AlHvytN5z1NgfDVgeJHhnSLt3HAkx3qz99TFfK7fF7rR21GVg4d1lLTEEkZQLADdioqzpTZEVjTS_jAcz0up6GmCaJTb1Ip9o12xphcMoDgk9e9xjdXrNvItKdAJG2qu5-9A1AOy3uWmfs_dtprvNHQ04KpQ9YURUjdrR2hXY4YH4YvOiHJng-Cj9bLxTpbwXW8Arq2kOyDe8o8FCWVlI5GNZN42JB0UV2HJXo3fb9vXWNgzpPlN8mKzie3gCr5BAVBqyxPowl4LUizks8szBcOhb2W7pHfVxYnny8l5sq8UTi37w05HwmchryFt1DCBo9SZ0Qke-ahC5az4-zmxHELeJh5wS6YNiy4MybLzOdpwuE2C4g-ISY1qXSktlZId2AmQL9kTV-ldSYqnAuvvfz57wZCZoLFo8Q4gmDXXzqfv9fZDfPqbm_WwLDzEgB9LXSHKlr_Fvd8R63PsMggYnaQec2n2Sj3eVOFRjITtNdFe3IrRaE2sO0_9OVZ-O4oWpxlWGTlx6BUNBxkPIqybaTF9YgZILGevxrUHVVm_BHLdjrU0WZNgHqPOLtdo-JKYcDOCDLeQbST17YgU6RLwU7lMup1GAJu8S4KjVrve1Tgy8rALrP0gQKKDAcPXZyUrkrydCii8DGS7GQfdkcLij5s1NEMlDVTq4GjiN8v2ynUom4E8t2DXCCgfSuLP58u1ormj5DzmLLFxRTOLGjIzK-dFXksAi1jq6r7Z6tnZ2mC9GSFDvvCqt1x_81-k3_SNOS3-tHgi5mUwLhSzar7-d1aa8GHbbG72DI1TQ4segEl9wGnaEQsSysy9U4C6lpXZ_7eAQSiRR_shMCCb5qcL-FvSBZA_OlbcHd0BBGFa_KrQL4qDCISXkz9QZpMSALbMAGIs7XsuckRu8oVE9RKvvr5hyeFIu7hC4fCYvEUmbKB4su4bLPcsdkl59gMqHYxaZW9tCVIBhS1h6RWiopWcqSLx52N4VHz9U4tr45AqJD1WNX7orcgB7gQEe9XOElGdvuMGT_5FxrrM-v1mYkFqEQHQIxkAH6OhhxdPwDLNepfADm9YoNd3Eq488TVItIqJOLqw8UYz7BQcCEgLlsf_hqW0oUBu4YJOpXjU-Hf87D24WT2e5eU4tWWEjSOuOmrebu2PWVId5YdOvRj1P5jtIf04eYdAHbhPGXnLpUVmU1dByCmzVFTu5g7Joc9gsD3mu8o5NSj29iDFZhnDALZmjHEKv4m722f4L7Lt3WdClGbptOrd7djtl6Bb18Ci5Qoo2Qa9NJ3rtHPYznFpsTCnIjeIJtNCqyrsUdoLJXnkyqs-0fMgzJfL0pcAOi6PNnbJYZL5_F0m0cYlJHsgNXeUk6Pyenj3fXLIBM46oLObzeriPw=w1920-h937" 
 				 style="width:100%"></a>
 				 </th>
				<th align="right">
				<c:choose>
					<c:when test="${sessionScope.customerId eq null}">
						<a href="<c:url value='/customer/Login'/>">로그인</a>
						</c:when>
					<c:otherwise>
						<a href="<c:url value='/customer/Logout'/>">로그아웃</a>
						</c:otherwise>
					</c:choose>
				</th>
				<th align="right">|</th>
				<th align="right"><a href=narvar.jsp " style="color: white">장바구니</a></th>
				<th align="right">|</th>
				<th align="right">
					<a href="<c:url value='/customer/myPage'>
					<c:param name='customerId' value='${customerId}'/></c:url>">마이페이지</a>
				</th>
			</tr>
		</table>
	</div>
</body>
</html>