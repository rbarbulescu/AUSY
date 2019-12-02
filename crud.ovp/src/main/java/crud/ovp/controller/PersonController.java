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

import crud.ovp.persistence.dao.PersonDAO;
import crud.ovp.persistence.model.Person;

@Path("/persons")
public class PersonController {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

	PersonDAO personDAO = context.getBean(PersonDAO.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getPersons() {
		return personDAO.displayPersons();
	}

	@GET
	@Path("/{userName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person findPerson(@PathParam("userName") String userName) {
		return personDAO.checkUserName(userName);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void addPerson(Person person) {
		personDAO.createPerson(person);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void updatePerson(Person person) {
		personDAO.updatePerson(person.getId(), person.getEmail(), person.getUserName(), person.getPassword(), person.getAdminsId());
	}

	@DELETE
	@Path("/{personId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deletePerson(@PathParam("personId") String personId) {
		personDAO.deletePerson(personId); 
	}

}
