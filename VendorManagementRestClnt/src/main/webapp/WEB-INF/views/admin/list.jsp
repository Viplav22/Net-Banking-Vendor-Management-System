<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--import JSTL supplied core tag lib --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--import spring supplied JSP tag lib --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>Last Action Status : ${requestScope.message}</h5>
	<table style="background-color: cyan; margin: auto;">
		<caption>Vendor List</caption>
		<thead>
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>Reg Amount</th>
				<th>Reg Date</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="v" items="${requestScope.vendor_list}">
				<tr>
					<td>${v.name}</td>
					<td>${v.email}</td>
					<td>${v.regAmount}</td>
					<td>${v.regDate}</td>
					<td><a href="<spring:url value='/admin/update?vid=${v.id}'/>">Update</a></td>
					<td><a href="<spring:url value='/admin/delete?vid=${v.id}'/>">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h5>
		<a href="<spring:url value='/admin/register'/>">Add New Vendor</a>
	</h5>
	<h5>
		<a href="<spring:url value='/user/logout'/>">Log Me Out</a>
	</h5>
</body>
</html>