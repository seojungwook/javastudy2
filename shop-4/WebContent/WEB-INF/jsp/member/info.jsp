<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<table border="1" width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td rowspan="7" align="center" width="30%"><img
			src="../picture/${member.pictureUrl}" width="200" height="250" /></td>
		<td width="15%">아이디</td>
		<td>${member.id}</td>
	</tr>
	<tr>
		
		<td width="15%">이름</td>
		<td>${member.name}</td>
	</tr>
	<tr>
		
		<td width="15%">생일</td>
		<td><f:formatDate value="${member.birthDay}" pattern="yyyy년 MM월dd일" /></td>
	</tr>
	<tr>
		
		<td width="15%">성별</td>
		<td>${member.gender}</td>
	</tr>
	<tr>
		
		<td width="15%">이메일</td>
		<td>${member.email}</td>
	</tr>
	<tr>
		
		<td>우편번호</td>
		<td colspan="2">${member.postCode}</td>
	</tr>
	<tr>
		
		<td>주소</td>
		<td colspan="2">${member.address}</td>
	</tr>
	<tr>
		<td colspan="3" align="center"><a
			href="updateForm.html?id=${member.id}">[수정]</a> <a
			href="deleteForm.html?id=${member.id}">[탈퇴]</a> <c:if
				test="${USER_KEY.id == 'admin'}">
				<a href="admin.html">[회원목록]</a>
			</c:if></td>
	</tr>
</table>