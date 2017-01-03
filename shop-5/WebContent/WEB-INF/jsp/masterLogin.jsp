<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인 화면</title>
</head>
<body>
	<div align="center">
		<h2>로그인 화면</h2>
		<c:if test="${not empty param.login_error }">
			<font color="red"> ${SPRING_SECURITY_LAST_EXCEPTION.message} </font>
		</c:if>
		<form action="j_spring_security_check" method="post">
			<table>
				<tr height="40px">
					<td>아이디</td>
					<td><input type="text" name="j_username" size="20"></td>
				</tr>
				<tr height="40px">
					<td>비밀번호</td>
					<td><input type="password" name="j_password" size="20"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="로그인"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>