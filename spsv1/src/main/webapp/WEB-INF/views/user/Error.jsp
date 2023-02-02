<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/css/error.css" var="error" />
<link rel="stylesheet" href="${ error }">
<title>DDEntry</title>
</head>
<body>

 <div id="clouds">
            <div class="cloud x1"></div>
            <div class="cloud x1_5"></div>
            <div class="cloud x2"></div>
            <div class="cloud x3"></div>
            <div class="cloud x4"></div>
            <div class="cloud x5"></div>
        </div>
        <div class='c'>
            <div class='_404'>404</div>
            <hr>
            <div class='_1'>SOMETHING WENT WRONG...!!</div>
            <div class='_2'></div>
            <button><a id="btt" class='btn' href='/cts/adminresult?back=back'>BACK TO PAGE</a></button>
        </div>
<!-- <center><button><b><a id="btt" href="/cts/adminresult?back=back">Back</a></b></button></center> -->
</body>
</html>