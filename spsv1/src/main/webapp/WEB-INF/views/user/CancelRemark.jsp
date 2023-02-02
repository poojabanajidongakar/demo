<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MIS SheetKraft</title>
<spring:url value="/css/bootstrap.min.css" var="bootstrapcssUrl" />
<link rel="stylesheet" href="${ bootstrapcssUrl }">
<spring:url value="../js/jquery.min.js" var="jqueryjsUrl" />
<script src="${ jqueryjsUrl }"></script>
<spring:url value="../js/bootstrap.min.js" var="bootstrapjsUrl" />
<script src="${ bootstrapjsUrl }"></script>
<spring:url value="../js/validation.js" var="validation" />
<script src="${ validation }"></script>
<link href="/spsv1/css/style4.css" rel='stylesheet' type='text/css' />
<spring:url value="../css/font-awesome.css" var="fontawesomecssUrl" />
<link rel="stylesheet" href="${ fontawesomecssUrl }">
<spring:url value="../css/elements.css" var="elementscssUrl" />
<link rel="stylesheet" href="${ elementscssUrl }">
<spring:url value="../js/cancelremarkchanges.js" var="myjs" />
<script src="${ myjs }"></script>
<spring:url value="/css/jquery-confirm.min.css" var="jqueryconfirmcss" />
<link rel="stylesheet" href="${ jqueryconfirmcss }">
<spring:url value="/js/jquery-confirm.min.js" var="jqueryconfirmjs" />
<script src="${ jqueryconfirmjs }"></script>
  <link rel="stylesheet" href="../css/style.css">
     <spring:url value="/js/todolistvalidation.js" var="todolistvalidationjs" />
  <script src="${ todolistvalidationjs }"></script>
</head>
<body id="mainbody">
<c:set var = "user" scope = "session" value = "${ user }"/>
<c:if test="${user.role =='TL' }">
<jsp:include page="tlheader.jsp"></jsp:include>
</c:if>
<c:if test="${user.role !='TL' }">
<jsp:include page="userheader.jsp"></jsp:include>
</c:if>
<div class="logictbl">
	<h2>Cancel Remarks Data</h2>
	
	<button id="popup" onclick="div_show()">Add</button>
	
	<table class="table table-responsive logictbldata">
		<thead>
			<tr>
				<th>Subtype</th>
				<th>Remarks</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="list" items="${cancelremarkdata }">
				<tr>
					<td>${list.getSubtype() }</td>
					<td>${list.getRemarks() }</td>
					<td><a class="edit" id="${list.getSubtype() };${list.getRemarks() };${list.getId() }" href="#"><i class="fa fa-edit"></i> Edit</a></td>
					<td><a class="delete" href="#" id="${list.getId() }"><i class="fa fa-trash-o"></i> Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>


<div id="abc">
	<!-- Popup Div Starts Here -->
		<div id="popupContact">
		<!-- Contact Us Form -->
			<form action="#" id="form" method="post" name="form">
				<img id="close" src="../images/3.png" onclick ="div_hide()">
				<h2>Add Cancel Remark Data</h2>
				<hr>
				<input class="name" name="subtype" id="subtype" placeholder="Enter Subtype" type="text">
				<input class="name" name="owner" id="remark" placeholder="Enter Remark" type="text">
				<a href="" id="submit">Save</a>
			</form>
		</div>
	<!-- Popup Div Ends Here -->
</div>

<div id="abc1">
	<!-- Popup Div Starts Here -->
		<div id="popupContact">
		<!-- Contact Us Form -->
			<form>
				<img id="close" src="../images/3.png" onclick ="div_hide()">
				<h2>Update Cancel Remark Data</h2>
				<hr>
				<input type="hidden" id="id1" />
				<input class="name" name="subtype" id="subtype1" value="" type="text">
				<input class="name" name="owner" id="remark1" value="" type="text">
				<a href="" id="submit" class="editdata">Edit</a>
			</form>
		</div>
	<!-- Popup Div Ends Here -->
</div>

</body>
</html>