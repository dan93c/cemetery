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
						<th>#</th>
						<th>Nr</th>
						<th>Observatii</th>
						<th>Tip</th>
						<th>Suprafata</th>
						<th>Fotografia</th>
						<th>Parcela</th>
						<th>Cimitir</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach var="grave" items="${graves}"
					varStatus="lineInfo">
					<tr>
						<td>${lineInfo.count}</td>
						<td>${grave.nrGrave}</td>
						<td>${grave.observations}</td>
						<td>${grave.type}</td>
						<td>${grave.surface}</td>
						<td>${grave.photoScanned}</td>
						<td>${grave.plot.name}</td>
						<td>${grave.plot.cemetery.name}</td>
						<td><a href="${CONTEXT_PATH}/grave/edit/${grave.id}"><button>Modifica</button></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>