<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><spring:message code="itemMenu.title" /></title>
</head>
<body>
<%-- update.html
1.유효성 검증 :  Controller에 메서드 선언부를 코딩 
2.파일 업로드 : 
3.db에 itemId 해당하는 레코드를 변경 : 

 --%>
	<form:form method="post" action="search.html">
		<h2>
			<spring:message code="itemMenu.title" />
		</h2>
상품명 검색<input type="text" name="itemName" value="${itemName }" />
		<input type="submit" value="검색">
		<a href="create.html"> 상품등록</a>
		<table border="1">
			<tr>
				<th align="center" width="80">상품 ID</th>
				<th align="center" width="320">상품명</th>
				<th align="center" width="100">가격</th>
				<th align="center" width="80">편집</th>
				<th align="center" width="80">제거</th>
			</tr>
			<c:forEach items="${itemList }" var="item">
				<tr>
					<td align="center">${item.itemId }</td>
					<td align="left"><a href="detail.html?itemId=${item.itemId}">${item.itemName }</a></td>
					<td align="right">
					<f:formatNumber type="CURRENCY" currencySymbol="" 
					value="${item.price}" minFractionDigits="0" />원</td>
					<td align="center">
					<a href="<c:url value='edit.html'> <c:param name="itemId" value = "${item.itemId }" />
                     </c:url>">상품편집</a></td>
					<td align="center"><a
						href="confirm.html?itemId=${item.itemId}">상품제거</a></td>
			</c:forEach>
		</table>
	</form:form>
</body>
</html>