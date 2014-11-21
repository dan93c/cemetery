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
			<table border="1">
				<thead>
					<tr>
						<th rowspan="2">#</th>
						<th rowspan="2">Numar</th>
						<th rowspan="2">Data creare</th>
						<th rowspan="2">CNP</th>
						<th rowspan="2">Nume</th>
						<th rowspan="2">Prenume</th>
						<th rowspan="2">Adresa</th>
						<th rowspan="2">E-mail</th>
						<th colspan="3">Mormant</th>
					</tr>
					<tr>
						<th>Numar</th>
						<th>Parcela</th>
						<th>Cimitir</th>
					</tr>
				</thead>
				<c:forEach var="contract" items="${contracts}" varStatus="lineInfo">
					<tr>
						<td>${lineInfo.count}</td>
						<td>${contract.receiptNr}</td>
						<td>${contract.releaseDate}</td>
						<td>${contract.cnp}</td>
						<td>${contract.lastName}</td>
						<td>${contract.firstName}</td>
						<td>${contract.address}</td>
						<td>${contract.emailAddress}</td>
						<td>${contract.grave.nrGrave}</td>
						<td>${contract.grave.plot.name}</td>
						<td>${contract.grave.plot.cemetery.name}</td>
						<td><a href="${CONTEXT_PATH}/contract/edit/${contract.id}"><button>Modifica</button></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>