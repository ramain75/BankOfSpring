<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Hello</title>
	</head>
	<body>
		<h2>Home</h2>
		<p>Actions:
			<ul>
				<li><a href="<c:url value="/customer"/>">View Customers</a></li>
				<li><a href="<c:url value="/selectUser"/>">Select User</a></li>
			</ul>
		</p>
	</body>
</html>