<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="CONTEXT_PATH" value="<%=request.getContextPath()%>"
	scope="application"></c:set>
<c:if test="${empty username}">
	<c:set var="username" value="<%=request.getUserPrincipal().getName()%>"
		scope="application"></c:set>
</c:if>
<!-- doar cele ce vor fi folosite pt majoritatea paginilor -->
<link rel="stylesheet" type="text/css"
	href="${CONTEXT_PATH}/resources/css/menu.css">
<link rel="stylesheet" type="text/css"
	href="${CONTEXT_PATH}/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="${CONTEXT_PATH}/resources/css/logout_button.css">
<link rel="stylesheet" type="text/css"
	href="${CONTEXT_PATH}/resources/css/search_filter.css">
<script type="text/javascript"
	src="${CONTEXT_PATH}/resources/js/jquery.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

