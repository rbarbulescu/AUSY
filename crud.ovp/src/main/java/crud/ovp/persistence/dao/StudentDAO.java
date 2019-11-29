package crud.ovp.persistence.dao;

import java.util.List;

import crud.ovp.persistence.model.Student;

public interface StudentDAO {

	void createStudent(Student student);
	void updateStudent(String firstName, String lastName, String phone, String address, String CNP, String birthday, int trips, String personsID);
	void deleteStudent(String CNP);
	Student findByStudentCNP(String find_studentCNP);
	void deleteAllStudents();
	List<Student> displayStudents();
	void selectStudentByCNP(String CNP);
	
}
