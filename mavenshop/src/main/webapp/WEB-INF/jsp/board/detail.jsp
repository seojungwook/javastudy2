
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>��1 �Խ��� : �Խ��� �󼼹� ����</title>
</head>
<body>
	<table border="1" cellpadding="0" cellspacing="0">
		<tr align="center" valign="middle">
			<td colspan="2">Model 1�Խ���</td>
		</tr>
		<tr>
			<td>�۾��� :${board.id}</td>
		</tr>
		<tr bgcolor="#cccccc">
			<td colspan="2" style="height: 1px;"></td>
		</tr>
		<tr>
			<td>���� :${board.subject}</td>
		</tr>
		<tr bgcolor="#cccccc">
			<td colspan="2" style="height: 1px;"></td>
		</tr>
		<tr>
			<td>����</td>
		<tr bgcolor="#cccccc">
			<td colspan="2" style="height: 1px;"></td>
		</tr>
		<td><table border="0" width="490" height="250">
			<tr>
				<td>${board.content}
			</table></td>
		</tr>
		<tr>
			<td align="left">÷������ :
			&nbsp;
			<c:if test="${!empty board.fileUrl}">
			<a href="../upload/${board.fileUrl}">${board.fileUrl}</a>
			</c:if>
			</td>
		</tr>
		<tr bgcolor="#cccccc">



			<td colspan="2" style="height: 1px;"></td>
		</tr>

		<tr align="center" valign="middle">
			<td colspan="2"><a href="reply.html?num=${board.num}&pageNum=${param.pageNum}">[�亯]</a>&nbsp;&nbsp;
				<a href="modifyf.html?num=${board.num}&pageNum=${param.pageNum}">[����]</a>&nbsp;&nbsp;
				<a href="deletef.html?num=${board.num}&pageNum=${param.pageNum}">[����]</a>&nbsp;&nbsp;
				<a href="list.html?pageNum=${param.pageNum}">[���]</a>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</body>
</html>