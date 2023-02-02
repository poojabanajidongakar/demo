<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SPSV </title>
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
	<h2>Search Pre-sampling Policy Details</h2>
		<form action="/spsv1/Prereport/searchuserdata" method="post">
			Policy No. <input type="text" name="policyno" id="policyno" autocomplete="off" />
			<input class="btn btn-success" id="searchpolicy" type="submit" value="Submit">
		</form>
		<h2 style="color:red" id="error">${error }</h2>
		
		<table class="table table-responsive table bordered report_tbl presamp_reporttbl">
			<thead>
			<tr>
				<th>Policy No.</th>
				<th>Maker Username</th>
				<th>Maker Remark</th>
				<th>Checker Username</th>
				<th>Checker Remark</th>
				<th>Date</th>
			</tr>
			</thead>
			<tbody id="print">
			
			<c:forEach var="list" items="${searchulist }">
				<tr>
					<td>${list.getPolicyno() }</td>
					<td>${list.getMakerName() }</td>
					<td>${list.getMakerRemark() }</td>
					<td>${list.getCheckerName() }</td>
					<td>${list.getCheckerRemark() }</td>
					<td>${list.date }</td>
				</tr>
			</c:forEach> 
			
			</tbody>
		</table>
	</div>
</center>
</body>
</html>