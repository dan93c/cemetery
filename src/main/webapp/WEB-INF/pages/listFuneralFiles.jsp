<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
						<th rowspan="2">#</th>
						<th rowspan="2">Nume</th>
						<th rowspan="2">Prenume</th>
						<th rowspan="2">Religie</th>
						<th rowspan="2">Data mortii</th>
						<th rowspan="2">Data inmormantarii</th>
						<th colspan="4">Mormant</th>
					</tr>
					<tr>
						<th>Numar</th>
						<th>Parcela</th>
						<th>Cimitir</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach var="funeralFile" items="${funeralFiles}"
					varStatus="lineInfo">
					<tr>
						<td>${lineInfo.count}</td>
						<td>${funeralFile.dead.lastName}</td>
						<td>${funeralFile.dead.firstName}</td>
						<td>${funeralFile.dead.religion}</td>
						<td><fmt:formatDate value="${funeralFile.dead.deathDate}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${funeralFile.funeralDate}" pattern="yyyy-MM-dd HH:MM:SS" /></td>
						<td>${funeralFile.grave.nrGrave}</td>
						<td>${funeralFile.grave.plot.name}</td>
						<td>${funeralFile.grave.plot.cemetery.name}</td>
						<td><a href="${CONTEXT_PATH}/funeral/edit/${funeralFile.id}"><button>Modifica</button></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>