<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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

			<h2>Sampling Export To Excel</h2>

			<form action="/spsv1/cheque/exportToExcel" method="post">
				Start Date : <input type="text" class="datepicker" name="sdate"
					autocomplete="off"> End Date : <input type="text"
					class="datepicker" name="edate" autocomplete="off"><br></br>
				<input class="btn btn-primary expman" type="submit"
					name="exportExcel" value="Export Mandate">
				<!--  <input class="btn btn-primary exdisc" type="submit" name="exportExcel" value="Export PresamplingMandate"> -->
			</form>

			<h3 class="successmsg">${downloadsuccess }</h3>
			<h3 class="errorsmsg">${dlerrormsg }</h3>
		</div>
	</center>
</body>
</html>