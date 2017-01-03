<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><spring:message code="itemEdit.title" /></title>
</head>
<body>
	<form:form modelAttribute="item" action="update.html"
		enctype="multipart/form-data">
		<form:hidden path="itemId" />
		<form:hidden path="pictureUrl" />
		<h2>
			<font color="green"> <spring:message code="itemEdit.title" />
			</font>
		</h2>
		<table>
			<tr height="40px">
				<td>��ǰ��</td>
				<td><form:input path="itemName" maxlength="20" /></td>
				<td><font color="red"><form:errors path="itemName" /></font></td>
			</tr>
			<tr height="40px">
				<td>����</td>
				<td><form:input path="price" maxlength="6" /></td>
				<td><font color="red"><form:errors path="price" /></font></td>
			</tr>
			<tr height="40px">
				<td>��ǰ�̹���</td>
				<td><input type="file" name="picture"></td>
				<td>${item.pictureUrl}&nbsp;</td>
			</tr>
			<tr height="40px">
				<td>����</td>
				<td><form:textarea path="description" /></td>
				<td><font color="red"><form:errors path="description" /></font></td>
			</tr>
			<tr height="40px">
				<td colspan="3">
				<input type="submit" value="����">
				<input type="reset" value="����">
				<input type="button" value="��Ϻ���" onclick="location.href='list.html'"> 
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>