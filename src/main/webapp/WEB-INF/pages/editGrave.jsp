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
<title>Adauga mormant</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<h2>Date despre mormant</h2>
			<form:form name="addGrave" modelAttribute="grave"
				action="${CONTEXT_PATH}/grave/edit" method="POST">
				<s:bind path="*">
					<c:if test="${status.error}">
						<div id="message" class="error">
							Error.Invalid data.
							<form:errors />
						</div>
					</c:if>
					<c:if test="${not empty errorMessage}">
						<span>${errorMessage}</span>
					</c:if>
				</s:bind>
				<table class="height-tr">
					<tr>
						<td><form:input type="hidden" path="id"></form:input></td>
					</tr>
					<tr>
						<td><label>Numarul</label></td>
						<td><form:input type="text" path="nrGrave"
								required="required" placeholder="Numar mormant" maxLengh="45"></form:input></td>
						<td><form:errors path="nrGrave" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Tipul</label></td>
						<td><form:input type="text" path="type" placeholder="Tipul"
								maxLengh="45"></form:input></td>
						<td><form:errors path="type" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Suprafata</label></td>
						<td><form:input type="text" path="surface"
								required="required" placeholder="Suprafata"></form:input></td>
						<td><form:errors path="surface" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Poza scanata</label></td>
						<td><form:input type="text" path="photoScanned"
								maxLengh="200"></form:input></td>
						<td><form:errors path="photoScanned" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Observatii</label></td>
						<td><form:input type="text" path="observations"
								placeholder="Observatii" maxLengh="100"></form:input></td>
						<td><form:errors path="observations" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Cimitirul</label></td>
						<td><select name="cemeterySelect">
								<c:forEach var="cemetery" items="${cemeteries}">
									<c:choose>
										<c:when test="${cemetery.id eq grave.plot.cemetery.id}">
											<option value="${cemetery.id}" selected="selected">${cemetery.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${cemetery.id}">${cemetery.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Parcela</label></td>
						<td><form:select path="plot.id">
								<c:forEach var="plot" items="${plots}">
									<c:choose>
										<c:when test="${plot.id eq grave.plot.id}">
											<option value="${plot.id}" selected="selected">${plot.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${plot.id}">${plot.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>
						<td><form:errors path="plot" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td colspan="2" align="left"><input type="submit"
							value="Modifica mormant" class="button medium square blue" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>