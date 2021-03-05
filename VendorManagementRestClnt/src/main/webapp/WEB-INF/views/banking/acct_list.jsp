<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--import JSTL supplied core tag lib --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--import spring supplied JSP tag lib --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table style="background-color: cyan; margin: auto;">
		<caption>Vendor Account List</caption>
		<thead>
			<tr>
				<th>Account No</th>
				<th>Type</th>
				<th>Balance</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="a" items="${requestScope.accts}">
				<tr>
					<td>${a.id}</td>
					<td>${a.acctType}</td>
					<td>${a.balance}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<h5>
		<a href="<spring:url value='/vendor/details'/>">Back To Vendor Details</a>
	</h5>
</body>
</html>