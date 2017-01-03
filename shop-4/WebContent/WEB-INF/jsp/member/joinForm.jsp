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
			<td colspan="4" align="center"><b>회원가입 페이지</b></td>
		</tr>
		<tr>
			<td rowspan="4" align="center" valign="bottom">
			<img src=""
				id="picture2" width="100" height="120"><br> 
			<a
				href="javascript:win_open()"><font size="1">사진등록</font>
			</a></td>
			<td>아이디</td>
			<td><form:input path="id" />
			<font color="red"><form:errors path="id" /></font></td>
		</tr>
		<tr>
			<td>비밀번호:</td>
			<td><form:password path="pass" />
			<font color="red"><form:errors path="pass" /></font></td>
		</tr>
		<tr>
			<td>이름:</td>
			<td><form:input path="name" />
			<font color="red"><form:errors path="name" /></font></td>
		</tr>
		<tr>
			<td>생년월일:</td>
			<td><form:input path="birthDay" />
			<font color="red"><form:errors path="birthDay" /></font></td>
		</tr>
		<tr>
			<td>성별:</td>
			<td colspan="3">
				<input type="radio" name="gender" value="남" checked="checked" />남자 
				<input type="radio" name="gender" value="여" />여자</td>
		</tr>
		<tr>
			<td>이메일:</td>
			<td colspan="3"><input type="text" name="email" size="30"/></td>
		</tr>
		<tr>
			<td>우편번호:</td>
			<td colspan="3"><input type="text" name="postCode" size="30"/></td>
		</tr>
		<tr>
			<td>주소:</td>
			<td colspan="3"><input type="text" name="address" size="30"/></td>
		</tr>
		<tr><td colspan="4" align="center">
		<a href="javascript:document.joinform.submit()">[회원가입]</a>
		<a href="javascript:document.joinform.reset()">[다시작성]</a>
		</td></tr>
	</table>
</form:form>