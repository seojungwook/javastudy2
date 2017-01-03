<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><spring:message code="itemAdd.title" /></title>
</head>
<body>
	<form:form modelAttribute="item" action="register.html"
		enctype="multipart/form-data"><!-- "multipart/form-data"파일업로드 가능 -->
		<h2>
			<font color="green"><spring:message code="itemAdd.title" /></font>
		</h2>
		<table>
			<tr height="40px">
				<td>상품명</td>
				<td><form:input path="itemName" maxlength="20" /></td>
				<td><font color="red"><form:errors path="itemName" /></font></td>
			</tr>
			<tr height="40px">
				<td>가격</td>
				<td><form:input path="price" maxlength="6" /></td>
				<td><font color="red"><form:errors path="price" /></font></td>
			</tr>
			<tr height="40px">
				<td>상품 이미지</td>
				<td colspan="2"><input type="file" name="picture" /></td>
			</tr>
			<tr height="40px">
				<td>개요</td>
				<td><form:textarea path="description" /></td>
				<td><font color="red"><form:errors path="description" /></font></td>
			</tr>
			<tr height="40px">

				<td colspan="3"><input type="submit" value="등록">&nbsp;
					<input type="reset" value="리셋">&nbsp; <input type="button"
					value="목록보기" onclick="location.href='list.html'">
				
			</tr>
		</table>
	</form:form>
</body>
</html>