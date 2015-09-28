<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Select To Account</title>
</head>
<body>
	<h2>Select To Account</h2>
	<p>Select which account you will transfer money to:</p>
	<form:form modelAttribute="transferForm">
		<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
		<div class="input">
			<form:label path="toAccount">From account:</form:label>
			<form:select path="toAccount">
				<form:option value="" label="-- Select from account --"></form:option>
				<form:options items="${toAccounts}" itemLabel="accountNumber" itemValue="accountNumber" />
			</form:select>
			<form:errors path="toAccount" cssClass="error" />
		</div>
		<input type="submit" name="_eventId_cancel" value="Cancel" />
		<input type="submit" name="_eventId_continue" value="Continue" />
	</form:form>
</body>
</html>