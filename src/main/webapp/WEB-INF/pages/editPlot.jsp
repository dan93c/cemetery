<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="sources.jsp" />
<title>Modifica parcela</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<h2>Date despre parcela</h2>
			<form:form name="editPlot" modelAttribute="plot"
				action="${CONTEXT_PATH}/plot/edit" method="POST">
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
						<td><label>Nume</label></td>
						<td><form:input type="text" path="name" required="required"
								placeholder="Nume parcela"></form:input></td>
						<td><form:errors path="name" cssClass="error" id="redError"/></td>
					</tr>
					<tr></tr>
					<tr>
						<td><label>Cimitirul</label></td>
						<td><form:select path="cemetery.id">
								<c:forEach var="cemetery" items="${cemeteries}">
									<c:choose>
										<c:when test="${cemetery.id eq plot.cemetery.id}">
											<option value="${cemetery.id}" selected="selected">${cemetery.name}</option>
										</c:when>
										<c:otherwise>
											<option value="${cemetery.id}">${cemetery.name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</form:select></td>
						<td><form:errors path="cemetery" cssClass="error" id="redError"/></td>
					</tr>

					<tr></tr>

					<tr>
						<td colspan="2" align="left"><input type="submit"
							value="Modifica parcela" class="button medium square blue" /></td>
						<td></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
</html>