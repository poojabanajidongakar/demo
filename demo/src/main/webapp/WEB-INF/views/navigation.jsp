<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Makeup World</title>
<link rel="stylesheet" href="/poojaDongarkar/css/navigation.css">
<link rel="stylesheet" href="/poojaDongarkar/js/navigation.js">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<div class="row mfidataentry">
			<div class="col-md-6 col-sm-6 leftsidediv">
				<table class="table lefttable">
					<form action="${pageContext.request.contextPath}/user/Navigation"
						method="get">
						<center>
							<br> <br>
							<h1>Hey Welcome to My World</h1>
							<h3 Style="font-size: 1.5rem; font-family: fantasy;">Please
								Select below Options and look Pretty.</h3>
							<br>

							<h4 Style="font-size: 25.50px;">Choose an option:</h4>
							<ul
								Style="color: #007bff; text-decoration: wavy; background-color: transparent; font-size: larger; font-family: fantasy;">
								<li><a href="#">Blouse</a></li>
								<li><a href="#">Dress</a></li>
								<li><a href="#">Gown</a></li>
								<li><a href="#">Tops</a></li>
								<li><a href="#">Party Wear</a></li>
							</ul>
						</center>
				</table>
			</div>
			<div class="col-md-6 col-sm-6 rightsidediv">
				<table class="table righttable">
					<%--                 <form action="${pageContext.request.contextPath}/user/Navigation" method="get"> --%>
					<!--                     <center> -->
					<!--                         <h1>Hey Welcome to My World</h1> -->
					<!--                         <h3>Don't change to fit the fashion, change the fashion to fit you.</h3> -->
					<!--                     </center> -->
					<!--                 </form> -->
				</table>
			</div>

			<button class="button-64" role="button"
				formaction="${pageContext.request.contextPath}/user/login">
				<span class="text">Logout</span>
			</button>
			
								</form>
			
		</div>
	</div>

	<!-- Bootstrap JS (optional) -->
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
