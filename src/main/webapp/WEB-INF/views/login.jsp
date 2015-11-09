<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
</head>
<body onload="document.loginForm.username.focus();">

	<h1>Login Form</h1>

	<div>

		<h2>Login with Username and Password</h2>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name="loginForm"
		  action="<c:url value="/j_spring_security_check" />" method="POST">

		  <table>
			<tr>
				<td>User:</td>
				<td><input type="text" name="j_username" value=""></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="j_password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input name="submit" type="submit"
					value="submit" /></td>
			</tr>
		  </table>

		</form>
	</div>

</body>
</html>