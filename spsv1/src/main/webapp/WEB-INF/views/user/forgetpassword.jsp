<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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


<title>Single Payment System</title>
</head>
<body onload="disableAutoComplete()" id="bodybackimg">
<% response.setHeader("Cache-Control", "no-cache,must-revalidate,proxy-revalidate,max-age=0");
response.setHeader("Pragma", "no-cache"); %>

<jsp:include page="forgetheader.jsp"></jsp:include>

 <div class="form-gap"></div>

	 <div class="container loginForm">
		<div class="row" id="mainform">
			
	<div class="row forgetpass readonlydata">
		<div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
              <div class="panel-body">
                <div class="text-center">
                 <!--  <h3><i class="fa fa-lock fa-4x"></i></h3> -->
                  <h2 class="text-center">Change Password?</h2>
                  <p>You can change your password here.</p>
                  <div class="panel-body">
    
                 <spring:url value="/forgetpassword/changeforgetpassword" var="forgetpassurl" />
                <form id="auto" action="${ forgetpassurl }" method="post" onsubmit="return forgetpassvalidation()">
               <input type="hidden" name="action" value="changepassword">

                      <div class="form-group">
                        <div class="input-group">
						 <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                          <%-- <input id="username" name="username" placeholder="username" value="${username }" readonly class="form-control passlog" required autocomplete="off" type="text"> --%>
                          <c:choose>
	                          <c:when test="${username1 !=null}">
	                          		<input id="username" name="username" placeholder="username" value="${username1 }" readonly class="form-control passlog" required autocomplete="off" type="text">
	                          </c:when>
	                          <c:otherwise>
	                        		<input id="username" name="username" placeholder="username" value="${username }" readonly class="form-control passlog" required autocomplete="off" type="text">  
	                          </c:otherwise>
                          </c:choose> 
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <div class="input-group">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                          <%-- <input id="username" name="oldpassword" placeholder="old password" value="${password }" class="form-control passlog" readonly type="text" required onDrag="return false" onDrop="return false" onPaste="return false" onCopy="return false"> --%>
                        	<c:choose>
	                          <c:when test="${password !=null}">
	                          		<input id="username" name="oldpassword" placeholder="old password" value="${password }" class="form-control passlog" maxlength="15" readonly type="password" required onDrag="return false" onDrop="return false" onPaste="return false" onCopy="return false">
	                          </c:when>
	                          <c:otherwise>
	                        		<input id="username" name="oldpassword" placeholder="old password" value="${oldpassword }" class="form-control passlog" maxlength="15" readonly type="password" required onDrag="return false" onDrop="return false" onPaste="return false" onCopy="return false">  
	                          </c:otherwise>
                          </c:choose> 
                          
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <div class="input-group">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                          <input id="username" name="newpassword" placeholder="New password" class="form-control passlog newpassword"  type="password" maxlength="15" required onDrag="return false" onDrop="return false" onPaste="return false" onCopy="return false">
                        </div>
                      </div>
                      
                      <div class="form-group">
                        <div class="input-group">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                          <input id="username" name="confirmpassword" placeholder="confirm password" class="form-control passlog confirmpassword" maxlength="15" required type="password" validate="true" onDrag="return false" onDrop="return false" onPaste="return false" onCopy="return false">
                        </div>
                      </div>
                      <div class="form-group">
                        <input name="Submit btn" id="changepass" class="btn btn-lg btn-primary " value="Change Password" type="submit">
                    	<!-- <button type="button"><a href="/mainapp/cheque/show">Back</a></button> -->
                      </div>
                       <h4 class="loginerror">${error }</h4>
                     <h4 class="loginerror">${error1 }</h4>
                     <h4 class="successmsg">${succmsg }</h4>
                    </form>
    </div>
    </div>
    </div>
    </div>
          </div></div>
                  </div>
                </div>
              </div>
</body>
</html>