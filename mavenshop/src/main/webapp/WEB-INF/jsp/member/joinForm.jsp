<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<script>
	function win_open() {
		var op = "width=300, hight=50,scrollbars=yes,resizeable=no,left=50,top=50";
		window.open("memberimg.html", "picture", op);
	}
</script>
<form:form name="joinform" modelAttribute="member"
	action="joinProcess.html" method="POST">
	<form:hidden path="pictureUrl" />
	<table>
		<tr>
			<td colspan="4" align="center"><b>ȸ������ ������</b></td>
		</tr>
		<tr>
			<td rowspan="4" align="center" valign="bottom">
			<img src=""
				id="picture2" width="100" height="120"><br> 
			<a
				href="javascript:win_open()"><font size="1">�������</font>
			</a></td>
			<td>���̵�</td>
			<td><form:input path="id" />
			<font color="red"><form:errors path="id" /></font></td>
		</tr>
		<tr>
			<td>��й�ȣ:</td>
			<td><form:password path="pass" />
			<font color="red"><form:errors path="pass" /></font></td>
		</tr>
		<tr>
			<td>�̸�:</td>
			<td><form:input path="name" />
			<font color="red"><form:errors path="name" /></font></td>
		</tr>
		<tr>
			<td>�������:</td>
			<td><form:input path="birthDay" />
			<font color="red"><form:errors path="birthDay" /></font></td>
		</tr>
		<tr>
			<td>����:</td>
			<td colspan="3">
				<input type="radio" name="gender" value="��" checked="checked" />���� 
				<input type="radio" name="gender" value="��" />����</td>
		</tr>
		<tr>
			<td>�̸���:</td>
			<td colspan="3"><input type="text" name="email" size="30"/></td>
		</tr>
		<tr>
			<td>�����ȣ:</td>
			<td colspan="3"><input type="text" name="postCode" size="30"/></td>
		</tr>
		<tr>
			<td>�ּ�:</td>
			<td colspan="3"><input type="text" name="address" size="30"/></td>
		</tr>
		<tr><td colspan="4" align="center">
		<a href="javascript:document.joinform.submit()">[ȸ������]</a>
		<a href="javascript:document.joinform.reset()">[�ٽ��ۼ�]</a>
		</td></tr>
	</table>
</form:form>