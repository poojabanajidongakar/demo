<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<% response.setHeader("Cache-Control", "no-cache,must-revalidate,proxy-revalidate,max-age=0");
response.setHeader("Pragma", "no-cache"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<title>DDEntry</title>

<script type="text/javascript">

        function preventBack() 
        { 
        	window.history.forward(); 
        }
        setTimeout("preventBack()", 0);
        window.onunload = function () 
        { 
        	null 
        };
        
    </script>

<style type="text/css">
/* #logoutbtn{
	margin-right: 2%;
	    margin-left: 50%;
} */
</style>
</head>
<body>
<div class="float-right" id="logoutbtn">
	<spring:url value="/user/login" var="logOutUrl"/><br/>
	
	<a href="${ logOutUrl }" class="btn btn-primary">Logout</a>
</div>
</body>
</html>