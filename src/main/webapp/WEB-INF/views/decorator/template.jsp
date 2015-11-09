<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:useBean id="footer_date" class="java.util.Date" />
<html>
	<head>
		<title><sitemesh:write property="title" default="Bank Of Spring"/></title>
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/favicon.ico"/>" />
		<sitemesh:write property='head'/>
	</head>
	<body>
		<div class="header">
        	<h1>Bank Of Spring</h1>
        	<p>
        	<a href="<c:url value="/home"/>">Home</a> | 
        	<sec:authorize access="isAuthenticated()">
        		Logged in as <sec:authentication property="principal.username"/> (<a href="<c:url value="/logout"/>">logout</a>)
        	</sec:authorize>
        	<sec:authorize access="isAnonymous()">
        		Not logged in yet.
        	</sec:authorize>
        	</p>
        	<p></p>
        	<c:if test="${sessionScope.infoMessage}">
        		<p style="color: blue; border: 1px solid blue;">${sessionScope.infoMessage}</p>
        		<c:set scope="session" var="infoMessage" value="${null}"/>
        	</c:if>
	    </div>
	    <hr/>
		<div class="container">
        	<sitemesh:write property='body'/>
	    </div>
	    <hr/>
	    <div class="footer">
	    	&copy; Bank Of Spring <fmt:formatDate value="${footer_date}" pattern="yyyy" />
	    </div>
	</body>
</html>