<%@page import="com.prodata.spsv1.model.*"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>spsv1AM</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="/spsv1/css/style.css">
<spring:url value="/js/validation.js" var="validationjs1" />
 <spring:url value="/js/demoscript.js" var="validationjs" />
 <spring:url value="/js/todolistvalidation.js" var="validationjs2" />
  <script src="${ validationjs2 }"></script>
 
<script src="${ validationjs }"></script>
<script src="${ validationjs1 }"></script>
</head>
<body id="mainbody">

<jsp:include page="header.jsp"></jsp:include>

 <form action="/spsv1/task/allocatedescTask" method="post" commandName="member">
				      <div class="panel-body" id="rstask">
	<div class="rightsidediv">
			<div class="spal">
				<div class="panel-group">
				    <div class="panel bd" >
				      <div class="panel-heading" id="refont">:: Reassign Task ::</div>
				      <div class="panel-body" id="rstask">
				     	<div class="row rtask">
				      			<div class="col-sm-6 col-md-6">
				      		<table class="sparc">
				      				
				      			  <tr>
				      				<td>WorkFlow : </td>
										<td> <select>
										   <option value="">---Select---</option>
								            <option value="">a</option>
								            <option value="">b</option>
								         </select>
								         </td>
				      				
				      			   <tr>
				      				<td> Employee : </td>
										<td> 
										<select class="emp_choice" name="employee" id="employee">
										<option value="">---Select---</option>
											<c:forEach var="username" items="${ulist }">
									            <option value="${username.getUsername() }">${username.getUsername() }</option>
								            </c:forEach>
								         </select>
								         <input type="text" name="transfer" id="transfer">
								         
								         </td>
				      		        </tr>
				      		        <tr>
				      		        <tr>
				      					<tr>
				      						<td>Policy No : </td>
				      						<td><input type="text"></td>
				      					</tr>
				      		
				      					<tr>
				      						<td>Current Balance : </td>
				      						<td><input type="text"></td>
				      					</tr>
				      					</table>
				      				</div>
				      		
				      			<div class="col-sm-6 col-md-6">
				      			<table class="sparc">
				      				<tr>
				      				<td> Process: </td>
										<td><center> <select>
										   <option value="">---Select---</option>
								            <option value="">a</option>
								            <option value="">b</option>
								            <option value="">c</option>
								         </select></center>
								         </td>
				      		        </tr>
				      		      <tr>
				      				<td> New Assignee : </td>
									<td>
										<select name="assignee" id="newassignee">
										 <option value="">---Select---</option>
											<c:forEach var="username" items="${ulist }">
									            <option value="${username.getUsername() }">${username.getUsername() }</option>
								            </c:forEach>
								         </select>
									</td>
				      		        </tr>
				      		        <tr>
				      			
				      			
				      			
				      			
				      					<tr>
				      						<td>TAT Status : </td>
				      						<td><input type="text"></td>
				      					</tr>
				      		
				      					<tr>
				      						<td>Received By : </td>
				      						<td><input type="text"></td>
				      					</tr>
				      					</table>
				      		</div>
				      	</div>
				      	<div class="row">
		                         <div>
		                            <center>
			                         <!-- <button type="button" class="btn btn-success">Search</button>
				      				 <button type="submit" class="btn btn-success">Update</button>	
				      				 <button type="reset" class="btn btn-success">Reset</button> -->
				      				  
                                <button type="submit" class="btn btn-success deletebtn" id="allocate">Allocate</button>
				      				 <!-- <button class="btn btn-success deletebtn"><a href="/spsv1/cheque/deleteData">Delete</a></button> -->	
				      				</center>
				      			</div>
				      	</div>	
				      	<hr>
				      	<div class="row">
		                         <div>
		                            <center>
			                         <button type="button" class="btn btn-success">Search</button>
				      				 <button type="button" class="btn btn-success">Update</button>	
				      				 <button type="button" class="btn btn-success">Reset</button>
				      				</center>
				      			</div>
				      	</div>	
				      	<hr>
				      	
			<div class="nidiv1">
                 <div class="panel-group">
                 

	<%-- 		<center><input type="text" id="myInput" placeholder="Search for names.." title="Type in a name"></center> --%>
				    <div class="panel panel-primary">
				      <div class="panel-heading" align="center">Reassign Task (${countdata })</div>
				   
				 <div class="panel-body">
				      		<div class="row mainrow">
				      		<!-- <form action="spoolExcelServlet" method="post"> -->
				      		<table class="table niya">
				               <thead>
				                
				      				<tr class="clr">
					      				<th>Select All<br/><input type="checkbox"></th>
					      				<th>WorkFlow</th>
					      				<th>Proccess</th>
					      				<th>Doc Key</th>
					      				<th></th>
				      				</tr>
				     </thead>
				     <tbody id="print">
				<c:forEach var="list" items="${ list }">
				<tr>
								
								<td><input type="checkbox"></td>
								 
							   <td></td>
				      		   <td></td>
				      		   <td> 
				      		    Customer Name: ${ list.getCustName() } <br>
				      		    Policy No:  <a href="/spsv1/cheque/displayDPolicyDetailDis?policyNo=${list.getPolicyno() }">${list.getPolicyno() }</a><br>
				      		    Client ID: ${list.getClientId() } <br> 
				      			
				      		   </td>
				      		   <td><%-- <a href="/spsv1/cheque/deleteToDoList?policyNo=${list.getPolicyno() }">Delete</a> --%></td>
				      		  
				      		 
				</tr>
				</c:forEach>
	   </tbody>
				      		
				     	</table>
				      			
				      			</div>
				      	 </div>
			
</div>
</div>
</div>
				      	
				      	
				     </div>
			    </div>
			</div>
		</div>
	</div>
				      		
 
 </div></div>
</div>
</form>
</body>
</html>