<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>사용자 등록 화면</title>
</head>
<body>
<!--modelAttribute="user"
    @ModelAttribute라는 어노테이션을 가진 메서드가 가 넘겨주는(리턴) 객체를 넘겨 받는다 
    -->
	<h2>사용자 등록</h2>
	<form:form modelAttribute="user" method="post" atction="userEntry.html">
		<spring:hasBindErrors name="user">
			<font color="red"> <c:forEach items="${errors.globalErrors }"
					var="error">
					<spring:message code="${error.code }" />
				</c:forEach>
			</font>
		</spring:hasBindErrors>
		<table>
			<tr>
				<td>유저ID</td>
				<%--
				path="userId"
				  id = "userId"
				  name="userId"
				  value="user.getUserId()"
				 --%>
				<td><form:input path="userId" /> <font color="red"> <form:errors
							path="userId" /></font></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><form:password path="password" /> <font color="red">
						<form:errors path="password" />
				</font></td>
			</tr>
			<tr height="40px">
				<td>이름</td>
				<td><form:input path="userName" /> <font color="red"> <form:errors
							path="userName" /></font></td>
			</tr>
			<tr height="40px">
				<td>우편번호</td>
				<td><form:input path="postCode" /> <font color="red"> <form:errors
							path="postCode" /></font></td>
			</tr>
			<tr height="40px">
				<td>주소</td>
				<td><form:input path="address" /> <font color="red"> <form:errors
							path="address" /></font></td>
			</tr>
			<tr height="40px">
				<td>이메일</td>
				<td><form:input path="email" /> <font color="red"> <form:errors
							path="email" /></font></td>
			</tr>
			<tr height="40px">
				<td>직책</td>
				<td><form:select path="job">
						<form:option value="임원" label="임원" />
						<form:option value="영업" label="영업" />
						<form:option value="관리" label="관리" />
						<form:option value="기획" label="기획" />
						<form:option value="기타" label="기타" />
					</form:select></td>
			</tr>
			<tr height="40px">
				<td>생년월일</td>
				<td><form:input path="birthDay" /> <font color="red"> <form:errors
							path="birthDay" />
				</font></td>
			</tr>
			<tr height="40px">
				<td colspan="2" align="center"><input type="submit" value="등록" />
					<input type="reset" value="리셋" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>