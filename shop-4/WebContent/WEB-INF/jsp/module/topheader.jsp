<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>

<table border="0">
	<tr align="center">
		<td width="20%"><a
			href="${pageContext.request.contextPath}/item/list.html">��ǰ��� </a></td>

		<c:choose>
			<c:when test="${not empty CART_KEY}">
				<td width="20%"><a
					href="${pageContext.request.contextPath}/cart/cartlist.html">
						īƮ���� </a></td>

				<td width="20%"><a
					href="${pageContext.request.contextPath}/board/list.html"> �Խ���</a></td>
			</c:when>
			<c:otherwise>
				<td width="20%">īƮ����</td>
				<td width="20%"><a
					href="${pageContext.request.contextPath}/board/list.html"> �Խ���</a></td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${not empty USER_KEY}">
			<c:if test="${USER_KEY.id=='admin' }">
			<td width="20%"><a href="${pageContext.request.contextPath}/member/admin.html">
			[������ȭ��]
			</a>
			</td>
			</c:if>
			<c:if test="${USER_KEY.id != 'admin' }">
			<td width="20%"><a
					href="${pageContext.request.contextPath}/member/mypage.html?id=${USER_KEY.id}">����������</a>
				</td>
				</c:if>
			</c:when>
			<c:otherwise>
				<td width="20%"><a
					href="${pageContext.request.contextPath}/member/joinForm.html">ȸ������</a>
				</td>
			</c:otherwise>
		</c:choose>
		<td width="20%"><c:choose>
				<c:when test="${not empty USER_KEY}">
					<font color="green">ȯ���մϴ� .</font>
			${USER_KEY.id}��<br>
					<a href="${pageContext.request.contextPath}/member/logout.html">�α׾ƿ�</a>
				</c:when>
				<c:otherwise>
					<font color="green"><a
						href="${pageContext.request.contextPath}/member/loginForm.html">�α���</a>
					</font>
				</c:otherwise>
			</c:choose>
	</tr>
</table>