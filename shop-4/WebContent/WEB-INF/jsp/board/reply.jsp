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
			<td colspan="2">게시판</td>
		</tr>
		<tr>
			<td height="16" align="center">작성자</td>
			<td><input type="text" name="id" value="${board.id }"></td>
		</tr>
		<tr>
			<td height="16" align="center">제목</td>
			<td><input type="text" name="subject" value="${board.subject }"></td>
		</tr>
		<tr>
			<td height="16" align="center">비밀번호</td>
			<td><input type="password" name="pass"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" cols="67" rows="15"></textarea></td>
		</tr>
		<tr><td colspan="2">
		<a href="javascript:document.boardform.submit()">[답글등록]</a>
		<a href="list.html?pageNum=${param.pageNum}">[목록]</a>
		</td>
		</tr>
	</table>
</form>