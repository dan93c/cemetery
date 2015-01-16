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
<title>Modifica cerere atribuire loc de inhumare</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<h2>Date cerere</h2>
			<form:form name="addGraveRequest" modelAttribute="graveRequest"
				action="${CONTEXT_PATH}/graveRequest/edit" method="POST">
				<s:bind path="*">
					<c:if test="${status.error}">
						<div class="alert alert-danger" role="alert">
							<span class="glyphicon glyphicon-exclamation-sign"
								aria-hidden="true"></span> <span class="sr-only">Error:</span>
							Error.Invalid data.
						</div>
					</c:if>
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger" role="alert">
							<span class="glyphicon glyphicon-exclamation-sign"
								aria-hidden="true"></span> <span class="sr-only">Error:</span>
							${errorMessage}
						</div>
					</c:if>
				</s:bind>
				<table class="height-tr">
					<tr>
						<td><form:input type="hidden" path="id"></form:input></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Numar infocet</label></td>
						<td><form:input type="text" path="nrInfocet"
								required="required" placeholder="Numar infocet" maxLengh="45"></form:input></td>
						<td><form:errors path="nrInfocet" cssClass="error"
								id="redError" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Stadiu de solutionare</label></td>
						<td><form:select path="solvingStage">
								<c:choose>
									<c:when
										test="${graveRequest.solvingStage == 'Cerere inregistrata'}">
										<option value="Cerere inregistrata" selected="selected">Cerere
											inregistrata</option>
									</c:when>
									<c:otherwise>
										<option value="Cerere inregistrata">Cerere
											inregistrata</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when
										test="${graveRequest.solvingStage == 'Cerere in curs de solutionare'}">
										<option value="Cerere in curs de solutionare"
											selected="selected">Cerere in curs de solutionare</option>
									</c:when>
									<c:otherwise>
										<option value="Cerere in curs de solutionare">Cerere
											in curs de solutionare</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when
										test="${graveRequest.solvingStage == 'Cerere solutionata'}">
										<option value="Cerere solutionata" selected="selected">Cerere
											solutionata</option>
									</c:when>
									<c:otherwise>
										<option value="Cerere solutionata">Cerere solutionata
										</option>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when
										test="${graveRequest.solvingStage == 'Cerere nesolutionata'}">
										<option value="Cerere nesolutionata" selected="selected">Cerere
											nesolutionata</option>
									</c:when>
									<c:otherwise>
										<option value="Cerere nesolutionata">Cerere
											nesolutionata</option>
									</c:otherwise>
								</c:choose>

							</form:select></td>
						<td><form:errors path="solvingStage" cssClass="error"
								id="redError" /></td>
					</tr>
					<tr>
						<td colspan="2" align="left"><input type="submit"
							value="Modifica cerere" class="button medium square blue" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>