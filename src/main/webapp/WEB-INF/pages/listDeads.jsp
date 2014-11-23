<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="sources.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href='<c:url value="/resources/css/bootstrap.css" />' rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<div id="center">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th rowspan="2">#</th>
						<th rowspan="2">Nume</th>
						<th rowspan="2">Prenume</th>
						<th rowspan="2">Religie</th>
						<th colspan="3">Mormant</th>
					</tr>
					<tr>
						<th>Numar</th>
						<th>Parcela</th>
						<th>Cimitir</th>
					</tr>
				</thead>
				<c:forEach var="dead" items="${deads}" varStatus="lineInfo">
					<tr>
						<td>${lineInfo.count}</td>
						<td>${dead.lastName}</td>
						<td>${dead.firstName}</td>
						<td>${dead.religion}</td>
						<td>${dead.grave.nrGrave}</td>
						<td>${dead.grave.plot.name}</td>
						<td>${dead.grave.plot.cemetery.name}</td>
						<td><a href="${CONTEXT_PATH}/dead/edit/${dead.id}"><button>Modifica</button></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>