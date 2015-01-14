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
<title>Modifica decedat</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<h2>Date contract</h2>
			<form:form name="editContract" modelAttribute="contract"
				action="${CONTEXT_PATH}/contract/edit" method="POST">
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
						<td><form:input type="hidden" path="expiredDate"></form:input></td>
						<td><form:input type="hidden" path="updatedDate"></form:input></td>
						<td><form:input type="hidden" path="releaseDate"></form:input></td>
					</tr>
					<tr>
						<td><label>Numar</label></td>
						<td><form:input type="text" path="receiptNr"
								required="required" placeholder="Numar" maxLengh="45"></form:input></td>
						<td><form:errors path="receiptNr" cssClass="error" id="redError"/></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>CNP</label></td>
						<td><form:input type="text" path="cnp" required="required"
								placeholder="CNP" maxLengh="45"></form:input></td>
						<td><form:errors path="cnp" cssClass="error" id="redError"/></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Nume</label></td>
						<td><form:input type="text" path="lastName"
								required="required" placeholder="Nume" maxLengh="45"></form:input></td>
						<td><form:errors path="lastName" cssClass="error" id="redError"/></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Prenume</label></td>
						<td><form:input type="text" path="firstName"
								required="required" placeholder="Prenume" maxLengh="45"></form:input></td>
						<td><form:errors path="firstName" cssClass="error" id="redError"/></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Adresa</label></td>
						<td><form:input type="text" path="address"
								required="required" placeholder="Adresa" maxLengh="45"></form:input></td>
						<td><form:errors path="address" cssClass="error" id="redError"/></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>E-mail</label></td>
						<td><form:input type="email" path="emailAddress"
								placeholder="E-mail" maxLengh="45"></form:input></td>
						<td><form:errors path="emailAddress" cssClass="error" id="redError"/></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Perioada</label></td>
						<td><form:input type="number" path="period"
								required="required" placeholder="Perioada"></form:input></td>
						<td><form:errors path="period" cssClass="error" id="redError"/></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Cimitirul</label></td>
						<td><form:select path="grave.plot.cemetery.id">
								<c:forEach var="cemetery" items="${cemeteries}">
									<option value="${cemetery.id}">
										<c:out value="${cemetery.name}" />
									</option>
								</c:forEach>
							</form:select></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Parcela</label></td>
						<td><form:select path="grave.plot.id">
								<c:forEach var="plot" items="${plots}">
									<option value="${plot.id}">
										<c:out value="${plot.name}" />
									</option>
								</c:forEach>
							</form:select></td>
					</tr>
					<tr></tr>
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
					<tr></tr>
					<tr>
						<td><label>Reinnoire </label></td>
						<td><input type="checkbox" name="r" value="update" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td colspan="2" align="left"><input type="submit"
							value="Modifica contract" class="button medium square blue" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>