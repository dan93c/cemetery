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
	<table border="1">
		<thead>
			<tr>
				<th>#</th>
				<th>Cod</th>
				<th>Nume</th>
				<th>Adresa</th>
				<th></th>
			</tr>
		</thead>
		<c:forEach var="cemetery" items="${cemeteries}" varStatus="lineInfo">
			<tr>
				<td>${lineInfo.count}</td>
				<td>${cemetery.code}</td>
				<td>${cemetery.name}</td>
				<td>${cemetery.address}</td>
				<td><a href="${CONTEXT_PATH}/cemetery/edit/${cemetery.code}"><button>Modifica</button></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="${CONTEXT_PATH}/cemetery/add"><button>Adauga
			cimitir</button></a>
</body>
</html>