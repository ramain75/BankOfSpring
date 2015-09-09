<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>  
<br/><br/>
<h1>Transfer Money</h1> 
<p>Use the form below to transfer money between accounts:</p>
<div class="panel panel-default"> 
	<div class="panel panel-default">
	  <div class="panel-heading">Transfer</div>
	  <div class="panel-body">
		
	
			<div class="alert alert-danger" role="alert">Error!!!</div>

		<sf:form method="POST" action="transfer" >
			<b>From: </b><sf:select cssClass="selectpicker" path="fromNumber" items="${accounts}"></sf:select>
			<b>To: </b><sf:select cssClass="selectpicker" path="toNumber" items="${accounts}"></sf:select>
			<b>Amount:</b> <sf:input path="amount"/> 
			<input name="submit" type="submit" value="Transfer" class="btn btn-primary" style="margin-left:10px;"/>
		</sf:form>
		
	  </div>
	</div>
</div>	        
	   