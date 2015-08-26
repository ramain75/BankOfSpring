<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Show Customers</title>
	</head>
	<body>
		<h2>Customer List</h2>
		<p>
			<c:if test="${not empty customers}">
			Here are a list of the customers associated with this login:
				<ul>
					<c:forEach items="${customers}" var="customer">
						<c:url value="/customer/${customer.id}" var="url"/>
						<li><a href="${url}">${customer.name}</a></li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${empty customers}">
				There are no customers associated with this login.
			</c:if>
		</p>
		<p>
			<a href="<c:url value="/home"/>">Back</a>
		</p>
	</body>
</html>