<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

		<h1>Add New Person</h1>
       <form:form method="post" action="save">  
      	<table >  
      	<tr>  
         <tr>  
          <td>Email :</td> 
          <td><form:input path="email"  /></td>
         </tr>  
         <tr>  
          <td>Username :</td>  
          <td><form:input path="username" /></td>
         </tr> 
         <tr>  
          <td>Password :</td>  
          <td><form:input path="password" /></td>
         </tr> 
         <tr>  
          <td>Type of user :</td>  
          <td><form:input path="typeofuser" /></td>
         </tr> 
         <tr>  
          <td>Admins ID :</td>  
          <td><form:input path="adminsId" /></td>
         </tr> 
         <tr>  
          <td> </td>  
          <td><input type="submit" value="Save" /></td>  
         </tr>  
        </table>  
       </form:form>  
