<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 폼</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<script>
function orderCreate() {
	if (orderForm.name.value == "") {
		alert("이름을 입력하세요.");
		orderForm.name.fouse();
		return false;
	}
	if (orderForm.address.value == "") {
		alert("주소를 입력하세요.");
		orderForm.address.fouse();
		return false;
	}
	if (orderForm.email.value == "") {
		alert("이메일을 입력하세요.");
		orderForm.email.fouse();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(orderForm.email.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		orderForm.email.focus();
		return false;
	}
	if (orderForm.phone.value == "") {
		alert("이메일을 입력하세요.");
		orderForm.phone.fouse();
		return false;
	}
	var phoneExp = /^\d{2,3}-\d{3,4}-\d{4}$/;
	if(phoneExp.test(orderForm.phone.value)==false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		orderForm.phone.focus();
		return false;
	}
	
	orderForm.submit();
}

function applyPoint() {
	var totalPice = <%= request.getAttribute("totalPrice")%>;
	var point = <%= request.getAttribute("customer.point")%>;
	
	if (orderForm.usedPointCheck.value > point) {
		alert("사용 가능 금액을 넘었습니다.");
		orderForm.usedPoint.value = 0;
	}
	
	if (orderForm.usedPointCheck.value > totalPrice) {
		("적립금을 총 주문 금액 이상 사용할 수 없습니다.");
		orderForm.usedPoint.value = 0;
	}
	
	orderForm.usedPointCheck.value = orderForm.usedPoint.value;
	orderForm.finalPrice.value = totalPrice - orderForm.usedPointCheck.value;
}
</script>
<body>
	<%@include file="/WEB-INF/basicBar.jsp" %>
	<div style="padding: 50px;">
	<div>
		<b style="font-size: 40px">Order</b>
	</div>	  
	<br>
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
	<br>
	<c:if test="${orderFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	</c:if>
	<form name="orderForm" method="POST" action="<c:url value='/order/makeOrder'/>">
		<div class="row mb-3">   
	        <label for="name" class="col-sm-2 col-form-label">이름</label>
	        <div class="col-sm-2">
	        	<input type="text" name="name" class="form-control" placeholder="이름" value="${customer.name}" <c:if test="${customer ne null}"> readonly </c:if>> 
	        </div>
	    </div>  
	    <fieldset class="row mb-3">
	    	<legend class="col-sm-2 col-form-label">배송지 선택</legend>
  			<div class="col-sm-10">
     			<div class="form-check form-check-inline">
      				<input class="form-check-input" type="radio" name="addressSelect" id="gridRadios1" value="option1" <c:if test="${customer ne null}"> checked </c:if> <c:if test="${customer eq null}"> disabled </c:if>>
      				<label class="form-check-label" for="gridRadios1">
         	 		  기존 배송지
       				</label>
     			</div>
	     		<div class="form-check form-check-inline">
	       			<input class="form-check-input" type="radio" name="addressSelect" id="gridRadios2" value="option2" <c:if test="${customer eq null}"> checked </c:if>>
	       			<label class="form-check-label" for="gridRadios2">
	         		  새 배송지
	       			</label>
				</div>
   			</div>
	   </fieldset>
		<div class="row mb-3">   
	        <label for="address" class="col-sm-2 col-form-label">주소</label>
	        <div class="col-sm-9">
	        	<input type="text" name="address" class="form-control" placeholder="주소" value="${customer.address}"> 
	        </div>
	    </div>  
		<div class="row mb-3">  
	        <label for="phone" class="col-sm-2 col-form-label">휴대전화</label>
	        <div class="col-sm-5">
	        	<input type="text" name="phone" class="form-control" placeholder="010-XXXX-YYYY" value="${customer.phoneNumber}" <c:if test="${customer ne null}"> readonly </c:if>>  
	        </div>
	    </div> 
	    <div class="row mb-3">  
	        <label for="email" class="col-sm-2 col-form-label">이메일 주소</label>
	        <div class="col-sm-5">
	        	<input type="text" name="email" class="form-control" placeholder="you@example.com" value="${customer.emailAddr}" <c:if test="${customer ne null}"> readonly </c:if>>  
	        </div>
	    </div> 
	    <div class="row mb-3">  
	        <label for="shippingMessage" class="col-sm-2 col-form-label">배송메세지</label>
	        <div class="col-sm-9">
	        	<textarea class="form-control" id="exampleFormControlTextarea1" name="shippingMessage" rows="3" placeholder="배송 메세지"></textarea>
	        </div>
	    </div> 
	    <br>
		<div class="container">
	  		<div class="row">
	    		<div class="col">
	     		총 주문 금액
	    		</div>
	   			<div class="col">
	      		사용할 적립금
	    		</div>
	    		<div class="col">
	      		최종 결제 금액
	    		</div>
	  		</div>
		</div>
		<hr>
		<div class="container">
	  		<div class="row">
	    		<div class="col">
	     		${totalPrice}
	    		</div>
	   			<div class="col">
	      			<div class="row mb-3"> 
	        			<div class="col-sm-6">
	        				<input type="text" name="usedPointCheck" class="form-control" value="0" readonly>  
	        			</div>
	    			</div>
	    		</div>
	    		<div class="col">
	      			<div class="row mb-3"> 
	        			<div class="col-sm-6">
	        				<input type="text" name="finalPrice" class="form-control" value="${totalPrice}" readonly>  
	        			</div>
	    			</div> 
	    		</div>
	  		</div>
		</div>
		<hr>
		<c:if test="${customer ne null}">
			<div class="row mb-3">  
		        <label for="phone" class="col-sm-2 col-form-label">사용 가능 적립금</label>
		        <div class="col-sm-2">
		        	<input type="text" name="point" class="form-control" value="${customer.point}" readonly>  
		        </div>
		    </div> 
		    <div class="row mb-3">  
		        <label for="email" class="col-sm-2 col-form-label">사용할 적립금</label>
		        <div class="col-sm-2">
		        	<input type="text" name="usedPoint" class="form-control" placeholder="0">  
		        </div>
		        <div class="col-sm-2">
	    		<button type="button" class="btn btn-primary" style="background-color: black; color: #c5dc63;" onclick="applyPoint()"><b>포인트 적용</b></button>
	 		</div>  
		    </div>
		</c:if>
		<br>
		<div class="row mb-3">  
	        <label for="account" class="col-sm-2 col-form-label">입금자명</label>
	        <div class="col-sm-2">
	        	<input type="text" name="accountHolder" class="form-control">  
	        </div>
	    </div> 
	    <div class="row mb-3">  
	        <label for="bank" class="col-sm-2 col-form-label">입금 은행</label>
	        <div class="col-sm-5">
	        	<select class="form-select form-select-sm" aria-label="Default select example" name="bankName">
				  <option value="신한 은행"selected>신한 은행</option>
				  <option value="우리 은행">우리 은행</option>
				  <option value="기업 은행">기업 은행</option>
				</select>  
	        </div>
	    </div>
	    <br>
		<div class="row mb-3">  
			<label for="cashReceiptType" class="col-sm-2 col-form-label">현금 영수증 신청</label>
	    	<div class="col-sm-10">
	     		<fieldset class="row mb-3">
	    			<legend class="col-sm-2 col-form-label">구분</legend>
  					<div class="col-sm-10">
     					<div class="form-check form-check-inline">
      						<input class="form-check-input" type="radio" name="cashReceiptType" id="gridRadios1" value="개인">
      						<label class="form-check-label" for="gridRadios1">
         	 		  		개인
       						</label>
     					</div>
	     				<div class="form-check form-check-inline">
	       					<input class="form-check-input" type="radio" name="cashReceiptType" id="gridRadios2" value="사업자">
	       					<label class="form-check-label" for="gridRadios2">
	         		  		사업자
	       					</label>
						</div>
   					</div>
	  		 	</fieldset>
	    	</div>
    	<label for="cashReceiptType" class="col-sm-2 col-form-label"></label>
			<label for="cashReceiptPhone" class="col-sm-2 col-form-label">휴대전화</label>
        	<div class="col-sm-5">
        		<input type="text" name="cashRecepitPhone" class="form-control" placeholder="010-XXXX-YYYY">  
        	</div>
		</div>
	  	<div class="row mb-1"> 
	    	<label for="cashReceiptPhone" class="col-sm-2 col-form-label"></label>
	  	</div>    
	    <div class="row mb-3">
	    	<input type="button" class="btn btn-primary" style="background-color: black; color: #c5dc63;" onClick="orderCreate()" value="주문하기">
 		</div>
	</form> 
	</div>
</body>
</html>