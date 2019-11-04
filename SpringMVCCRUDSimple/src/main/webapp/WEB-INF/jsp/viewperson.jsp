    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

	<h1>Persons List</h1>
	<table>
	<tr><th>Id</th><th>Email</th><th>User name</th><th>Password</th><th>Type of User</th><th>Admins ID</th><th>Edit</th><th>Delete</th></tr>
    <c:forEach var="persons" items="${list}"> 
    <tr>
    <td>${persons.id}</td>
    <td>${persons.email}</td>
    <td>${persons.username}</td>
    <td>${persons.password}</td>
    <td>${persons.typeofuser}</td>
    <td>${persons.adminsId}</td>
    <td><a href="editperson/${persons.id}">Edit</a></td>
    <td><a href="deleteperson/${persons.id}">Delete</a></td>
    </tr>
    </c:forEach>
    </table>
    <br/>
    <a href="personform">Add New Person</a>