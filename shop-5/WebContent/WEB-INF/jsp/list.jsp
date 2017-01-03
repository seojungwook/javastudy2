<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��ǰ���</title>
</head>
<body>
	<form action="search.html" method="post">
		<h2>��ǰ���</h2>
		��ǰ�� �˻� <input type="text" name="itemName"> <input
			type="submit" value="�˻�"> <a href="create.html">��ǰ���</a>
		<table border="1" cellspacing="0" cellpadding="0">
			<tr>
				<th align="center" width="80">��ǰ���̵�</th>
				<th align="center" width="320">��ǰ��</th>
				<th align="center" width="100">����</th>
				<th align="center" width="80">����</th>
				<th align="center" width="80">����</th>
			</tr>
			<c:forEach items="${itemList}" var="item">
				<tr>
					<td align="center">${item.itemId}</td>
					<td align="left"><a href="detail.html?itemId=${item.itemId}">
							${item.itemName}</a></td>
					<td align="left"><f:formatNumber type="CURRENCY" value="${item.price}"
							minFractionDigits="0" />��</td>
					<td><a href="edit.html?itemId=${item.itemId }">��ǰ����</a></td>
					<td><a href="confirm.html?itemId=${item.itemId }">��ǰ����</a></td>
				</tr>
			</c:forEach>
		</table>
		<br> 
		<a href="../j_spring_security_Logout">�α׾ƿ�</a>
	</form>
</body>
</html>