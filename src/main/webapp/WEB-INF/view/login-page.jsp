<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Custom Login Page</title>
</head>
<body>
	<h3>My Customer Login Page</h3>
</body>

<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
	<p>
		<!-- name must be username and password -->
		Username: <input type="text" name="username" />
	</p>
	<p>
		Password: <input type="password" name="password" />
	</p>
	<input type="submit" value="Login" />
</form:form>
</html>