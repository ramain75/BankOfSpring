<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Account List</title>
	</head>
	<body>
		<h2>Account List</h2>
		<p>
			<c:if test="${not empty accounts}">
			Here are your accounts:
				<ul>
					<c:forEach items="${accounts}" var="account">
						<c:url value="/customer/${id}/${account.accountNumber}" var="url"/>
						<li><a href="${url}">${account.accountNumber}</a></li>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${empty accounts}">
				There are no accounts associated with this customer.
			</c:if>
		</p>
		<p>
			<a href="<c:url value="/customer"/>">Back</a>
		</p>
	</body>
</html>