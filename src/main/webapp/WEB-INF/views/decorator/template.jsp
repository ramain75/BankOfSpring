<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:useBean id="footer_date" class="java.util.Date" />
<html>
	<head>
		<title><sitemesh:write property="title" default="Bank Of Spring"/></title>
		<sitemesh:write property='head'/>
	</head>
	<body>
		<div class="header">
        	<h1>Bank Of Spring</h1>
        	<c:if test="${not empty sessionScope.bos_current_user}">
        		Logged in as <c:out value="${bos_current_user}"/>
        	</c:if>
        	<c:if test="${empty sessionScope.bos_current_user}">
        		Not logged in yet.
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