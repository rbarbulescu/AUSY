<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<html>
<head>
<meta http-equiv="refresh" content="2;URL=/FinalProject/index.jsp">
<title>Hello</title>
</head>
<%
// 	String firstname = request.getParameter("firstname");
// 	String lastname = request.getParameter("lastname");
// 	String phone = request.getParameter("phone");
// 	String address = request.getParameter("address");
	String email = request.getParameter("email");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String typeOfUser = request.getParameter("typeofuser");
%>
<body>
	<%
	
	if(password.length() < 6){
		out.print("Please select a password with more than 6 characters");
	}else{
		%>
		<h1>Hello </h1><%out.println(username);
		try {
			Connection connection = null;	
			//Connection to my database
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ovpdb", "root", "Password@123");
//			//insert into db			
// 			if (!connection.isClosed()){
// 				out.println("Successfully connected to " + "MySQL server using TCP/IP...");
// 				PreparedStatement ps = connection.prepareStatement("INSERT INTO ovpdb.persons(email, username, password, typeofuser) VALUES ('"+email+"', '"+username+"', '"+password+"', '"+typeOfUser+"');");
// 				ps.executeUpdate();
// 				System.out.println("User added");
// 			}
			Statement loginValidation = connection.createStatement();
			ResultSet rs = loginValidation.executeQuery("SELECT email, username FROM ovpdb.persons WHERE email='"+username+"';");
			String user = "";
			while (rs.next()) {
				user = rs.getString("username");
				%><h1>Hello </h1><%out.println(user);
			}	
			
			connection.close();
		} catch (Exception ex) {
			out.println("Unable to connect to database.");
		}		
	}	
	%> 
	
	

</body>
</html>