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
<title>Adauga mormant</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<h2>Date despre mormant</h2>
			<form:form name="addGrave" modelAttribute="grave"
				action="${CONTEXT_PATH}/grave/add" method="POST">
				<s:bind path="*">
					<c:if test="${status.error}">
						<div class="alert alert-danger" role="alert">
							<span class="glyphicon glyphicon-exclamation-sign"
								aria-hidden="true"></span> <span class="sr-only">Error:</span>
							Error.Invalid data.
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
					<tr></tr>
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
						<td><select name="cemeterySelect" id="cmm">
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
						<td><form:select path="plot.id">
								<c:forEach var="plot" items="${plots}">
									<option value="${plot.id}">
										<c:out value="${plot.name}" />
									</option>
								</c:forEach>
							</form:select></td>
						<td><form:errors path="plot" cssClass="error" /></td>
					</tr>
					<tr></tr>
					<tr>
						<td colspan="2" align="left"><input type="submit"
							value="Adauga mormant" class="button medium square blue" /></td>
					</tr>
				</table>
			</form:form>
		</div>
	</div>
</body>
<%-- <script src="${CONTEXT_PATH}/resources/js/jquery.js"></script>
<script type=text/javascript>
	$('#cmm').change(function() {
		alert($(this).val());
		$.ajax({
			url : "${CONTEXT_PATH}"+'/plot/getPlotsByCemetery/' + $(this).val(),
			type : 'GET',
			dataType: 'json',
			success : function(response) {
				alert("succes");
				var data = response.partialContent;
				for (var i = 0; i < data.length; i++) {
					var row = data[i];
					alert(row);
				}
			},
			error : function(xhr, status, error) {
				alert(error);
				handleGenericError(xhr, status, error, function() {
					var err = eval("(" + xhr.responseText + ")");
					alert(err);
				});
			}
		});

	});
</script> --%>
</html>