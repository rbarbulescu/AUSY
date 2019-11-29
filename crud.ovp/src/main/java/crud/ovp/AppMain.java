package crud.ovp;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import crud.ovp.persistence.dao.AdminDAO;
import crud.ovp.persistence.dao.PersonDAO;
import crud.ovp.persistence.dao.StudentDAO;
import crud.ovp.persistence.model.Admin;
import crud.ovp.persistence.model.Person;
import crud.ovp.persistence.model.Student;

public class AppMain {
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		
		AdminDAO adminDAO = context.getBean(AdminDAO.class);
		PersonDAO personDAO = context.getBean(PersonDAO.class);
		StudentDAO studentDAO = context.getBean(StudentDAO.class);
		
		Person person = new Person();
		person.setAdminsId("firstAdmin1");
		person.setEmail("email@exmaple.fr");
		person.setPassword("password");
		person.setTypeOfUser("Student");
		person.setUserName("usernameunique");
		
		personDAO.createPerson(person);
		
		List<Person> resultPersons = personDAO.displayPersons();
		System.out.println("Persons: ");
		for(Person p : resultPersons) {
			System.out.println(p);			
		}
		
		
		Admin admin = new Admin();
		admin.setAdminId("firstAdmin1");
		admin.setInstitution("ACE");
		
		adminDAO.updateAdmin("admin2", "Unemployed");
		adminDAO.checkAdminId("firstAdmin1");
		//adminDAO.createAdmin(admin);
		
		List<Admin> resultAdmins = adminDAO.displayAdmins();
		System.out.println("Admins: ");
		for(Admin a : resultAdmins) {
			System.out.println(a);
		}			
		
		Student student = new Student();
		student.setAddress("Sibiu");
		student.setBirthday("19941224");
		student.setFirstName("Raluca");
		student.setLastName("Raluca's LastName");
		student.setPersonsId(person.getUserName());
		student.setPhone("0767854999");
		student.setTrips(5);
		student.setCNP("1941224160088");

		studentDAO.createStudent(student);
		
		List<Student> resultStudents = studentDAO.displayStudents();
		System.out.println("Students: ");
		for(Student s : resultStudents) {
			System.out.println(s);
		}
				
		context.close();
		
	}

}
