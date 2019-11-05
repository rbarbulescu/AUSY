<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<style type="text/css">
html, body {
	height: 100%;
	width: 100%;
}
body {
	min-width: 300px;
	min-height: 300px;
	/* 	background-image: linear-gradient(to bottom right, rgba(10,255,10,0.1), rgba(66,66,66,0.8)); */
	background-image: url("images/hero.jpg");
	background-repeat: no-repeat;
	background-position: center;
	background-attachment: fixed;
	background-size: cover;
	width: 100%;
	height: 100vh;
}
.wrapper {
	width: 100%;
}
form {
	background-color: #fff;
}
.form-container {
	border-radius: 50px;
	padding: 30px;
	box-shadow: 7px 7px 5px 2px black;
	opacity: 0.8;
}
.center-image {
	display: block;
	margin-left: auto;
	margin-right: auto;
	width: 50%;
	border-radius: 50%;
	margin-bottom: 10px;
	box-shadow: 7px 7px 5px 2px black;
}
.wrapper-card {
	opacity: 1;
	padding: 30px;
}
.wrapper {
	bakground-color: black;
}
.bg {
	background-image: linear-gradient(to bottom right, rgba(10, 255, 10, 0.1),
		rgba(66, 66, 66, 0.8));
	opacity: 0.4;
}
</style>

</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="index.jsp">Home</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="about.jsp">About</a></li>
					<li class="nav-item"><a class="nav-link" href="contact.jsp">Contact</a></li>
					<li class="nav-item"><a class="nav-link" href="signup.jsp">Sign up!</a>
					<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				</ul>
			</div>
		</nav>

		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-xl-4 col-lg-5 col-md-6 col-sm-8 col-xs-12">
					<div class="wrapper-card">
						<img src="images/sign-up.jpg" class="center-image">
						<form class="form-container" method="POST" action="save">
							<h4 class="text-center font-weight-bold">Sign up form</h4>
							<div class="form-group">
								<input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="Choose your email" name="email">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" id="inputusername" placeholder="Choose your username" name="username">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="inputPassword" placeholder="Choose a Password" name="password">
							</div>
							<div class="form-group">
							<div class="form-group">
								<input type="text" class="form-control" id="typeOfUser" placeholder="Enter your type ID" name="typeOfUser">
							</div>
							<select class="custom-select" id="adminsId" name="adminsId">
							    <option selected>Choose your admin...</option>
							    <option value="admin1">Admin 1</option>
							    <option value="admin2">Admin 2</option>
								<option value="admin3">Admin 3</option>
							</select>
							</div>							
							<button type="submit" value="Save" class="btn btn-primary btn-block">Submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="js/bootsrap.js"></script> <script
		type="text/javascript" src="js/bootsrap.bundle.js"></script> <script
		type="text/javascript" src="js/bootsrap.bundle.js.map"></script>

</body>
</html>