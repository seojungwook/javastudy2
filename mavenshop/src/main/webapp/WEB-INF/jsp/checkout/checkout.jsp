<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>계산화면 보기</title>
</head>
<body>
<form action="end.html?address=${address}&postCode=${postCode}">
	<h2>계산화면</h2>
	<font color="red"><b>배송받을곳</b></font>
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
	<br>
	<br>
	<font color="red"><b>쇼핑목록</b></font>
	<table border="1">
		<tr>
			<th width="200">상품명</th>
			<th width="200">가격</th>
			<th width="200">갯수</th>
			<th width="200">소계</th>
		</tr>
		<c:forEach items="${itemList }" var="itemSet">
			<tr>
				<td align="left">${itemSet.item.itemName }</td>
				<td align="right">${itemSet.item.price }원</td>
				<td align="right">${itemSet.quantity }개</td>
				<td align="right">${itemSet.item.price * itemSet.quantity }원</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<b>합계총액:${totalAmount }원</b>
	<br>
	<br>
	
	
		<input type="submit" value="확정">
	</form>
	<a href="../item/list.html">목록보기</a>
	<br>
</body>
</html>