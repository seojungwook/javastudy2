<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- /Webcontent/WED-INF/jsp/index.jsp --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��ǰ ��� ȭ��</title>
</head>
<body>
	<h2>��ǰ ���</h2>
	<table border="1" width="400">
		<tr>
			<th width="50">��ǰid</th>
			<th width="250">��ǰ��</th>
			<th width="100">����</th>
			<th width="50">�̹���</th>
		</tr>
		<c:forEach items="${itemList}" var="item">
			<tr>
				<td align="center">${item.itemId }</td>
				<td align="center"><a href="detail.html?itemId=${item.itemId}">
				${item.itemName }</a></td>
				<td align="center">${item.price }��</td>
				<td align="center"><img src="img/${item.pictureUrl}" width="50" height="50"></td>
		</c:forEach>
	</table>
</body>
</html>