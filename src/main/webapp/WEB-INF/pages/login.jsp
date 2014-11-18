<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="CONTEXT_PATH" value="<%=request.getContextPath()%>"
	scope="application" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Immortals - Login</title>
<link rel="stylesheet" type="text/css"
	href="${CONTEXT_PATH}/resources/css/login-form.css">
</head>
<body>
	<div id="login_el">
		<h2>login</h2>
		<form method="GET" name="loginForm"
			action="${CONTEXT_PATH}/cemetery/list">
			<!-- INCA NU FUNCTIONEAZA -->

			<c:if test="${errorMessage == true}">
				<div id="error">
					<spring:message code="login.auth.failed" />
				</div>
			</c:if>
			<div class="input">
				<label for="name" class="entypo-user"></label> <input type="text"
					value="" name="j_username" id="j_username" placeholder="username" />
			</div>
			<div class="input">
				<label for="name" class="entypo-lock"></label> <input
					type="password" name="j_password" id="j_password"
					placeholder="******" />
			</div>
			<input type="submit" value="Sign In" />
		</form>
	</div>
</body>
</html>