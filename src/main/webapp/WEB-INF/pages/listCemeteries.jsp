<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="sources.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>Nume</th>
						<th colspan="2">Adresa</th>
					</tr>
				</thead>
				<c:forEach var="cemetery" items="${cemeteries}" varStatus="lineInfo">
					<tr>
						<td>${cemetery.name}</td>
						<td>${cemetery.address}
						<a href="${CONTEXT_PATH}/cemetery/edit/${cemetery.id}"><button class="btn-table">Modifica</button></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>