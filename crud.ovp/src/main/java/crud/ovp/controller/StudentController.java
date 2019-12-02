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

import crud.ovp.persistence.dao.StudentDAO;
import crud.ovp.persistence.model.Student;

@Path("/students")
public class StudentController {

	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

	StudentDAO studentDAO = context.getBean(StudentDAO.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudents() {
		return studentDAO.displayStudents();
	}

	@GET
	@Path("/{studentCNP}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student findStudent(@PathParam("studentCNP") String studentCNP) {
		return studentDAO.checkStudentByCNP(studentCNP);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void addStudent(Student student) {
		studentDAO.createStudent(student);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student.getFirstName(), student.getLastName(), student.getPhone(), student.getAddress(), student.getCNP(), student.getBirthday(), student.getTrips(), student.getPersonsId());
	}

	@DELETE
	@Path("/{studentCNP}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteStudent(@PathParam("studentCNP") String studentCNP) {
		studentDAO.deleteStudent(studentCNP);
	}

}
