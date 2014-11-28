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
<link href='<c:url value="/resources/css/bootstrap.css" />'
	rel="stylesheet">
<title>Programare inmormantare</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<h2>Date despre decedat</h2>
			<form:form name="editFuneralFile" modelAttribute="funeralFile"
				action="${CONTEXT_PATH}/funeral/edit" method="POST">
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
				<table>
					<tr>
						<td><form:input type="hidden" path="id"></form:input></td>
					</tr>
					<tr>
						<td><label>Nume</label></td>
						<td><form:input type="text" path="dead.lastName"
								required="required" placeholder="Nume decedat" maxLengh="45"></form:input></td>
						<td><form:errors path="dead.lastName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Prenume</label></td>
						<td><form:input type="text" path="dead.firstName"
								required="required" placeholder="Prenume decedat" maxLengh="45"></form:input></td>
						<td><form:errors path="dead.firstName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Religia</label></td>
						<td><form:input type="text" path="dead.religion"
								required="required" placeholder="Religia" maxLengh="45"></form:input></td>
						<td><form:errors path="dead.religion" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Data mortii</label></td>
						<td><form:input type="date" path="dead.deathDate"></form:input></td>
						<td><form:errors path="dead.deathDate" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Data inmormantarii</label></td>
						<td><form:input type="datetime-local" path="funeralDate" pattern="yyyy-MM-dd"></form:input></td>
						<td><form:errors path="funeralDate" cssClass="error" /></td>
					</tr>
					<tr>
						<td><label>Cimitirul</label></td>
						<td><select name="cemeterySelect">
								<c:forEach var="cemetery" items="${cemeteries}">
									<option value="${cemetery.id}">
										<c:out value="${cemetery.name}" />
									</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Parcela</label></td>
						<td><select name="plotSelect">
								<c:forEach var="plot" items="${plots}">
									<option value="${plot.id}">
										<c:out value="${plot.name}" />
									</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Mormantul</label></td>
						<td><form:select path="grave.id">
								<c:forEach var="grave" items="${graves}">
									<option value="${grave.id}">
										<c:out value="${grave.nrGrave}" />
									</option>
								</c:forEach>
							</form:select></td>
					</tr>
					<tr>
						<td colspan="2" align="left"><input type="submit"
							value="Modifica"
							class="button medium square blue" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>