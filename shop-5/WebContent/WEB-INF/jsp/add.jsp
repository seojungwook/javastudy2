<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>상품 등록 화면</title>
</head>
<body>
	<form:form modelAttribute="item" action="register.html"
		enctype="multipart/form-data">
		<h2>상품 등록</h2>
		<table border="1" cellspacing="0" cellpadding="0">
			<tr height="40px">
				<td>상품명</td>
				<td><form:input path="itemName" maxlength="20" /></td>
				<td><font color="red"><form:errors path="itemName" /></font></td>
			</tr>
			<tr height="40px">
				<td>가격</td>
				<td><form:input path="price" maxlength="20" /></td>
				<td><font color="red"><form:errors path="price" /></font></td>
			</tr>
			<tr height="40px">
				<td>이미지</td>
				<td><input type="file" name="picture"></td>
				<td></td>
			</tr>
			<tr height="40px">
				<td>비고</td>
				<td><form:textarea path="description" /></td>
				<td><font color="red"><form:errors path="description" /></font></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="등록">
				<a href="list.html">상품목록</a>
				<a href="../j_spring_security_logout">로그아웃</a>
			</td>
			</tr>
		</table>
	</form:form>
</body>
</html>