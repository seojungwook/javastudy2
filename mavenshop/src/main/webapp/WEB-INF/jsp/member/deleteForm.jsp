<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<table border="1" cellspacing="0" cellpadding="0">
<tr><td align="center" colspan="3">ȸ�� Ż�� ����</td></tr>
	<tr>
		<td rowspan="7" align="center" width="30%"><img
			src="../picture/${member.pictureUrl}" width="200" height="250">
		</td>
		<td>���̵�</td>
		<td>${member.id}</td>
	</tr>
	<tr>
		<td>�̸�</td>
		<td>${member.name}</td>
	</tr>
	<tr>
		<td>����</td>
		<td>${member.birthDay}</td>
	</tr>
	<tr>
		<td>�̸���</td>
		<td>${member.email}</td>
	</tr>
	<tr>
		<td>�����ȣ</td>
		<td colspan="2">${member.postCode}</td>
	</tr>
	<tr>
		<td>�ּ�</td>
		<td colspan="2">${member.address}</td>
	</tr>
	<tr>
		<td>��й�ȣ</td>
		<td colspan="2"><form action="delete.html" method="post">
				<input type="password" name="pass"> 
				<input type="hidden" name="id" value="${member.id}"> 
				<input type="submit" value="Ż��">
			</form></td>
	</tr>
</table>