<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>īƮ��ǰ Ȯ���ϱ�</title>
</head>
<body>
	<h2>īƮ ��ǰ Ȯ���ϱ�</h2>
	<table>
		<tr>
			<td colspan="4"><font color="green"> īƮ�� ������ ���� ��ǰ��
					����ֽ��ϴ� . </font></td>
		</tr>
		<tr>
			<td width="200">��ǰ��</td>
			<td width="100">����</td>
			<td width="100">����</td>
			<td width="100" align="center">�հ�</td>
			<c:set var="tot" value="${0}" />
			<!-- 
			${cart.itemList} 
			List<ItemSet>cart.getItemList() �޼��� ȣ���
			-->
			<c:forEach items="${cart.itemList}" var="itemSet" varStatus="stat">
				<tr>
					<td>${itemSet.item.itemName}</td>
					<td>${itemSet.item.price}</td>
					<td>${itemSet.quantity}</td>
					<td align="right">${itemSet.quantity * itemSet.item.price}��
					<a href="cartDelete.html?index=${stat.index}">��</a>
					</td>
					<c:set var="tot"
						value="${tot+(itemSet.quantity * itemSet.item.price)}" />
				</tr>
				
			</c:forEach>
		<tr>
			<td colspan="4" align="right">
			<font color="green">�ѱ��Ա��Աݾ� : ${tot} ��</font>
			</td>
		</tr>
	</table>
	<br>${message}<br>
	<a href="../item/list.html">��ǰ��Ϻ���</a><br>
	<a href="../checkout/checkout.html">����ϱ�</a><br>
</body>
</html>