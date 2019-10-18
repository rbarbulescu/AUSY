<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
	.body{
		background-color: #000;
		color: grey;
		font-family:serif;
	}
</style>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
%>
<%@ page import="java.util.Date"%>
<body class="body">
	<%
	if(password.length() < 6){
		out.print("Please select a password with more than 6 characters");
	}else{%>
		<h1>Hello</h1><br><%out.println(username);%><br>
		<%Date d = new Date();
		out.println("Current date: " + d);
	}
	%>
</body>
</html>