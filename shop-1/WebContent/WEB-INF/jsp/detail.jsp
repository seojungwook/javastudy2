<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상품 상세 화면</title>
</head>
<body>
	<h2>상품 상세 화면</h2>
	<table>
		<tr>
			<td><img src="img/${item.pictureUrl }"></td>
			<td align="center">
				<table>
					<tr>
						<td>상품명</td>
						<td>${item.itemName }</td>
					</tr>
					<tr>
						<td>가격</td>
						<td>${item.price }원</td>
					</tr>
					<tr>
						<td>비고</td>
						<td>${item.description}</td>
					</tr>
					<tr>
						<td colspan="2"><a href="index.html">목록보기</a></td>
					</tr>
				</table>
			</td>
	</table>
</body>
</html>