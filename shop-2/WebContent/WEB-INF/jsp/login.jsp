<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%--
    form : html �±׿� ���� 
           html �±� + spring ����߰� 
    spring : spring ��ɰ� ������      
     --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�α��� ȭ��</title>
</head>
<body>
	<h2>�α��� ȭ��</h2>
	<form:form modelAttribute="user" method="post" action="login.html">
		<spring:hasBindErrors name="user">
			<font color="red"> <c:forEach items="${errors.globalErrors }"
					var="error">
					<spring:message code="${error.code}" />
				</c:forEach>
			</font>
		</spring:hasBindErrors>
		<table>
			<tr height="40px">
				<td>���� ID</td>
				<td><form:input path="userId" /> <font color="red"> <form:errors
							path="userId" />
				</font>
			</tr>
			<tr height="40px">
				<td>��й�ȣ</td>
				<td><form:input path="password" /> <font color="red"> <form:errors
							path="password" />
				</font>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="�α���">&nbsp;&nbsp;
					<input type="reset" value="����"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>