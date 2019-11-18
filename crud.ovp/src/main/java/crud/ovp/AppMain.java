package crud.ovp;

import java.util.List;

import crud.ovp.model.Admin;
import crud.ovp.model.Person;
import crud.ovp.model.Student;
import crud.ovp.persistence.daoimpl.AdminDAOImpl;
import crud.ovp.persistence.daoimpl.PersonDAOImpl;
import crud.ovp.persistence.daoimpl.StudentDAOImpl;

public class AppMain {
	
	public static void main(String[] args) {
		
		AdminDAOImpl admObj = new AdminDAOImpl();				
		List<Admin> admins = admObj.displayAdmins();		
		if(admins != null && admins.size() > 0) {
			for(Admin adminObj : admins) {
				System.out.println(adminObj.toString());
			}
		} else {
			System.out.println("No Admins!");
		}
		
		StudentDAOImpl stdObj = new StudentDAOImpl();
		stdObj.createStudent("Razvan", "Barbulescu", "0730217022", "Craiova", "1950524160025", "19950524", 51, "username");
		
		stdObj.selectStudentByCNP("1950524160025");

		List<Student> students = stdObj.displayStudents();		
		if(students != null && students.size() > 0) {
			for(Student studentObj : students) {
				System.out.println(studentObj.toString());
			}
		} else {
			System.out.println("No Students!");
		}
		
		PersonDAOImpl persObj = new PersonDAOImpl();
		persObj.checkUserName("username");
		
		List<Person> persons = persObj.displayPersons();		
		if(persons != null && persons.size() > 0) {
			for(Person personObj : persons) {
				System.out.println(personObj.toString());
			}
		} else {
			System.out.println("No Persons!");
		}
		
	}

}
