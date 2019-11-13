package crud.operations;

import java.util.List;

import crud.operations.person.Person;
import crud.operations.person.PersonOperations;
import crud.operations.student.Student;
import crud.operations.student.StudentOperations;

public class AppMain {
	
	public static void main(String[] args) {
		
//		DbOperations.createAdmin("admin234", "ACE");
//		DbOperations.createAdmin("admin2112", "ACE");
		
		//read records
		List<Admin> admins = DbOperations.displayAdmins();		
		if(admins != null && admins.size() > 0) {
			for(Admin adminObj : admins) {
				System.out.println(adminObj.getAdminId() + " " + adminObj.getInstitution());;
			}
		} else {
			System.out.println("invalid displayAdmins()");
		}
		
		List<Student> students = StudentOperations.displayStudents();		
		if(students != null && students.size() > 0) {
			for(Student studentObj : students) {
				System.out.println(studentObj.toString());
			}
		} else {
			System.out.println("invalid displayStudents()");
		}
		
		List<Person> persons = PersonOperations.displayPersons();		
		if(persons != null && persons.size() > 0) {
			for(Person personObj : persons) {
				System.out.println(personObj.toString());
			}
		} else {
			System.out.println("invalid displayPersons()");
		}
		
//		DbOperations.updateAdmin("admin111", "UMFCV");
//
//		List<Admin> updatedAdmins = DbOperations.displayAdmins();
//        if(updatedAdmins != null & updatedAdmins.size() > 0) {
//            for(Admin adminObj : updatedAdmins) {
//                System.out.println(adminObj.toString());
//            }
//        } else {
//        	System.out.println("invalid displayAdmins() after update");
//        }
//        
//        DbOperations.deleteAdmin("admin1111");
//        List<Admin> deleteAdminRecord = DbOperations.displayAdmins();
//        for(Admin adminObj : deleteAdminRecord) {
//        	System.out.println(adminObj.toString());
//        }
	
	}

}
