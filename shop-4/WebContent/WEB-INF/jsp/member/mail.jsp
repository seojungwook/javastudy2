<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<script src="//cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
<h2>메일보내기</h2>
<hr>
<form name="form1" method="post" action="main.html">
	<table border="1" cellpadding="5" cellspacing="0">
		<tr>
			<td>보내는 사람</td>
			<td>test@mesedu.co.kr</td>
		</tr>
		<tr>
			<td>받는 사람</td>
			<td><input type="text" name="recipient" size="100" value='<c:forEach items="${memberlist}" var="member">${member.name} &lt;${member.email}&gt;,</c:forEach>'>
			</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td>메세지 형식</td>
			<td><select name="mtype">
					<option value="text/plain;charset=euc-kr">TEXT</option>
					<option value="text/html;charset=euc-kr">HTML</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="2"><textarea name="editor1" cols="40" rows="10"></textarea>
			<script>
			 CKEDITOR.replace('editor1')
			</script>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit" value="전송"></td>
		</tr>
	</table>
</form>