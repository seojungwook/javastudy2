<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���οϷ�</title>
</head>
<body>
	<h3>�̿��� �ּż� �����մϴ� .</h3>
	<%-- �ֹ� ������ �߰��ϱ�--%>
	<h3>��۹����� ��</h3>
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
	<c:set var="tot" value="0" />
	<table border="1">
		<tr>
			<th width="200">��ǰ��</th>
			<th width="200">����</th>
			<th width="200">����</th>
			<th width="200">�Ұ�</th>
		</tr>
		<c:forEach items="${sale.saleLine }" var="saleLine">
			<tr>
				<td align="left">${saleLine.item.itemName}</td>
				<td align="right">${saleLine.item.price}��</td>
				<td align="right">${saleLine.quantity}��</td>
				<td align="right">${saleLine.item.price * saleLine.quantity}��</td>
			</tr>
			<c:set var="tot"
				value="${tot + (saleLine.item.price * saleLine.quantity)}" />
		</c:forEach>
	</table>
	<br>
	<b>�հ��Ѿ�:${totalAmount }��</b>
	<br>
	<b>${tot}</b>
	<br>
	<a href="../item/list.html">��Ϻ���</a>
</body>
</html>