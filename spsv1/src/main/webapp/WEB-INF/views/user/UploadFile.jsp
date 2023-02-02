<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>spsv1</title>

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

 <spring:url value="../js/upload.js" var="validationjs1" />
<script src="${ validationjs1 }"></script>
   <link rel="stylesheet" href="/spsv1/css/main.css">
     <script src="/spsv1/js/script.js"></script>
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

        <div class="container">
		<center>
		<h2 class="uploadtitle">Upload Sampling Input File</h2><br><br>
            <div class="upload_form_cont">

               <%--  <form id="upload_form" enctype="multipart/form-data" method="post" action="/spsv1/cheque/insertExcel"> --%>
					<form id="upload_form"  method="post" action="/spsv1/cheque/excel">
					
					<div id="loading">
  						<p><img src="../images/loading.gif" />   uploading....</p>
					</div>
					 <div>
						<button class="btn btn-primary" id="upld" type="submit" value="Execute Upload">Execute Sampling Upload</button>

                    </div>

                </form>
                <!-- <a href="/spsv1/Precheque/upldsampling"><button class="btn btn-primary" id="upld" value="Upload Pre-sampling">Upload Pre-sampling</button></a> --> <br><br>
                <div>
          			<a href="/spsv1/cheque/delete"><button value="delete" id="deletefile" class="btn btn-success">Delete</button></a>
                   	<img id="preview" />
				</div>
            </div>

</center>
<h2 class="successmsg">${uploadmsg }</h2>
<h2 class="successmsg">${uploaddelete }</h2>

        </div>


</body>
</html>