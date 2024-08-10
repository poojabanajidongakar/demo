<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- <meta charset="ISO-8859-1"> -->

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Welcome Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Welcome.css">
<link rel="stylesheet" href="/poojaDongarkar/js/navigation.js">

</head>
<body>

	<div class="wrapper">
		<form action="${pageContext.request.contextPath}/user/Navigation"
			method="get">
			<h1>Pooja Dongarkar</h1>
			<h2>Welcome To the World of Fashion</h2>
			<h3>Don't change to fit the fashion, change the fashion to fit
				you.</h3>
    <div class="container">
				<!--         <button class="button-64">Makeup</button> -->
				<!-- 					<button class="button-64">Stitching</button> -->

				<button class="button-64" role="button">
					<span class="text">Makeup</span>
				</button>
				<button class="button-64" role="button" formaction="">
					<span class="text">Stitching</span>
				</button>

			</div>
		</form>
	</div>



	<div class="slider">
		<div class="slide-track">
			<div class="slide">
				<img src="${pageContext.request.contextPath}/image/img3.jpg"
					alt="Image 1" />
			</div>

			<div class="slide">
				<img src="${pageContext.request.contextPath}/image/img3.jpg"
					alt="Image 1" />
			</div>
			<div class="slide">
				<img src="${pageContext.request.contextPath}/image/img3.jpg"
					alt="Image 1" />
			</div>



			<div class="slide">
				<img src="${pageContext.request.contextPath}/image/img3.jpg"
					alt="Image 1" />
			</div>

			<div class="slide">
				<img src="${pageContext.request.contextPath}/image/img3.jpg"
					alt="Image 1" />
			</div>
			<div class="slide">
				<img src="${pageContext.request.contextPath}/image/img3.jpg"
					alt="Image 1" />
			</div>
			
			
			<button class="button-64" role="button" formaction="${pageContext.request.contextPath}/user/login">
					<span class="text">Logout</span>
				</button>


		</div>
	</div>


</body>
</html>
