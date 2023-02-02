<%@page import="com.prodata.spsv1.model.*"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>SPS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
   <spring:url value="/css/bootstrap.min.css" var="bootstrapcssUrl" />
<link rel="stylesheet" href="${ bootstrapcssUrl }">

<spring:url value="../js/jquery.min.js" var="jqueryjsUrl" />
<script src="${ jqueryjsUrl }"></script>

<spring:url value="../js/validation.js" var="validationjs" />
<script src="${ validationjs }"></script>

<spring:url value="../js/bootstrap.min.js" var="bootstrapjsUrl" />
<script src="${ bootstrapjsUrl }"></script>

   <spring:url value="../js/dateValidation.js" var="validationjs1" />
  <script src="${ validationjs1 }"></script>
  
  <link rel="stylesheet" href="../css/style.css">
 
  <spring:url value="/css/font-awesome.min.css" var="fontawesome" />
<link rel="stylesheet" href="${ fontawesome }">
  
   <spring:url value="/js/todolistvalidation.js" var="todolistvalidationjs" />
  <script src="${ todolistvalidationjs }"></script>
  
</head>
<body id="mainbody">
<c:set var = "type" scope = "session" value = "${ type }"/>
<c:set var = "user" scope = "session" value = "${ user }"/>
<c:if test="${user.role =='TL' }">
<jsp:include page="tlheader1.jsp"></jsp:include>
</c:if>
<c:if test="${user.role !='TL' }">
<jsp:include page="userheader1.jsp"></jsp:include>
</c:if>


	<div class="rightsidediv">
			<div class="spal">
				<div class="panel-group">
				    <div class="panel bd" >
				    <c:if test="${user.role =='TL' }">
				      <div class="panel-heading" id="refont">:: Reassign Task :: </div>
				        <h3 class="successmsg">${deletesuccess }</h3>
				        <h3 class="successmsg">${downloadsuccess }</h3>
				        <h3 class="successmsg">${tastsuccess }</h3>
				      <form action="/spsv1/Pretask/allocateTask" method="post" commandName="member">
				      <div class="panel-body" id="rstask">
				      
				      	<div class="row rtask">
				      			<div class="col-sm-6 col-md-6">
				      		<table class="sparc">
				      			<tr>
				      				<td> Employee : </td>
										<td> 
										<select class="emp_choice" name="employee" id="employee">
										<option value="">---Select---</option>
											<c:forEach var="username" items="${ulist }">
									             <option value="${username.getUsername() }">${username.getUsername() }</option>
								            </c:forEach>
								         </select>
								         <input type="text" name="transfer" id="transfer" autocomplete="off" onkeypress="return IsNumeric(event)" placeholder="Enter number to allocate">
								         
								         </td>
				      		        </tr>
				      		        
				      					</table>
				      				</div>
				      		
				      			<div class="col-sm-6 col-md-6">
				      			<table class="sparc">
				      			
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
				      				<!-- 	<tr>
				      						<td>TAT Status : </td>
				      						<td><input type="text"></td>
				      					</tr>
				      		
				      					<tr>
				      						<td>Received By : </td>
				      						<td><input type="text"></td>
				      					</tr> -->
				      					</table>
				      		</div>
				      	</div>
				      	
				      	<hr>
				      	<div class="row">
		                         <div>
		                            <center>
			                         <!-- <button type="button" class="btn btn-success">Search</button>
				      				 <button type="submit" class="btn btn-success">Update</button>	
				      				 <button type="reset" class="btn btn-success">Reset</button> -->
				      				  <button type="submit" id="allocate" class="btn btn-success deletebtn">Allocate</button>
				      				 <!-- <button class="btn btn-success deletebtn"><a href="/spsv1/cheque/deleteData">Delete</a></button> -->	
				      				</center>
				      			</div>
				      	</div>	
				      	<hr>
			 
			 </c:if>
			<!-- <div class="clearfix"></div>   --> 	
			<input type="hidden" name="type" value="${ type }">
			<div class="nidiv1">
                 <div class="panel-group">
				    <div class="panel panel-primary">
				      <div class="panel-heading" align="center">Reassign Task (${countdata })</div>
				      <div class="panel-body">
				      		<div class="row mainrow">
				      		<!-- <form action="spoolExcelServlet" method="post"> -->
				      		<table class="table niya">
				               
				                
				      				<tr class="clr">
					      				<!-- <th>Select All<br/><input type="checkbox"></th> -->
					      				<th>Proccess</th>
					      				<!-- <th>workflow</th> -->
					      				<!-- <th>Doc Key</th> -->
					      				<!-- <th>Start Date</th>
					      				<th>EDC</th>
					      				<th>Employee</th> -->
				      				</tr>
				     
				<c:forEach var="list" items="${list }">
				<tr>
				
								<%-- <td><input type="checkbox" name="checkassigntask" value="${list.getPolicyno() }"></td> --%>
								 <%-- <td>${list.getSubtypeteam()}</td> --%>
								  
				      		  <!--  <td></td> -->
				      		   <td> 
				      		  
				      		   
				      		   <%-- <c:choose>
									<c:when test="${list.getFinalRemarkOners() !=null }"> --%>
										Policy No:  <a href="/spsv1/Precheque/displayPolicyDetails?policyNo=${list.getPolicyno() }&onersremark=${list.getFinalRemarkOners()}">${list.getPolicyno() }</a><br>
								<%-- 	</c:when>
									<c:otherwise> --%>
										<%-- Policy No:  <a href="/spsv1/Precheque/displayPolicyDetails?policyNo=${list.getPolicyno() }&onersremark=null">${list.getPolicyno() }</a><br> --%>
								<%-- 	</c:otherwise>
								</c:choose>
				      		    --%>
				      			<%-- Policy No:  <a href="/spsv1/cheque/displayPolicyDetails?policyNo=${list.getPolicyno() }&prnno=${list.getPrnNo() }&paymentMode=${list.getPaymentMode()}">${list.getPolicyno() }</a><br> --%>
				      			
				      		   Customer Name: ${list.getName() } <br>
				      			
				      		   </td>
				      		  <!--  <td></td>
				      		   <td></td>
				      		   <td></td> -->
				      		  
				</tr>
				</c:forEach>
	   
				      		
				     	</table>
				      			
				      	  </div>
				      	 </div>
			
						</div>
						</div>
						</div> 	
				     </div>
				     
				     </form>
				     
				     
				     
			    </div>
			</div>
		</div>


</body>
</html>