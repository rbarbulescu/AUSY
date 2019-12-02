package crud.ovp.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import crud.ovp.persistence.dao.AdminDAO;
import crud.ovp.persistence.model.Admin;

@Path("/admins")
public class AdminController {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	
	AdminDAO adminDAO = context.getBean(AdminDAO.class);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Admin> getAdmins(){
		return adminDAO.displayAdmins();
	}
	
	@GET 
	@Path("/{admin}")
	@Produces(MediaType.APPLICATION_JSON)
	public Admin findAdmin(@PathParam("admin") String adminId) {
		return adminDAO.findByAdminId(adminId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void addAdmin(Admin admin) {
		adminDAO.createAdmin(admin);
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void updateAdmin(Admin admin) {
		adminDAO.updateAdmin(admin.getAdminId(), admin.getInstitution());
	}
	

	@DELETE
	@Path("/{adminId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteCountry(@PathParam("adminId") String adminId) {
		adminDAO.deleteAdmin(adminId);
	}
}
