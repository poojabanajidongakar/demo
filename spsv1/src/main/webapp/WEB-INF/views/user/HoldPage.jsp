<%@page import="java.util.List"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hold File Export</title>
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
<style>
.button {
	border: none;
	color: black;
	padding: 12px 30px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

.button1 {
	background-color: #4CAF50;
} /* Green */
.button2 {
	background-color: #008CBA;
} /* Blue */
</style>
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
		<div class="container">
			<h2 class="uploadtitle">HOLD FILE IMPORT EXPORT</h2>
			<div class="panel-body">
				<div class="row scriteria">
					<div class="col-sm-4 col-md-4">
						<center>
							<div class="upload_form_cont">
								<form id="upload_form" method="post"
									action="/spsv1/Hold/HoldFileProcess">
									<div>
										<button class="btn btn-primary" id="upld" type="submit"
											value="holdfilepro" name="holffile">Hold File Upload</button>
									</div>
								</form>
							</div>
							<br> <br>
							<form id="upload_form" method="post"
								action="/spsv1/Hold/uplFixedFile">
								<div>
									<button class="btn btn-primary" id="upld" type="submit"
										value="FixedFile" name="uplFixedFile">Upload Fixed
										file</button>
								</div>
							</form>
							<br> <br>
							<h2 class="successmsg">${uploadmsg }</h2>
							<h2 class="successmsg">${uploadmsg1 }</h2>
						</center>
					</div>
					<div class="col-sm-4 col-md-4">
						<center>
							<form action="/spsv1/Hold/holdfilequery" method="post">
								<button class="button button2" type="submit"
									value="exportRecondate" name="exc"
									style="line-height: inherit;">Excute</button>
							</form>
							<br> <br>
							<div>
								<a href="/spsv1/Hold/HoldFileExport"><button value="delete"
										id="deletefile" class="btn btn-success">Export Hold
										File</button></a> <img id="preview" />
							</div>
							<br> <br>
							<div>
								<a href="/spsv1/Hold/GlenysFileExport"><button
										value="delete" id="deletefile" class="btn btn-success">Export
										Glenys</button></a> <img id="preview" />
							</div>
							<h2 class="successmsg">${ExcuteAllFiles }</h2>
						</center>
					</div>
					<div class="col-sm-4 col-md-4">
						<center></center>
					</div>
				</div>
			</div>
		</div>
	</center>
</body>
</html>