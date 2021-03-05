<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form method="post" modelAttribute="bankAccount">
		<table style="background-color: lightgrey; margin: auto;">
			<tr>
				<td>Choose A/C Type</td>
				<td><form:radiobuttons path="acctType"
						items="${requestScope.acct_types}" />
				<td><form:errors path="acctType" /></td>
			</tr>
			<tr>
				<td>Enter Opening Balance</td>
				<td><form:input type="number" path="balance" /></td>
				<td><form:errors path="balance" /></td>
			</tr>
			<tr>
				<td>Enter Bank Customer ID</td>
				<td><form:input path="acctOwner.customerId" /></td>
				<td><form:errors path="acctOwner.customerId" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Create A/C" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>