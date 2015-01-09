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
		<!-- div containing the search and filter elements -->
		<div id="search_filter_container">
			<div id="filter_container">
				<label id="filter_label"> <select class="input_box"
					id="filter_criteria">
						<option selected>Select Box</option>
						<option>Short Option</option>
						<option>This Is A Longer Option</option>
				</select>
				</label> <input class="input_box" type="text" name="filter_word"
					placeholder="Enter key word ..." /> &nbsp; <a href="#"
					class="link_button">Filter</a>
			</div>
			<div id="search_container">
				<input class="input_box" type="text" name="search"
					placeholder="What are you looking for?" /> &nbsp; <a href="#"
					class="link_button">Search</a>
			</div>
		</div>

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
						<th colspan="2">Cimitir</th>
					</tr>
				</thead>
				<c:forEach var="dead" items="${deads}" varStatus="lineInfo">
					<tr>
						<td>${lineInfo.count}</td>
						<td>${dead.lastName}</td>
						<td>${dead.firstName}</td>
						<td>${dead.religion}</td>
						<td><fmt:formatDate value="${dead.deathDate}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${dead.funeralDate}"
								pattern="yyyy-MM-dd HH:mm " /></td>
						<td>${dead.grave.nrGrave}</td>
						<td>${dead.grave.plot.name}</td>
						<td>${dead.grave.plot.cemetery.name}<a
							href="${CONTEXT_PATH}/dead/edit/${dead.id}"><button
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
								href="${CONTEXT_PATH}/history/1?action=${action}&sch=${sch}">
									First </a></li>
							<li title="Previous page"><a
								href="${CONTEXT_PATH}/history/${currentPage - 1}?action=${action}&sch=${sch}">Previous</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled" title="First page"><a
								href="${CONTEXT_PATH}/history/1?action=${action}&sch=${sch}">
									First </a></li>
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

					<c:choose>
						<c:when test="${currentPage lt nrOfPages}">
							<li title="Next page"><a
								href="${CONTEXT_PATH}/history/${currentPage + 1}?action=${action}&sch=${sch}">Next</a></li>

							<li title="Last page"><a
								href="${CONTEXT_PATH}/history/${nrOfPages}?action=${action}&sch=${sch}">
									Last </a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled" title="Next page"><a
								href="${CONTEXT_PATH}/history/${nrOfPages}?action=${action}&sch=${sch}">Next</a></li>

							<li class="disabled" title="Last page"><a
								href="${CONTEXT_PATH}/history/${nrOfPages}?action=${action}&sch=${sch}">
									Last </a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
	</div>
	
	
</body>
</html>