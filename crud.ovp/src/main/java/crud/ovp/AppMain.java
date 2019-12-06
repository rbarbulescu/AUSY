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
				
		List<Person> resultPersons = personDAO.displayPersons();
		System.out.println("Persons: ");
		for(Person p : resultPersons) {
			System.out.println(p);			
		}
				
		List<Admin> resultAdmins = adminDAO.displayAdmins();
		System.out.println("Admins: ");
		for(Admin a : resultAdmins) {
			System.out.println(a);
		}			
				
		List<Student> resultStudents = studentDAO.displayStudents();
		System.out.println("Students: ");
		for(Student s : resultStudents) {
			System.out.println(s);
		}
						
		context.close();
		
	}

}
