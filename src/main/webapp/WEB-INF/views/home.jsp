<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Home</title>
	</head>
	<body>
		<h2>Home</h2>
		<p>Actions:
			<ul>
				<li><a href="<c:url value="/me"/>">View Accounts</a></li>
				<li><a href="<c:url value="/transfer"/>">Transfer Cash</a></li>
				<li><a href="<c:url value="/transferFlow"/>">Transfer Cash (Flow)</a></li>
			</ul>
		</p>
	</body>
</html>