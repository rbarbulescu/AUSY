package crud.operations;

import java.util.List;

import crud.operations.admin.Admin;
import crud.operations.admin.AdminOperations;
import crud.operations.person.Person;
import crud.operations.person.PersonOperations;
import crud.operations.student.Student;
import crud.operations.student.StudentOperations;

public class AppMain {
	
	public static void main(String[] args) {
		
		//AdminOperations.deleteAdmin("admin2");
		//StudentOperations.deleteAllStudents();
		//read records
		List<Admin> admins = AdminOperations.displayAdmins();		
		if(admins != null && admins.size() > 0) {
			for(Admin adminObj : admins) {
				System.out.println(adminObj.getAdminId() + " " + adminObj.getInstitution());
			}
		} else {
			System.out.println("invalid displayAdmins()");
		}
		
		List<Student> students = StudentOperations.displayStudents();		
		if(students != null && students.size() > 0) {
			for(Student studentObj : students) {
				System.out.println(studentObj.getFirstname() + " " + studentObj.getLastname());
			}
		} else {
			System.out.println("invalid displayStudents()");
		}
		
		List<Person> persons = PersonOperations.displayPersons();		
		if(persons != null && persons.size() > 0) {
			for(Person personObj : persons) {
				System.out.println(personObj.getEmail() + " " + personObj.getUsername());
			}
		} else {
			System.out.println("invalid displayPersons()");
		}
		
	}

}
