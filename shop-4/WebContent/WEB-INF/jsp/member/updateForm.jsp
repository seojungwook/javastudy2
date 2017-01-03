<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<script>
	function win_open() {
		var op = "width=300, height=50, scrollbars=no, resizeable=no, left=150, top=150";
		window.open("memberimg.html", "picture", op);
	}
</script>
<form:form modelAttribute="member" path="joinform" action="update.html" method="post" name="joinform">
	<input type="hidden" name="id" value="${member.id}"> <input
		type="hidden" name="pictureUrl" value="${member.pictureUrl}">
	<table border="1" cellpadding="0" cellspacing="0">
		<tr>
			<td>아이디</td>
			<td>${member.id}</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><form:input path="pass" />
			<font color="red"><form:errors path="pass" /></font></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><form:input path="name" />
			<font color="red"><form:errors path="name" /></font></td>
		</tr>
		<tr>
			<td>생일</td>
			<td><form:input path="birthDay"/>
				<font color="red"><form:errors path="birthDay" /></font>
				</td>
		</tr>
		<tr>
			<td>성별</td>
			<td><input type="radio" name="gender" id="g1" value="남">남
				<input type="radio" name="gender" id="g2" value="여">여 
				
				<script>
					gender = '${member.gender}';
					if (gender == '남')
						document.getElementById("g1").setAttribute("checked",
								"checked");
					else
						document.getElementById("g2").setAttribute("checked",
								"checked");
				</script></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="${member.email}"></td>
		</tr>
		<tr>
			<td>우편번호</td>
			<td><form:input path="postCode"/>
			<font color="red"><form:errors path="postCode" /></font></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><form:input path="address"/>
			<font color="red"><form:errors path="address" /></font></td>
		</tr>
		<tr>
			<td align="center" colspan="2"><img src="../picture/${member.pictureUrl}"
				id="picture2" width="200" height="150"></td>
		</tr>
		<tr>
			<td align="center" colspan="2"><a href="javascript:win_open()"> <font
					size="1">사진등록</font>
			</a></td>
		</tr>
		<tr><td colspan="2" align="center">
		<a href="javascript:joinform.submit();">회원수정</a>
		<c:choose>
		<c:when test="${USER_KEY.id == 'admin'}">
		<a href="admin.html">회원목록</a>
		</c:when>
		<c:otherwise>
		<a href="history.go(-2)">이전</a>
		</c:otherwise>
		</c:choose>
		</td></tr>
	</table>

</form:form>