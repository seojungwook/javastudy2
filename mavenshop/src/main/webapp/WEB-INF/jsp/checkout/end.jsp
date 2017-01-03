<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>쇼핑완료</title>
</head>
<body>
	<h3>이용해 주셔서 감사합니다 .</h3>
	<%-- 주문 정보를 추가하기--%>
	<h3>배송받으실 분</h3>
	<table>
		<tr>
			<td>사용자ID</td>
			<td>${loginMember.id}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${loginMember.name}</td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td>${loginMember.postCode}</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${loginMember.address}</td>
		</tr>
		<tr>
			<td>E-mail</td>
			<td>${loginMember.email}</td>
		</tr>
	</table>
	<c:set var="tot" value="0" />
	<table border="1">
		<tr>
			<th width="200">상품명</th>
			<th width="200">가격</th>
			<th width="200">갯수</th>
			<th width="200">소계</th>
		</tr>
		<c:forEach items="${sale.saleLine }" var="saleLine">
			<tr>
				<td align="left">${saleLine.item.itemName}</td>
				<td align="right">${saleLine.item.price}원</td>
				<td align="right">${saleLine.quantity}개</td>
				<td align="right">${saleLine.item.price * saleLine.quantity}원</td>
			</tr>
			<c:set var="tot"
				value="${tot + (saleLine.item.price * saleLine.quantity)}" />
		</c:forEach>
	</table>
	<br>
	<b>합계총액:${totalAmount }원</b>
	<br>
	<b>${tot}</b>
	<br>
	<a href="../item/list.html">목록보기</a>
</body>
</html>