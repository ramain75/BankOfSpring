<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Account Details</title>
	</head>
	<body>
		<h2>Account Details: ${account.accountNumber}</h2>
		<p>
			<dl>
				<dt>Account Number</dt>
				<dd>${account.accountNumber}</dd>
				<dt>Account Description</dt>
				<dd>${account.accountDescription}</dd>
				<dt>Account Balance</dt>
				<dd>${account.accountBalance}</dd>
			</dl>
		</p>
		<p>
			<a href="<c:url value="/me"/>">Back</a>
		</p>
	</body>
</html>