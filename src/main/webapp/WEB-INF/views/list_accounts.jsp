<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Accounts</title>
    </head>
    <body>
        <h1>List Accounts</h1>
        <ol>
          <c:forEach var="account" items="${accounts}">
            <li>
              ${account.accountNumber} ${account.accountDescription}
            </li>
          </c:forEach>
        </ol>
    </body>
</html>
