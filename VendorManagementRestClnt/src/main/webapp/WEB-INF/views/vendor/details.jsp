<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--import spirng supplied JSP tag lib --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5 style="color: green;">${sessionScope.message}</h5>
	<h5 style="color: green;">${requestScope.ac_status}</h5>
	<h5>Vendor Details : ${sessionScope.user_details}</h5>
	<h5>
		<a href="<spring:url value='/banking/login'/>">NetBanking Login</a>
	</h5>
	<h5>
		<a href="<spring:url value='/banking/create_acct'/>">Open New Bank Account</a>
	</h5>
	<h5>
		<a href="<spring:url value='/user/logout'/>">Log Me Out</a>
	</h5>
</body>
</html>