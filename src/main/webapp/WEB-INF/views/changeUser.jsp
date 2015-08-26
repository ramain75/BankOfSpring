<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Select User</title>
	</head>
	<body>
		<h2>Change User</h2>
		<p>Select a user:
			<form action="<c:url value="/selectUser"/>" method="post">
				<select name="user">
					<c:forEach items="${users}" var="user">
						<option value="${user.username}">${user.username}</option>
					</c:forEach>
				</select>
				<input type="submit" value="Go"/>
			</form>
		</p>
		<p>
			<a href="<c:url value="/home"/>">Back</a>
		</p>
	</body>
</html>