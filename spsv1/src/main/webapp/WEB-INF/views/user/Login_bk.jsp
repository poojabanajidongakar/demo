<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%-- <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> --%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>
<spring:url value="/css/bootstrap.min.css" var="bootstrapcssUrl" />
<link rel="stylesheet" href="${ bootstrapcssUrl }">

<spring:url value="/js/jquery.min.js" var="jqueryjsUrl" />
<script src="${ jqueryjsUrl }"></script>

<spring:url value="/js/bootstrap.min.js" var="bootstrapjsUrl" />
<script src="${ bootstrapjsUrl }"></script>

<spring:url value="/js/validation.js" var="validationjs" />
<script src="${ validationjs }"></script>

<spring:url value="/css/style1.css" var="style1" />
<link rel="stylesheet" href="${ style1 }">

<title>SPS</title>

<script>
function disableAutoComplete(){
	var f=document.getElementById("auto");
	var u=f.elements[0];
	f.setAttribute("autocomplete","off");
	u.focus();
}
</script>
</head>
<body onload="disableAutoComplete()">

<% response.setHeader("Cache-Control", "no-cache,must-revalidate,proxy-revalidate,max-age=0");
response.setHeader("Pragma", "no-cache"); %>
	<div class="container">
		<div class="row" id="mainform">
			<div class="col-md-offset-5 col-md-12">
				<%-- <spring:url value="/user/login" var="loginUrl" /> --%>
				<%-- <form id="auto" action="${ loginUrl }" method="post" onsubmit="return loginvalidation()"> --%>
				<form id="auto" action="/spsv1/user/login" method="post" onsubmit="return loginvalidation()">	
					<input type="hidden" name="action" value="login">
					<div class="form-login">
						<h4 class="loginerror">${error }</h4>
						<%-- <h3>${ error }</h3> --%>
						<h4>Login</h4>
						<input type="text" id="userName" name="username"
							class="form-control input-sm chat-input validlogin" placeholder="username"
							required  onblur="clearUp(this)"/>
							<span></span> <br /> 
							
							<input type="password" id="password"
							name="password" class="form-control input-sm chat-input validlogin"
							placeholder="password" required  onblur="clearUp(this)"/>
							<span></span>  <br />
						<div class="wrapper">
							<span class="group-btn"> <input type="submit"
								class="btn btn-primary" value="submit">
							</span>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>