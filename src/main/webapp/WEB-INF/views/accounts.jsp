<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Bank of Spring Style -->
    <link href="/resources/css/bankofspring.css" rel="stylesheet">

  </head>
  <body>
  	  <nav class="navbar navbar-inverse navbar-fixed-top">
	      <div class="container">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" href="#">Bank of Spring</a>
	        </div>
	        <div id="navbar" class="collapse navbar-collapse">
	          <ul class="nav navbar-nav">
	            <li class="active"><a href="#">Accounts</a></li>
	            <li><a href="#about">About</a></li>
	          </ul>
	        </div><!--/.nav-collapse -->
	      </div>
	    </nav>
	    
	   	<div class="container">
	   		<div class="bankofspring_body_container">
			    <h1>Bank of Spring Accounts</h1>
	        
				<div class="panel panel-default">
				  <!-- Default panel contents -->
				  <div class="panel-heading">Accounts</div>				
				  <!-- Table -->
				  <table class="table">
				    <tr>
				    	<th>Account Number</th>
				    	<th>Account Description</th>
				    	<th>Amount Available</th>
				    </tr>
				    <c:forEach items="${accounts}" var="account">
				    	<tr>
				    		<td>${account.accountNumber}</td>
				    		<td>${account.accountDescription}</td>
				    		<td>&pound;${account.accountBalance}</td>
				    	</tr>
			        </c:forEach>
				  </table>
				</div>	        
		        
		       
	        </div>
	    </div>
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/resources/js/jquery-1.11.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/resources/js/bootstrap.min.js"></script>
  </body>
</html>