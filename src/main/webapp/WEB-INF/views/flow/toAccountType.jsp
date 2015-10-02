<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
	<head>
		<title>Select Account Type</title>
	</head>
	<body>
		<h2>Select Account Type</h2>
		<p>Do you want to transfer money to one of your accounts or another person's account</p>
		<p>
			<form:form>
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
				<input type="submit" name="_eventId_cancel" value="Cancel" />
				<input type="submit" name="_eventId_myAccount" value="My Account" />
				<input type="submit" name="_eventId_anotherAccount" value="Another Account" />
			</form:form>
		</p>
	</body>
</html>