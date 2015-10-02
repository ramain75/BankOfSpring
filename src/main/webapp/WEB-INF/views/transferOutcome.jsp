<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Transfer outcome</title>
	</head>
	<body>
		<h2>Transfer outcome</h2>
		<p>
			The outcome of the transfer was:
			<c:if test="${outcome}">
				SUCCESS
			</c:if>
			<c:if test="${not outcome}">
				FAILURE
			</c:if>
		</p>
	</body>
</html>