<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>  
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
   <title>Bank of Spring</title>

   <!-- Bootstrap -->
   <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
   <link href="/resources/css/bootstrap-select.css" rel="stylesheet">
   
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
	     <a class="navbar-brand" href="/"><img src="/resources/img/boslogo.png"/></a>
	   </div>
	   <div id="navbar" class="collapse navbar-collapse">
	     <ul class="nav navbar-nav">
	       <li class="active"><a href="/accounts">Accounts</a></li>
	       <li><a href="/transfer">Transfer</a></li>
	       <li><a href="/about">About</a></li>
	     </ul>
	      <ul class="nav pull-right navbar-nav">
          <li class="dropdown" id="menuLogin">
            <a class="dropdown-toggle" href="#" data-toggle="dropdown" id="navLogin">Login</a>
            <div class="dropdown-menu pull-right ddLoginMenu" style="padding:17px;">
              <sf:form method="POST" action="loginForm" cssClass="ddLoginForm" >
              	<p>Login to the Bank of Spring</p>
                <input name="username" id="username" type="text" class="form-control" placeholder="Username"> 
                <input name="password" id="password" type="password" class="form-control" placeholder="Password"><br>
                <button type="button" id="btnLogin" class="btn btn-primary form-control">Login</button>
              </sf:form>
            </div>
          </li>
        </ul>
	   </div><!--/.nav-collapse -->
	  </div>
	</nav>

	<sitemesh:write property='body'/>
	<!-- Footer -->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="/resources/js/jquery-1.11.3.min.js"></script>
	<script src="/resources/js/bootstrap.min.js"></script>
	<script src="/resources/js/bootstrap-select.min.js"></script>
</body>
</html>
  