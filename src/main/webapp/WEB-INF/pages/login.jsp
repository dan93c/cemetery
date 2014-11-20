<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="CONTEXT_PATH" value="<%=request.getContextPath()%>"
	scope="application" />
<title>Immortals - Login</title>
<link rel="stylesheet" type="text/css"
	href="${CONTEXT_PATH}/resources/css/login-form.css">

<script type="text/javascript">
	function validLogin() {
		if (document.form.j_username.value == "") {
			alert("Please enter Login Name.");
			document.loginform.userName.focus();
			return false;
		}
		if (document.form.j_password.value == "") {
			alert("Please enter password.");
			document.userform.password.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div id="login_el">
		<h2>login</h2>
		<form method="post" name="loginForm"
			action="${CONTEXT_PATH}/<c:url value='j_spring_security_check'/>"
			onsubmit="return validLogin();">
			<div class="input">
				<label for="name" class="entypo-user"></label> <input type="text"
					value="" name="j_username" id="j_username" placeholder="username"
					required="required" />
			</div>
			<div class="input">
				<label for="name" class="entypo-lock"></label> <input
					type="password" name="j_password" id="j_password"
					placeholder="******" required="required" />
			</div>
			<input type="submit" value="Sign In" title="Sign In" />
		</form>
		<div id="error">
			<c:if test="${errorMessage == true}">
				<spring:message code="login.auth.failed" />
			</c:if>
		</div>
	</div>
</body>
</html>