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
		enctype="multipart/form-data"><!-- "multipart/form-data"���Ͼ��ε� ���� -->
		<h2>
			<font color="green"><spring:message code="itemAdd.title" /></font>
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
				<td>��ǰ �̹���</td>
				<td colspan="2"><input type="file" name="picture" /></td>
			</tr>
			<tr height="40px">
				<td>����</td>
				<td><form:textarea path="description" /></td>
				<td><font color="red"><form:errors path="description" /></font></td>
			</tr>
			<tr height="40px">

				<td colspan="3"><input type="submit" value="���">&nbsp;
					<input type="reset" value="����">&nbsp; <input type="button"
					value="��Ϻ���" onclick="location.href='list.html'">
				
			</tr>
		</table>
	</form:form>
</body>
</html>