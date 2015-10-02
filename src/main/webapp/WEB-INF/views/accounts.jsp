<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<div class="container">
<h1>Bank of Spring Accounts</h1> 

	<div class="panel panel-default">
	  <!-- Default panel contents -->
	 <!-- Table -->
	 <table class="table table-striped">
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
	   