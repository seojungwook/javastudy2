<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���� ��� ȭ��</title>
</head>
<body>
<h2>���� ��� ȭ��</h2>
<table>
<tr>
<td>userId : ${user.userId}</td></tr>
<tr><td>password : ${user.password}</td></tr>
<tr><td>userName : ${user.userName}</td></tr>
<tr><td>postCode : ${user.postCode}</td></tr>
<tr><td>address : ${user.address}</td></tr>
<tr><td>email : ${user.email }</td></tr>
<tr><td>job : ${user.job }</td></tr>
<tr><td>���� :<fmt:formatDate value="${user.birthDay}" pattern="yyyy�� MM�� dd��"/> </td>

</tr>

</table>
</body>
</html>