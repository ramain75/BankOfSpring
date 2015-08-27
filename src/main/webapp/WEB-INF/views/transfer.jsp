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
			<c:url value="/transfer" var="url"/>
			<form:form modelAttribute="transferForm" action="${url}" method="POST">
				<div class="input">
					<form:label path="fromAccount">From account:</form:label>
					<form:select path="fromAccount">
						<form:option value="" label="-- Select from account --"></form:option>
						<form:options items="${fromAccounts}" itemLabel="accountNumber" itemValue="accountNumber" />
					</form:select>
					<form:errors path="fromAccount" cssClass="error"/>
				</div>
				<div class="input">
					<form:label path="toAccount">To account:</form:label>
					<form:select path="toAccount">
						<form:option value="" label="-- Select to account --"></form:option>
						<form:options items="${toAccounts}" itemLabel="accountNumber" itemValue="accountNumber" />
					</form:select>
					<form:errors path="toAccount" cssClass="error"/>
				</div>
				<div class="input">
					<form:label path="amount">Amount:</form:label>
					<form:input path="amount"/>
					<form:errors path="amount" cssClass="error"/>
				</div>
				<input type="submit" value="Transfer"/>
			</form:form>
		</p>
	</body>
</html>