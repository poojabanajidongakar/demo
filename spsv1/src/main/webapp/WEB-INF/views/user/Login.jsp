<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

<spring:url value="/css/style2.css" var="style" />
<link rel="stylesheet" href="${ style }">

<spring:url value="/css/style3.css" var="style1" />
<link rel="stylesheet" href="${ style1 }">

<spring:url value="/css/font-awesome.css" var="style2" />
<link rel="stylesheet" href="${ style2 }">

<spring:url value="../css/elements.css" var="elements" />
<link rel="stylesheet" href="${ elements }">

<spring:url value="/css/jquery-confirm.min.css" var="jqueryconfirmcss" />
<link rel="stylesheet" href="${ jqueryconfirmcss }">

<spring:url value="/js/jquery-confirm.min.js" var="jqueryconfirmjs" />
<script src="${ jqueryconfirmjs }"></script>

<spring:url value="../js/passnotification.js" var="passnotificationjs" />
<script src="${ passnotificationjs }"></script>

<title>SPS</title>

<script>
	function disableAutoComplete() {
		var f = document.getElementById("auto");
		var u = f.elements[0];
		f.setAttribute("autocomplete", "off");
		u.focus();
	}
</script>
</head>
<body onload="disableAutoComplete()">
	<c:set var="count" scope="session" value="${ count }" />
	<c:set var="user" scope="session" value="${ user }" />
	<%
	response.setHeader("Cache-Control", "no-cache,must-revalidate,proxy-revalidate,max-age=0");
	response.setHeader("Pragma", "no-cache");
	%>

	<h1>Login Form</h1>
	<div class="main-agile">
		<h2 class="error">${error }</h2>
		<div class="alert-close"></div>
		<div class="content-wthree">
			<div class="new-account-form">

				<h2 class="heading-w3-agile">Login</h2>
				<form action="/spsv1/user/login" method="post">
					<input type="hidden" value="${count}" name="count">
					<div class="inputs-w3ls">
						<p>Username</p>
						<i class="fa fa-user" aria-hidden="true"></i> <input type="text"
							class="email" value="${user.getUsername()}" name="username"
							id="userName" required="" autocomplete="off"
							onCopy="return false" onPaste="return false">
					</div>
					<div class="inputs-w3ls">
						<p>Password</p>
						<i class="fa fa-unlock-alt" aria-hidden="true"></i> <input
							type="password" class="password" name="password" id="password"
							required="" autocomplete="off" maxlength="15"
							onCopy="return false" onPaste="return false">
					</div>
					<input type="submit" value="Login">
				</form>
			</div>
			<div class="clear"></div>
		</div>
	</div>



	<div id="abc">
		<!-- Popup Div Starts Here -->
		<div id="popupContact">
			<!-- Contact Us Form -->
			<form>
				<img id="close" src="../images/3.png" onclick="div_hide()">
				<h2>Change Password</h2>
				<hr>
				<!-- <input type="hidden" id="id1" /> -->
				<input class="name" name="uname" id="uname" readonly value=""
					type="text"> <input class="name" name="oldpassword"
					id="oldpassword" placeholder="Enter Old Password" maxlength="15"
					value="" type="password" onCopy="return false"
					onPaste="return false"> <input class="name"
					name="newpassword" id="newpassword"
					placeholder="Enter New Password" maxlength="15" value=""
					type="password" onCopy="return false" onPaste="return false">
				<input class="name" name="confirmpassword" id="confirmpassword"
					placeholder="Enter Confirm Password" maxlength="15" value=""
					type="password" onCopy="return false" onPaste="return false">
				<a href="" id="submit" class="editdata">Update</a>
			</form>
		</div>
		<!-- Popup Div Ends Here -->
	</div>





</body>
</html>