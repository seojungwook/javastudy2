<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><spring:message code="itemDelete.title" /></title>
</head>
<body>
<%--
delete.html기능
1.itemId에 해당하는 상품을 제거 
Controller 추가 
DB에 삭제 모튤 추가 
 --%>
	<form:form modelAttribute="item" action="delete.html">
	<form:hidden path="itemId"/>
		<h2>
			<spring:message code="itemDelete.title" />
		</h2>
		<table>
			<tr height="40px">
				<td>상품명</td>
				<td><form:input path="itemName" disabled="true" /></td>
			<tr height="40px">
				<td>가격</td>
				<td><form:input path="price" disabled="true" /></td>
			</tr>
			<tr height="40px">
				<td>상품이미지</td>
				<td><img src="../img/${item.pictureUrl}" width="210" height="210" /></td>
			</tr>
			<tr height="40px">
				<td>개요</td>
				<td><form:textarea path="description" disabled="true" /></td>
			<tr height="40px">
				<td colspan="2"><input type="submit" value="삭제">&nbsp;
					<input type="button" value="목록보기"
					onclick="location.href='list.html'"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>