<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Bank Of Spring Login</title>
</head>
<body>
	<spring:url var="authUrl" value="/static/j_spring_security_check" />
	<div>
		<form method="post" class="signin" action="${authUrl}">
			<fieldset>
				<legend>Login</legend>
				<label for="username">User Name</label> <input id="username"
					name="j_username" type="text" /> <label for="password">Password</label>
				<input id="password" name="j_password" type="password" />
				<div class="submit">
					<button type="submit" name="save">Login</button>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>