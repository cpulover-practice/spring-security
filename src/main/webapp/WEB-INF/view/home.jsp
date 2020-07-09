<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<hr>
	<!-- Display user id and role -->
	<p>
		User name:
		<security:authentication property="principal.username" />
	</p>
	<p>
		User role(s):
		<security:authentication property="principal.authorities" />
	</p>
	<hr>

	<!-- Only MANAGERS can see this -->
	<security:authorize access="hasRole('MANAGER')">
		<!-- Add a link points to /leaders, only for managers -->
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership
				Meeting</a> (Only for Managers)
		</p>
	</security:authorize>

	<!-- Only ADMINS can see this -->
	<security:authorize access="hasRole('ADMIN')">
		<!-- Add a link points to /systems, only for admins -->
		<p>
			<a href="${pageContext.request.contextPath}/systems">VIP Meeting</a>
			(Only for Admins)
		</p>
		<hr>
	</security:authorize>
	
	<p>Welcome to Home page!</p>

	<!-- Add logout button -->
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>