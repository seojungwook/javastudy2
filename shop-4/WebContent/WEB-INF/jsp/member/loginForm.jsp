<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<form:form modelAttribute="member" name="loginForm" action="login.html"
	method="post">
	<%--의미없는 값 유효성 검증용 Member bean클래스의 name유효성 검사 회피용  --%>
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
			<td colspan="2" align="center">로그인</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td><form:input path="id" /> <font color="red"><form:errors
						path="id" /></font></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><form:password path="pass" />
			<font color="red"><form:errors path="pass" /></font></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a
				href="javascript:loginForm.submit()">로그인</a> 
				<a href="joinForm.html">회원가입</a>
			</td>
	</table>
</form:form>