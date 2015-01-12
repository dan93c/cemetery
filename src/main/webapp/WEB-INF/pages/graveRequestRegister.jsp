<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="sources.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrul cu evidenta cererilor de atribuire a locurilor
	de inhumare</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<!-- div containing the search and filter elements -->
		<div id="search_filter_container">
			<div id="filter_container">
				<form method="get"
					action="${CONTEXT_PATH}/graveRequest/cereri/${currentPage}?order=${order}&sch=${sch}"
					name="selectOrderForm">
					<label id="order_label"> <select name="order"
						class="input_box" id="order_criteria"
						onchange="this.form.submit();">
							<c:choose>
								<c:when test="${order eq '0'}">
									<option value="0" selected="selected">Data
										inregistrarii - Asc</option>
								</c:when>
								<c:otherwise>
									<option value="0">Data inregistrarii - Asc</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${order eq '1'}">
									<option value="1" selected="selected">Data
										inregistrarii - Desc</option>
								</c:when>
								<c:otherwise>
									<option value="1">Data inregistrarii - Desc</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${order eq '2'}">
									<option value="2" selected="selected">Nr infocet</option>
								</c:when>
								<c:otherwise>
									<option value="2">Nr infocet</option>
								</c:otherwise>
							</c:choose>
					</select>
					</label>
				</form>
			</div>
			<div id="search_container">
				<form method="get"
					action="${CONTEXT_PATH}/graveRequest/cereri/${currentPage}?order=${order}&sch=${sch}"
					name="searchForm">
					<input class="input_box" type="text" name="sch" value="${sch}" />
					<input type="submit" value="Cauta" class="btn">
				</form>
			</div>
		</div>

		<div id="center">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Data inregistrarii</th>
						<th>Nr infocet</th>
						<th>Stadiu de solutionare</th>
					</tr>
				</thead>
				<c:forEach var="graveRequest" items="${graveRequests}"
					varStatus="lineInfo">
					<tr>
						<td>${lineInfo.count}</td>
						<td><fmt:formatDate value="${request.registrationDate}"
								pattern="yyyy-MM-dd HH:mm " /></td>
						<td>${graveRequest.nrInfocet}</td>
						<td>${graveRequest.solvingStage}</td>
						<td><a href="${CONTEXT_PATH}/graveRequest/edit/${graveRequest.id}"><button
									class="btn-table">Modifica</button></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>

		<div id="footer_container">
			<ul id="pagin">

				<c:choose>
					<c:when test="${currentPage != 1}">
						<li title="First page"><a
							href="${CONTEXT_PATH}/graveRequest/cereri/1?order=${order}&sch=${sch}">
								First </a></li>
						<li title="Previous page"><a
							href="${CONTEXT_PATH}/graveRequest/cereri/${currentPage - 1}?order=${order}&sch=${sch}">Previous</a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled" title="First page"><a
							href="${CONTEXT_PATH}/graveRequest/cereri/1?order=${order}&sch=${sch}">
								First </a></li>
						<li class="disabled" title="Previous page"><a
							href="${CONTEXT_PATH}/graveRequest/cereri/1?order=${order}&sch=${sch}">Previous</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="${begin}" end="${end}" var="i">
					<c:choose>
						<c:when test="${currentPage eq i}">
							<li title="Current page"><a class="current">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li title="Page ${i}"><a
								href="${CONTEXT_PATH}/graveRequest/cereri/${i}?order=${order}&sch=${sch}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:choose>
					<c:when test="${currentPage lt nrOfPages}">
						<li title="Next page"><a
							href="${CONTEXT_PATH}/graveRequest/cereri/${currentPage + 1}?order=${order}&sch=${sch}">Next</a></li>

						<li title="Last page"><a
							href="${CONTEXT_PATH}/graveRequest/cereri/${nrOfPages}?order=${order}&sch=${sch}">
								Last </a></li>
					</c:when>
					<c:otherwise>
						<li class="disabled" title="Next page"><a
							href="${CONTEXT_PATH}/graveRequest/cereri/${nrOfPages}?order=${order}&sch=${sch}">Next</a></li>

						<li class="disabled" title="Last page"><a
							href="${CONTEXT_PATH}/graveRequest/cereri/${nrOfPages}?order=${order}&sch=${sch}">
								Last </a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>

</body>
</html>