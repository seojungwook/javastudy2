<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="replyr.html" method="post" name="boardform">
	<input type="hidden" name="num" value="${board.num}"> <input
		type="hidden" name="ref" value="${board.ref}"> <input
		type="hidden" name="refLevel" value="${board.refLevel}"> <input
		type="hidden" name="refStep" value="${board.refStep}">
	<table border="1" cellspacing="0" cellpadding="0">
		<tr align="center" valign="middle">
			<td colspan="2">�Խ���</td>
		</tr>
		<tr>
			<td height="16" align="center">�ۼ���</td>
			<td><input type="text" name="id" value="${board.id }"></td>
		</tr>
		<tr>
			<td height="16" align="center">����</td>
			<td><input type="text" name="subject" value="${board.subject }"></td>
		</tr>
		<tr>
			<td height="16" align="center">��й�ȣ</td>
			<td><input type="password" name="pass"></td>
		</tr>
		<tr>
			<td>����</td>
			<td><textarea name="content" cols="67" rows="15"></textarea></td>
		</tr>
		<tr><td colspan="2">
		<a href="javascript:document.boardform.submit()">[��۵��]</a>
		<a href="list.html?pageNum=${param.pageNum}">[���]</a>
		</td>
		</tr>
	</table>
</form>