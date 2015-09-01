<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>View accounts</title>
		<link rel="stylesheet" href="/resources/bosp.css"/>
	</head>
	<body>
		<div id="logo">Bank of Spring</div>
		<h1>View accounts</h1>
		
		<c:choose>
			<c:when test="${empty accounts}">
				<p>No accounts found!</p>
			</c:when>
			<c:otherwise>
				<table class="grid">
					<thead>
						<tr>
							<th>Account number</th>
							<th>Account description</th>
							<th>Current balance</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="act" items="${accounts}">
							<tr>
								<td><c:out value="${act.accountNumber}"/></td>
								<td><c:out value="${act.accountDescription}"/></td>
								<td><c:out value="${act.accountBalance}"/></td>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
	</body>
</html>