package crud.ovp;

import java.util.List;

import crud.ovp.daoimpl.AdminDAOImpl;
import crud.ovp.daoimpl.PersonDAOImpl;
import crud.ovp.daoimpl.StudentDAOImpl;
import crud.ovp.model.Admin;
import crud.ovp.model.Person;
import crud.ovp.model.Student;

public class AppMain {
	
	public static void main(String[] args) {
		
		//StudentOperations.deleteAllStudents();
		
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
		stdObj.deleteAllStudents();
		
		
		List<Student> students = stdObj.displayStudents();		
		if(students != null && students.size() > 0) {
			for(Student studentObj : students) {
				System.out.println(studentObj.toString());
			}
		} else {
			System.out.println("No Students!");
		}
		
		PersonDAOImpl persObj = new PersonDAOImpl();
		
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
