<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��ǰ �� ȭ��</title>
</head>
<body>
	<h2>��ǰ �� ȭ��</h2>
	<table>
		<tr>
			<td><img src="img/${item.pictureUrl }"></td>
			<td align="center">
				<table>
					<tr>
						<td>��ǰ��</td>
						<td>${item.itemName }</td>
					</tr>
					<tr>
						<td>����</td>
						<td>${item.price }��</td>
					</tr>
					<tr>
						<td>���</td>
						<td>${item.description}</td>
					</tr>
					<tr>
						<td colspan="2"><a href="index.html">��Ϻ���</a></td>
					</tr>
				</table>
			</td>
	</table>
</body>
</html>