<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/jsp/jspHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><spring:message code="itemDetail.title" /></title>
</head>
<body>
	<h2>
		<spring:message code="itemDetail.title" />
	</h2>
	<table>
		<tr>
			<td><img src="../img/${item.pictureUrl}"></td>
			<td align="center">
				<table>
					<tr height="50">
						<td width="80">상품명</td>
						<td width="160">${item.itemName}</td>
						</tr>
						<tr>
						<td width="80">가격</td>
						<td width="160">${item.price}</td>
						</tr>
						<tr>
						<td width="80">비고</td>
						<td width="160">${item.description}</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<form action="../cart/cartAdd.html">
								<input type="hidden" name="itemId" value="${item.itemId}">
								<table>
									<tr>
										<td><select name=quantity>
												<c:forEach begin="1" end="10" var="idx">
													<option>${idx}</option>
												</c:forEach>
										</select></td>
										<td><input type="submit" value="카트에 넣기"></td>
										<td><input type="button" value="목록보기" onclick="history.go(-1)"></td>
									</tr>
								</table>
							</form>
						</td>
					</tr>
				</table>
	</table>
</body>
</html>