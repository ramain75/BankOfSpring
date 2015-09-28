<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Transfer Flow</title>
	</head>
	<body>
		<h2>Transfer Flow</h2>
		<p>Welcome, this flow will allow you to transfer a balance to another account.</p>
		<p>
			<form:form>
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
				<input type="submit" name="_eventId_cancel" value="Cancel" />
				<input type="submit" name="_eventId_continue" value="Continue" />
			</form:form>
		</p>
	</body>
</html>