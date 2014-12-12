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
		
		<!-- div containing the logout button and the user's name -->
		<div id="logout_header">
			<p id="logged_user">&nbsp;Logged User&nbsp;</p>
			<img id="logout_button" src="<%=request.getContextPath()%>/resources/img/logout3.png" alt="logout">
		</div>
		
		<!-- div containing the search and filter elements -->
		<div id="search_filter_container">
			<div id="filter_container">
				<label id="filter_label">
					<select class="input_box" id="filter_criteria">
						<option selected> Select Box </option>
						<option>Short Option</option>
						<option>This Is A Longer Option</option>
					</select>
				</label>
				<input class="input_box" type="text" name="filter_word" placeholder="Enter key word ..."/>
				&nbsp;
				<a href="#" class="link_button">Filter</a>
			</div>
			<div id="search_container">
				<input class="input_box" type="text" name="search" placeholder="What are you looking for?"/>
				&nbsp;
				<a href="#" class="link_button">Search</a>
			</div>
		</div>
		
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
						<th colspan="2">Cimitir</th>
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
						<td>${grave.plot.cemetery.name}
						<a href="${CONTEXT_PATH}/grave/edit/${grave.id}"><button class="btn-table">Modifica</button></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<!-- div that will be populated with pagination links -->
		<div id="footer_container">
			<div id="page_navigation">
			
			</div>
		</div>
	</div>
	
	<!-- the input fields that will hold the variables we will use in pagination script -->
	<input type="hidden" id="current_page" />
	<input type="hidden" id="show_per_page" />
	
</body>
</html>