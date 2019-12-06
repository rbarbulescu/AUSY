package ovp.backend.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ovp.backend.persistence.dao.UserDAO;
import ovp.backend.persistence.dao.UserTypeDAO;
import ovp.backend.persistence.model.User;

@Path("/users")
public class UserController {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	
	UserDAO userDAO = context.getBean(UserDAO.class);
	UserTypeDAO userTypeDAO = context.getBean(UserTypeDAO.class);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers(){
		return userDAO.displayUsers();
	}
	
	@GET
	@Path("/{userName}")
	@Produces(MediaType.APPLICATION_JSON)	
	public User findUser(@PathParam("userName") String userName) {
		return userDAO.findUserByUserName(userName);
	}
	
	@POST 
	@Produces(MediaType.APPLICATION_JSON)
	public void addUser(String json){
		
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(json);
			String userName = jsonObj.getString("userName");
			String password = jsonObj.getString("password");

			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
						
			userDAO.createUser(user);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@PUT
	public void changePassword(String json) {
		
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(json);
			String userName = jsonObj.getString("userName");
			String oldPassword = jsonObj.getString("oldPassword");
			String newPassword = jsonObj.getString("newPassword");
			
			User check = (User) userDAO.loginCheck(userName, oldPassword);
			System.out.println("check password: " + check.getPassword() + " old password: " + oldPassword);
			
			if(check.getPassword() == oldPassword) {
				userDAO.updateUser(userName, newPassword);
			} else {
				System.out.println("retry");
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Path("/{userName}")
	public void deleteUser(@PathParam("userName") String userName) {
		userDAO.deleteUser(userName);
	}
	
	@POST 
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public User loginCheck(String json) {
		
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(json);
			String userName = jsonObj.getString("userName");
			String password = jsonObj.getString("password");
			return userDAO.loginCheck(userName, password);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
}





















