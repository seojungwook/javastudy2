<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<form action="mailForm.html" method="post">
	<table border="1" width="100%">
		<tr align="center">
			<td colspan="8">회원목록</td>
		</tr>
		<tr>
			<th>사진</th>
			<th>아이디</th>
			<th>이름</th>
			<th>성별</th>
			<th>생일</th>
			<th>이메일</th>
			<th colspan="2">&nbsp;</th>

		</tr>
		<c:forEach var="mem" items="${memberlist}">
		<input type="hidden" name="id" value="${mem.id}">
		
			<tr align="center">
				<td><img src="../picture/sm_${mem.pictureUrl}" width="50"
					height="50"></td>
				<td><a href="info.html?id=${mem.id}">${mem.id}</a></td>
				<td>${mem.name}</td>
				<td>${mem.gender}</td>
				<td><f:formatDate value="${mem.birthDay}" pattern="yyyy년MM월dd일" />
				</td>
				<td>${mem.email}</td>
				<td><a href="updateForm.html?id=${mem.id}">[수정]</a> <a
					href="deleteForm.html?id=${mem.id}">[강퇴]</a> <a
					href="mypage.html?id=${mem.id}">[주문정보]</a></td>

				<td><input type="checkbox" name="chk" value="${mem.id}"/></td>

			</tr>
		</c:forEach>
		<tr>
			<td colspan="8" align="center"><input type="submit" value="메일보내기"></td>
		</tr>
	</table>
</form>