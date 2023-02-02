<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>spsv1</title>
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

    <link rel="stylesheet" href="../css/jquery-ui.min.css">
   <script src="../js/jquery-ui.min.js"></script>
   
      <spring:url value="/js/todolistvalidation.js" var="todolistvalidationjs" />
  <script src="${ todolistvalidationjs }"></script>
</head>
<body>
<c:set var = "user" scope = "session" value = "${ user }"/>
<c:if test="${user.role =='TL' }">
<jsp:include page="tlheader.jsp"></jsp:include>
</c:if>
<c:if test="${user.role !='TL' }">
<jsp:include page="userheader.jsp"></jsp:include>
</c:if>
<%-- <h2>allocate Task:${showreport}</h2> --%>
<center>
	<div class="report">
		<form action="/spsv1/report/show" method="post">
			Start Date : <input type="text" class="datepicker" name="sdate" autocomplete="off">
			End Date : <input type="text" class="datepicker" name="edate" autocomplete="off">
			Username :
			<select class="emp_choice1" name="employee">
										<option value="">---Select---</option>
										<option value="all">All</option>
											<c:forEach var="username" items="${ulist }">
									            <option value="${username.getUsername() }">${username.getUsername() }</option>
								            </c:forEach>
								         </select>
			<br></br>
			<input class="btn btn-success expman"  type="submit" name="exportExcel" value="Submit">
			<c:set var="error" value="${error }"></c:set>
			<h2 style="color:red" id="error">${error}</h2>
		</form>
		
		<table class="table table-responsive table bordered report_tbl">
			<tr><th>Name</th>
				<th>No. of Allocated Task</th>
				<th>No. of Pending Task</th>
				<th>No. of Complete Task</th>
			</tr>
			
			<c:forEach var="list" items="${report }">
			<tr>
				<td>${list.username}</td>
				<td>${list.allocate }</td>
				<td>${list.pending }</td>
				<td>${list.complete }</td>
				</tr>
			</c:forEach>
				
			
		</table>
	</div>
</center>
</body>
</html>