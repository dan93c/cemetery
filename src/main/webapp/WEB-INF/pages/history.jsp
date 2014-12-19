<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="sources.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Istoric</title>
</head>
<body>
	<div id="container">
		<%@include file="menu.jsp"%>
		<!-- div containing the search and filter elements -->
		<div id="search_filter_container">
			<table>
				<tr>
					<td>
						<div id="filter_container">
							<form method="get"
								action="${CONTEXT_PATH}/history/${currentPage}?action=${action}&sch=${sch}"
								name="selectActionForm">
								<label id="filter_label"> <select name="action"
									class="input_box" id="filter_criteria"
									onchange="this.form.submit();">
										<c:choose>
											<c:when test="${action eq '0'}">
												<option value="0" selected="selected">Toate
													actiunile</option>
											</c:when>
											<c:otherwise>
												<option value="0">Toate actiunile</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${action eq 'Adaugare'}">
												<option value="Adaugare" selected="selected">Adaugare</option>
											</c:when>
											<c:otherwise>
												<option value="Adaugare">Adaugare</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${action eq 'Modificare'}">
												<option value="Modificare" selected="selected">Modificare</option>
											</c:when>
											<c:otherwise>
												<option value="Modificare">Modificare</option>
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${action eq 'Stergere'}">
												<option value="Stergere" selected="selected">Stergere</option>
											</c:when>
											<c:otherwise>
												<option value="Stergere">Stergere</option>
											</c:otherwise>
										</c:choose>
								</select>
								</label>
							</form>
					</td>
					<div id="search_container">
						<form method="get"
							action="${CONTEXT_PATH}/history/${currentPage}?action=${action}&sch=${sch}"
							name="searchForm">
							<input class="input_box" type="text" name="sch" value="${sch}"/> <input
								type="submit" value="Cauta" class="btn">
						</form>
					</div>
				</tr>
			</table>
		</div>
		<div id="center">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>Utilizator</th>
						<th>Actiune</th>
						<th>Data</th>
						<th>Obiect</th>
						<th>Detalii</th>
					</tr>
				</thead>
				<c:forEach var="object" items="${history}" varStatus="lineInfo">
					<tr>
						<td><c:out
								value="${lineInfo.count +((currentPage-1)*selectNrOfRecords)}" /></td>
						<td><c:out value="${object.user.username}" /></td>
						<td><c:out value="${object.actionName}" /></td>
						<td><c:out value="${object.modificationDate}" /></td>
						<td><c:out value="${object.modifiedObject}" /></td>
						<td><c:out value="${object.details}" /></td>
					</tr>
				</c:forEach>
			</table>
			<center>
				<ul id="pagin">
					<%--For displaying First link --%>
					<c:choose>
						<c:when test="${currentPage != 1}">
							<li title="First page"><a
								href="${CONTEXT_PATH}/history/1?action=${action}&sch=${sch}">
									<< </a></li>
							<li title="Previous page"><a
								href="${CONTEXT_PATH}/history/${currentPage - 1}?action=${action}&sch=${sch}">Previous</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled" title="First page"><a
								href="${CONTEXT_PATH}/history/1?action=${action}&sch=${sch}">
									<< </a></li>
							<li class="disabled" title="Previous page"><a
								href="${CONTEXT_PATH}/history/1?action=${action}&sch=${sch}">Previous</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach begin="${begin}" end="${end}" var="i">
						<c:choose>
							<c:when test="${currentPage eq i}">
								<li title="Current page"><a class="current">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li title="Page ${i}"><a
									href="${CONTEXT_PATH}/history/${i}?action=${action}&sch=${sch}">${i}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<%--For displaying Next link --%>
					<c:choose>
						<c:when test="${currentPage lt nrOfPages}">
							<li title="Next page"><a
								href="${CONTEXT_PATH}/history/${currentPage + 1}?action=${action}&sch=${sch}">Next</a></li>
							<%--For displaying Last link --%>
							<li title="Last page"><a
								href="${CONTEXT_PATH}/history/${nrOfPages}?action=${action}&sch=${sch}">
									>> </a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled" title="Next page"><a
								href="${CONTEXT_PATH}/history/${nrOfPages}?action=${action}&sch=${sch}">Next</a></li>
							<%--For displaying Last link --%>
							<li class="disabled" title="Last page"><a
								href="${CONTEXT_PATH}/history/${nrOfPages}?action=${action}&sch=${sch}">
									>> </a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</center>
		</div>
</body>
</html>