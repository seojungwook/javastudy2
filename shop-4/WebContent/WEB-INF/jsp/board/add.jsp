<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<form:form modelAttribute="board" action="write.html"
	enctype="multipart/form-data" name="boardform">
	<table cellpadding="0" cellspacing="0">
		<tr align="center" valign="middle">
			<td colspan="2">������ �Խ��� ���</td>
		</tr>
		<tr>
			<td height="25">�۾���</td>
			<td><form:input path="id" />
			<font color="red"><form:errors path="id" /></font></td>
		</tr>
		<tr>
			<td height="25">��й�ȣ</td>
			<td><form:password path="pass" />
			<font color="red"><form:errors path="pass" /></font>
			</td>
		</tr>
		<tr>
			<td height="25">����</td>
			<td><form:input path="subject" />
			<font color="red"><form:errors path="subject" /></font>
			</td>
		</tr>
		<tr>
			<td height="25">����</td>
			<td><form:textarea path="content" cols="67" rows="15" />
			<font color="red"><form:errors path="content" /></font>
			</td>
		</tr>
		<tr>
			<td height="25">����÷��</td>
			<td><input type="file" name="file1"></td>
		</tr>
		<tr align="center" valign="middle">
			<td colspan="2"><a href="javascript:document.boardform.submit()">[���]</a>
				<a href="list.html">[��Ϻ���]</a></td>
		</tr>
	</table>
</form:form>