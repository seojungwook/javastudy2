<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<form:form modelAttribute="member" name="loginForm" action="login.html"
	method="post">
	<%--�ǹ̾��� �� ��ȿ�� ������ Member beanŬ������ name��ȿ�� �˻� ȸ�ǿ�  --%>
	<form:hidden path="name" value="a" />
	<spring:hasBindErrors name="member">
		<font color="red"> <c:forEach items="${errors.globalErrors}"
				var="error">
				<spring:message code="${error.code }" />
			</c:forEach>
		</font>
	</spring:hasBindErrors>
	<table>
		<tr>
			<td colspan="2" align="center">�α���</td>
		</tr>
		<tr>
			<td>���̵�</td>
			<td><form:input path="id" /> <font color="red"><form:errors
						path="id" /></font></td>
		</tr>
		<tr>
			<td>��й�ȣ</td>
			<td><form:password path="pass" />
			<font color="red"><form:errors path="pass" /></font></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a
				href="javascript:loginForm.submit()">�α���</a> 
				<a href="joinForm.html">ȸ������</a>
			</td>
	</table>
</form:form>