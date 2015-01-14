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
<title>Modifica decedat fara apartinator</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<h2>Date despre decedat</h2>
			<form:form name="editDeadWithoutFamily" modelAttribute="deadWithoutFamily"
				action="${CONTEXT_PATH}/dead2/edit" method="POST">
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
					<tr>
						<td><label>Adeverinta inhumare</label></td>
						<td><form:input type="text" path="funeralCertificate"
								required="required" placeholder="Adeverinta inhumare"
								maxLengh="45"></form:input></td>
						<td><form:errors path="funeralCertificate" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Solicitare IML</label></td>
						<td><form:input type="text" path="imlRequest"
								required="required" placeholder="Solicitare IML" maxLengh="45"></form:input></td>
						<td><form:errors path="imlRequest" cssClass="error" /></td>
					</tr>
					<tr></tr>
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
					<tr></tr>
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
						<td colspan="2" align="left"><input type="submit"
							value="Modifica decedat" class="button medium square blue" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>

</body>
</html>