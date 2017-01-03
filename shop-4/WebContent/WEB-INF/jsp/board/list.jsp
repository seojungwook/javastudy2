<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<c:if test="${listcount > 0}">
		<tr align="center" valign="middle">
			<td colspan="4">Spring 게시판</td>
			<td>글개수 :${listcount}</td>
		</tr>
		<tr bgcolor="yellow" align="center" valign="middle"
			bordercolor="#212121">
			<td width="8%" headers="26">번호</td>
			<td width="50%" headers="26">제목</td>
			<td width="14%" headers="26">작성자</td>
			<td width="17%" headers="26">날짜</td>
			<td width="11%" headers="26">조회수</td>
		</tr>
		<c:forEach var="board" items="${boardlist}">

			<tr align="center" valign="middle" bordercolor="#333333"
				onmouseover="this.style.backgroundColor='#5CD1E5'"
				onmouseout="this.style.backgroundColor=''">
				<td height="23">${board.num}</td>
				<td align="left"><c:choose>
						
						<c:when test="${board.refLevel>0}">
							<c:forEach begin="1" end="${board.refLevel}" step="1">
				&nbsp;&nbsp;&nbsp;
				</c:forEach>
				<c:if test="${not empty board.fileUrl}">
				<a href="filedown.html?filename=${board.fileUrl }">@</a>
				</c:if>
							<a href="detail.html?num=${board.num}&pageNum=${pageNum}">${board.subject}</a>
						</c:when>
						<c:otherwise>
						<c:if test="${not empty board.fileUrl}">
				<a href="filedown.html?filename=${board.fileUrl }">@</a>
				</c:if>
							<a href="detail.html?num=${board.num}&pageNum=${pageNum}">${board.subject}</a>
						</c:otherwise>
					
					</c:choose></td>
				<td align="center">${board.name}</td>
                <c:choose>
                <c:when test="${board.getFormat() == today}">
                <td align="center"><f:formatDate value="${board.regDate}" pattern="hh:mm:ss"/></td>
				</c:when>
				<c:otherwise>
				<td align="center"><f:formatDate value="${board.regDate}" pattern="yyyy-MM-dd"/></td>
				</c:otherwise>
                </c:choose>
				
				<td align="center">${board.readCnt}</td>
			</tr>
		</c:forEach>
		<hr>
		<tr align="center" height="26">
			<td colspan="5"><c:if test="${pageNum > 1 }">
					<a href="${msg}.html?pageNum=${pageNum -1}&searchtype=${searchtype}&searchContent=${searchContent}">
				</c:if> [이전]&nbsp; <c:if test="${pageNum > 1}">
					</a>
				</c:if> <c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:if test="${pageNum == a }">[${a}]</c:if>
					<c:if test="${pageNum != a}">
						<a href="${msg}.html?pageNum=${a}&searchtype=${searchtype}&searchContent=${searchContent}">[${a}]</a>
					</c:if>&nbsp;
	</c:forEach> <c:if test="${pageNum < maxpage }">
					<a href="${msg}.html?pageNum=${pageNum+1 }&searchtype=${searchtype}&searchContent=${searchContent}">
				</c:if> [다음]&nbsp; <c:if test="${pageNum < maxpage }">
					</a>
				</c:if></td>
		</tr>
		<tr>
			<td colspan="5" align="center">
				<form action="search.html" method="post">
					<select name="searchtype" id="searchtype">
						<option selected="selected" value="subject">제목</option>
						<option value="id">작성자</option>
						<option value="content">내용</option>
					</select>&nbsp;

					<!-- <script>
					if(${searchContent !=""}){
						document.getElementById("searchtype").value = '${param.searchtype}';						
					};
					</script> -->
					<input type="text" name="searchContent"
						value="${param.searchContent }"> <input type="submit"
						value="검색">
				</form>
			</td>
		</tr>
	</c:if>
	<c:if test="${listcount == 0}">
		<tr>
			<td colspan="5">등록된 게시물이 없습니다 .</td>
		</tr>
	</c:if>
	<tr>
		<td colspan="5"><a href="add.html">[글쓰기]</a></td>
	</tr>
</table>