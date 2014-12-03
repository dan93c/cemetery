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
<title>Adauga parcela</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<h2>Date despre parcela</h2>
			<form:form name="addPlot" modelAttribute="plot"
				action="${CONTEXT_PATH}/plot/add" method="POST">
				<s:bind path="*">
					<c:if test="${status.error}">
						<div id="message" class="error">
							Error.Invalid data.
							<form:errors />
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
						<td><label>Nume</label></td>
						<td><form:input type="text" path="name" required="required"
								placeholder="Nume parcela"></form:input></td>
						<td><form:errors path="name" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Cimitirul</label></td>
						<td><form:select path="cemetery.id">
								<c:forEach var="cemetery" items="${cemeteries}">
									<option value="${cemetery.id}">
										<c:out value="${cemetery.name}" />
									</option>
								</c:forEach>
							</form:select>
						</td>
						<td><form:errors path="cemetery" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td colspan="2" align="left"><input type="submit"
							value="Adauga parcela" class="button medium square blue"/></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>