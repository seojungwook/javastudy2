<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>

<table border="0">
	<tr align="center">
		<td width="20%"><a
			href="${pageContext.request.contextPath}/item/list.html">상품목록 </a></td>

		<c:choose>
			<c:when test="${not empty CART_KEY}">
				<td width="20%"><a
					href="${pageContext.request.contextPath}/cart/cartlist.html">
						카트보기 </a></td>

				<td width="20%"><a
					href="${pageContext.request.contextPath}/board/list.html"> 게시판</a></td>
			</c:when>
			<c:otherwise>
				<td width="20%">카트보기</td>
				<td width="20%"><a
					href="${pageContext.request.contextPath}/board/list.html"> 게시판</a></td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${not empty USER_KEY}">
			<c:if test="${USER_KEY.id=='admin' }">
			<td width="20%"><a href="${pageContext.request.contextPath}/member/admin.html">
			[관리자화면]
			</a>
			</td>
			</c:if>
			<c:if test="${USER_KEY.id != 'admin' }">
			<td width="20%"><a
					href="${pageContext.request.contextPath}/member/mypage.html?id=${USER_KEY.id}">마이페이지</a>
				</td>
				</c:if>
			</c:when>
			<c:otherwise>
				<td width="20%"><a
					href="${pageContext.request.contextPath}/member/joinForm.html">회원가입</a>
				</td>
			</c:otherwise>
		</c:choose>
		<td width="20%"><c:choose>
				<c:when test="${not empty USER_KEY}">
					<font color="green">환영합니다 .</font>
			${USER_KEY.id}님<br>
					<a href="${pageContext.request.contextPath}/member/logout.html">로그아웃</a>
				</c:when>
				<c:otherwise>
					<font color="green"><a
						href="${pageContext.request.contextPath}/member/loginForm.html">로그인</a>
					</font>
				</c:otherwise>
			</c:choose>
	</tr>
</table>