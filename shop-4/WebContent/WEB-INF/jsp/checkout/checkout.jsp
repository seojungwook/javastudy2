<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���ȭ�� ����</title>
</head>
<body>
<form action="end.html?address=${address}&postCode=${postCode}">
	<h2>���ȭ��</h2>
	<font color="red"><b>��۹�����</b></font>
	<table>
		<tr>
			<td>�����ID</td>
			<td>${loginMember.id}</td>
		</tr>
		<tr>
			<td>�̸�</td>
			<td>${loginMember.name}</td>
		</tr>
		<tr>
			<td>�����ȣ</td>
			<td>${loginMember.postCode}</td>
		</tr>
		<tr>
			<td>�ּ�</td>
			<td>${loginMember.address}</td>
		</tr>
		<tr>
			<td>E-mail</td>
			<td>${loginMember.email}</td>
		</tr>
	</table>
	<br>
	<br>
	<font color="red"><b>���θ��</b></font>
	<table border="1">
		<tr>
			<th width="200">��ǰ��</th>
			<th width="200">����</th>
			<th width="200">����</th>
			<th width="200">�Ұ�</th>
		</tr>
		<c:forEach items="${itemList }" var="itemSet">
			<tr>
				<td align="left">${itemSet.item.itemName }</td>
				<td align="right">${itemSet.item.price }��</td>
				<td align="right">${itemSet.quantity }��</td>
				<td align="right">${itemSet.item.price * itemSet.quantity }��</td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<b>�հ��Ѿ�:${totalAmount }��</b>
	<br>
	<br>
	
	
		<input type="submit" value="Ȯ��">
	</form>
	<a href="../item/list.html">��Ϻ���</a>
	<br>
</body>
</html>