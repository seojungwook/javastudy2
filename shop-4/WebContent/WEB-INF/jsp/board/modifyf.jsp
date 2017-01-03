<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<script>
function file_delete(){
	document.updateform.file2.value ="";
	document.getElementById("file_desc").innerHTML ="";
	}
</script>
<form:form modelAttribute="board" action="modify.html?pageNum=${param.pageNum }" method="post"
	enctype="multipart/form-data" name="updateform">
	<input type="hidden" name="file2" value="${board.fileUrl}" />
	<form:hidden path="num" />
	<form:hidden path="id" />
	<table cellspacing="0" cellpadding="0" border="1">
		<tr align="center" valign="middle">
			<td colspan="2">spring게시판</td>
		</tr>
		<tr>
			<td height="16px" align="center">제목</td>
			<td><form:input path="subject" /> <font color="red"><form:errors
						path="subject" /></font></td>
		</tr>
		<tr>
			<td align="center">내용</td>
			<td><form:textarea path="content" /> <font color="red"><form:errors
						path="content" /></font></td>
		</tr>
		<tr>
			<td align="center">첨부파일</td>
			<td>&nbsp; <c:if test="${!empty board.fileUrl}">
					<div id="file_desc">
						<a href="../fileUpload/${board.fileUrl }">${board.fileUrl}</a>&nbsp;
						<a href="javascript:file_delete()">[첨부파일 삭제]</a>
					</div>
				</c:if> <input type="file" name="file1" id="file1"></td>
				</tr>
				<tr>
			<td height="16px" align="center">비밀번호</td>
			<td><form:password path="pass" /> <font color="red"><form:errors
						path="pass" /></font></td>
		</tr>
		<tr align="center" valign="middle">
		<td colspan="2">
		<a href="javascript:document.updateform.submit()">[수정]</a>&nbsp;
		<a href="list.html?pageNum=${param.pagNum}">[게시글 목록]</a>
		</td></tr>
	</table></form:form>