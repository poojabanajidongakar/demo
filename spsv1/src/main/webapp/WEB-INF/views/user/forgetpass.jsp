<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Single Payment System</title>
<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script> -->


<spring:url value="../js/validation.js" var="validationjs" />
<script src="${ validationjs }"></script>

<%--    <spring:url value="../js/dateValidation.js" var="validationjs1" />
  <script src="${ validationjs1 }"></script> --%>
  
  <link rel="stylesheet" href="/spsv1/css/style.css">
 

  <link rel="stylesheet" href="/spsv1/css/style1.css">

  <link rel="stylesheet" href="/spsv1/main.css">

 <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"> -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
  
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

 <div class="form-gap"></div>
<div class="container loginForm">
	<div class="row forgetpass">
		<div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="text-center">
                 <!--  <h3><i class="fa fa-lock fa-4x"></i></h3> -->
                  <h2 class="text-center">Forget Password?</h2>
                  <p>You can change your password here.</p>
                  <div class="panel-body">
    
                 <spring:url value="/forgetpassword/forgetpass" var="forgetpassurl" />
                <form id="auto" action="${ forgetpassurl }" method="post" onsubmit="return loginvalidation()">
               <input type="hidden" name="action" value="forgetpass">

                      <div class="form-group">
                        <div class="input-group">
                         <%--  <select class="emp_choice1" name="username" id="employee" required>
										<option value="">---Select---</option>
											<c:forEach var="username" items="${ulist }">
									            <option value="${username.getUsername() }">${username.getUsername() }</option>
								            </c:forEach>
								         </select> --%>
								          <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
								         <input id="username" name="username" placeholder="Username"  value="${username }" class="form-control"  type="text" validate="true" onDrag="return false" onDrop="return false" onPaste="return false" onCopy="return false" required>
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <div class="input-group">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                          <input id="username" name="newpassword" placeholder="New password" class="form-control"  type="password" validate="true" onDrag="return false" onDrop="return false" onPaste="return false" onCopy="return false" required>
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <div class="input-group">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                          <input id="username" name="confirmpassword" placeholder="confirm password" class="form-control"  type="password" validate="true" onDrag="return false" onDrop="return false" onPaste="return false" onCopy="return false" required>
                        </div>
                      </div>
                      <div class="form-group">
                        <input name="Submit btn" class="btn btn-lg btn-primary " value="Change Password" type="submit">
                    	<!-- <button type="button"><a href="/mainapp/cheque/show">Back</a></button> -->
                      </div>
                      <h4 class="successmsg">${succmsg }</h4>
                      <h4 class="loginerror">${error }</h4>
                    </form>
    </div>
    </div>
    </div>
    </div>
                  </div>
                </div>
              </div>
              </body>