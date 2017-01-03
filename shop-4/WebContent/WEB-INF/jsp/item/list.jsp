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
1.��ȿ�� ���� :  Controller�� �޼��� ����θ� �ڵ� 
2.���� ���ε� : 
3.db�� itemId �ش��ϴ� ���ڵ带 ���� : 

 --%>
	<form:form method="post" action="search.html">
		<h2>
			<spring:message code="itemMenu.title" />
		</h2>
��ǰ�� �˻�<input type="text" name="itemName" value="${itemName }" />
		<input type="submit" value="�˻�">
		<a href="create.html"> ��ǰ���</a>
		<table border="1">
			<tr>
				<th align="center" width="80">��ǰ ID</th>
				<th align="center" width="320">��ǰ��</th>
				<th align="center" width="100">����</th>
				<th align="center" width="80">����</th>
				<th align="center" width="80">����</th>
			</tr>
			<c:forEach items="${itemList }" var="item">
				<tr>
					<td align="center">${item.itemId }</td>
					<td align="left"><a href="detail.html?itemId=${item.itemId}">${item.itemName }</a></td>
					<td align="right">
					<f:formatNumber type="CURRENCY" currencySymbol="" 
					value="${item.price}" minFractionDigits="0" />��</td>
					<td align="center">
					<a href="<c:url value='edit.html'> <c:param name="itemId" value = "${item.itemId }" />
                     </c:url>">��ǰ����</a></td>
					<td align="center"><a
						href="confirm.html?itemId=${item.itemId}">��ǰ����</a></td>
			</c:forEach>
		</table>
	</form:form>
</body>
</html>