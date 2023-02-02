<%@ page import="com.prodata.spsv1.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html onContextmenu="return false">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SPS</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<%--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>
  <link rel="stylesheet" href="/spsv1/css/style.css">
  
  <spring:url value="/js/validation.js" var="validationjs" />
<script src="${ validationjs }"></script>

  <spring:url value="/js/paymentModeValidation.js" var="paymentModeValidation" />
<script src="${ paymentModeValidation }"></script> --%>

<spring:url value="/css/bootstrap.min.css" var="bootstrapcssUrl" />
<link rel="stylesheet" href="${ bootstrapcssUrl }">

<spring:url value="../js/jquery.min.js" var="jqueryjsUrl" />
<script src="${ jqueryjsUrl }"></script>

<spring:url value="../js/bootstrap.min.js" var="bootstrapjsUrl" />
<script src="${ bootstrapjsUrl }"></script>

 <spring:url value="/css/font-awesome.min.css" var="fontawesome" />
<link rel="stylesheet" href="${ fontawesome }">

 <spring:url value="/css/jquery-confirm.min.css" var="jqueryconfirmcss" />
<link rel="stylesheet" href="${ jqueryconfirmcss }">

 <spring:url value="/js/jquery-confirm.min.js" var="jqueryconfirmjs" />
<script src="${ jqueryconfirmjs }"></script>

  <link rel="stylesheet" href="../css/style.css">
  
  <spring:url value="/js/validation.js" var="validationjs" />
<script src="${ validationjs }"></script>

  <spring:url value="/js/paymentModeValidation.js" var="paymentModeValidation" />
<script src="${ paymentModeValidation }"></script>

   <spring:url value="/js/todolistvalidation.js" var="todolistvalidationjs" />
  <script src="${ todolistvalidationjs }"></script>
</head>
<body>

<body id="mainbody">
	<c:set var="user" scope="session" value="${ user }" />
	<c:if test="${user.role =='TL' }">
		<jsp:include page="tlheader.jsp"></jsp:include>
	</c:if>
	<c:if test="${user.role !='TL' }">
		<jsp:include page="userheader.jsp"></jsp:include>
	</c:if>

<center>
<div class="exportExcel">
 
	<h2>Status</h2>
	
	
<%-- 	<spring:url value="/report/MISReport" var="samplingReportUrl"/>
	<form action="${OneRsReportUrl}" method="post" class="exoprtupreport">
		<input class="btn btn-primary expman" type="submit" name="submit" value="Display Status">
		<!-- <input class="btn btn-primary expman" type="submit" name="submit" value="Export User Productivity Report">	  -->
	</form> --%>
	
	<c:if test="${DisplayStatus !=null}">
	<c:set var="list" value="${DisplayStatus}"></c:set>
	<table class="table table-responsive table-bordered status_tbl">
			<tr>
				<th rowspan=2></th>
				<th colspan=2 >Ok To process</th>
				<th colspan=2> Reverse</th>
				
			</tr>
			
			<tr>
				<th>Count</th>
				<th>Amount</th>
				<th>Count</th>
				<th>Amount</th>
			</tr>
			<tr>
				<th>Need to check</th>
				<c:choose>
						<c:when test="${list.needcheckok > 0 }"> <!-- need to check OTP count -->
							<td>${list.needcheckok }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
				</c:choose>
		
				<c:choose>
						<c:when test="${list.okntc > 0 }"> <!-- need to check  OTP amount -->
							<td>${list.okntc }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
				</c:choose>
		
				<c:choose>
						<c:when test="${list.needCheckreverse > 0 }"> <!-- need to check  reverse count -->
							<td>${list.needCheckreverse }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
				</c:choose>
				
				<c:choose>
						<c:when test="${list.revntc > 0 }">  <!-- need to check  reverse amount -->
							<td>${list.revntc }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
				</c:choose>
				
				
				
			</tr>
			
			
						<tr>
				<th>Partial Ok</th>
				<c:choose>
						<c:when test="${list.partialOk > 0 }"> <!-- need to check OTP count -->
							<td>${list.partialOk }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
				</c:choose>
		
				<c:choose>
						<c:when test="${list.parok > 0 }"> <!-- need to check  OTP amount -->
							<td>${list.parok }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
				</c:choose>
		
				<c:choose>
						<c:when test="${list.partialReverse > 0 }"> <!-- need to check  reverse count -->
							<td>${list.partialReverse }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
				</c:choose>
				
				<c:choose>
						<c:when test="${list.parrev > 0 }">  <!-- need to check  reverse amount -->
							<td>${list.parrev }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
				</c:choose>
				
				
				
			</tr>

			
			
	</table>
	</c:if>
	
		<%-- <table class="table table-responsive table bordered status_tbl">
			<tr>
				<th>Date</th>
				<th>Ok To Process</th>
				<th>Need To Check</th>
				<th>All Others</th>
				
				
			</tr>
			
			
			

		<c:forEach var="list" items="${DisplayOneRsStatus }" varStatus="loop">
				<tr>
					<td>${sdate} to ${edate }</td>
				
					<c:choose>
						<c:when test="${list.okToProcess > 0 }">
							<td>${list.okToProcess }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${list.needToCheck > 0 }">
							<td>${list.needToCheck }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${list.allOthers > 0 }">
							<td>${list.allOthers }</td>
						</c:when>
						<c:otherwise>
							<td>-</td>
						</c:otherwise>
					</c:choose>
					
					<c:set var="totalalct" value="${list.supwdone + list.crdone + list.fldone + list.samplingdone
					+ list.annuitydone + list.loandone + list.sbamdone + list.srudone + list.unclaimdone}"/>
					<td>${totalalct }</td>
				</tr>
			</c:forEach>	 --%>
			
		
	
</div>
</center>


</body>
</html>