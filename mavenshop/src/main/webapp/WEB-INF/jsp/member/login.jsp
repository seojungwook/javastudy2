<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인</title>
</head>
<body>
	<h2>로그인</h2>
	<form:form modelAttribute="member" method="post" action="login.html">
		<spring:hasBindErrors name="member">
			<font color="red"> <c:forEach items="${errors.globalErrors }"
					var="error">
					<spring:message code="${error.code }"></spring:message>
				</c:forEach>
			</font>
		</spring:hasBindErrors>

		<table>
			<tr height="40px">
				<td>사용자Id</td>
				<td><form:input path="id" /> <font color="red"><form:errors
							path="id" /></font></td>
			</tr>
			<tr height="40px">
				<td>비밀번호</td>
				<td><form:password path="pass" /> <font color="red"><form:errors
							path="pass" /></font></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="로그인"> <input
					type="button" value="회원가입" onclick="location.href='joinForm.html'" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>