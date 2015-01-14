<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="sources.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adauga reclamatie</title>
</head>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<h2>Date reclamatie</h2>
			<form:form name="addClaim" modelAttribute="claimBook"
				action="${CONTEXT_PATH}/claim/add" method="POST">
				<s:bind path="*">
					<c:if test="${status.error}">
						<div class="alert alert-danger" role="alert">
							<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
							<span class="sr-only">Error:</span>
								Error.Invalid data.
						</div>
					</c:if>
					<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
						<span class="sr-only">Error:</span>
							${errorMessage}
					</div>
					</c:if>
				</s:bind>
				<table class="height-tr">
					<tr>
						<td><form:input type="hidden" path="id"></form:input></td>
					</tr>
					<tr>
						<td><label>Reclamant</label></td>
						<td><form:input type="text" path="complainer" required="required"
								placeholder="Nume reclamant"></form:input></td>
						<td><form:errors path="complainer" cssClass="error" /></td>
					</tr>
				  	<tr></tr> 
					<tr>
						<td><label>Reclamatie</label></td>
						<td><form:input type="text" path="claims"
								required="required" placeholder="Reclamatie"></form:input></td>
						<td><form:errors path="claims" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td colspan="2" align="left"><input type="submit"
							value="Adauga reclamatie" class="button medium square blue"/></td>
						<td></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</html>