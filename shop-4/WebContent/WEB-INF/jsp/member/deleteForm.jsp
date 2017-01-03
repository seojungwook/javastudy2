<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<table border="1" cellspacing="0" cellpadding="0">
<tr><td align="center" colspan="3">회원 탈퇴 정보</td></tr>
	<tr>
		<td rowspan="7" align="center" width="30%"><img
			src="../picture/${member.pictureUrl}" width="200" height="250">
		</td>
		<td>아이디</td>
		<td>${member.id}</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${member.name}</td>
	</tr>
	<tr>
		<td>생일</td>
		<td>${member.birthDay}</td>
	</tr>
	<tr>
		<td>이메일</td>
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
		<td>비밀번호</td>
		<td colspan="2"><form action="delete.html" method="post">
				<input type="password" name="pass"> 
				<input type="hidden" name="id" value="${member.id}"> 
				<input type="submit" value="탈퇴">
			</form></td>
	</tr>
</table>