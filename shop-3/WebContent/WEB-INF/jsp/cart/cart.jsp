<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>카트물품 확인하기</title>
</head>
<body>
	<h2>카트 물품 확인하기</h2>
	<table>
		<tr>
			<td colspan="4"><font color="green"> 카트에 다음과 같은 상품이
					들어있습니다 . </font></td>
		</tr>
		<tr>
			<td width="200">상품명</td>
			<td width="100">가격</td>
			<td width="100">수량</td>
			<td width="100" align="center">합계</td>
			<c:set var="tot" value="${0}" />
			<!-- 
			${cart.itemList} 
			List<ItemSet>cart.getItemList() 메서드 호출됨
			-->
			<c:forEach items="${cart.itemList}" var="itemSet" varStatus="stat">
				<tr>
					<td>${itemSet.item.itemName}</td>
					<td>${itemSet.item.price}</td>
					<td>${itemSet.quantity}</td>
					<td align="right">${itemSet.quantity * itemSet.item.price}원
					<a href="cartDelete.html?index=${stat.index}">ⓧ</a>
					</td>
					<c:set var="tot"
						value="${tot+(itemSet.quantity * itemSet.item.price)}" />
				</tr>
				
			</c:forEach>
		<tr>
			<td colspan="4" align="right">
			<font color="green">총구입구입금액 : ${tot} 원</font>
			</td>
		</tr>
	</table>
	<br>${message}<br>
	<a href="../item/list.html">상품목록보기</a><br>
	<a href="../checkout/checkout.html">계산하기</a><br>
</body>
</html>