<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<table border="1" width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td rowspan="7" align="center" width="30%"><img
			src="../picture/${member.pictureUrl}" width="200" height="250" /></td>
		<td width="15%">���̵�</td>
		<td>${member.id}</td>
	</tr>
	<tr>
		
		<td width="15%">�̸�</td>
		<td>${member.name}</td>
	</tr>
	<tr>
		
		<td width="15%">����</td>
		<td><f:formatDate value="${member.birthDay}" pattern="yyyy�� MM��dd��" /></td>
	</tr>
	<tr>
		
		<td width="15%">����</td>
		<td>${member.gender}</td>
	</tr>
	<tr>
		
		<td width="15%">�̸���</td>
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
		<td colspan="3" align="center"><a
			href="updateForm.html?id=${member.id}">[����]</a> <a
			href="deleteForm.html?id=${member.id}">[Ż��]</a> <c:if
				test="${USER_KEY.id == 'admin'}">
				<a href="admin.html">[ȸ�����]</a>
			</c:if></td>
	</tr>
</table>