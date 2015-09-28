<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Select Amount</title>
</head>
<body>
	<h2>Select Amount</h2>
	<p>Select the amount of money to transfer:</p>
	<form:form modelAttribute="transferForm">
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
		<div class="input">
			<form:label path="amount">Amount:</form:label>
			<form:input path="amount" />
			<form:errors path="amount" cssClass="error" />
		</div>
		<input type="submit" name="_eventId_cancel" value="Cancel" />
		<input type="submit" name="_eventId_continue" value="Continue" />
	</form:form>
</body>
</html>