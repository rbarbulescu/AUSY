package crud.ovp.dao;

import java.util.List;

import crud.ovp.model.Person;

public interface PersonDAO {

	void createPerson(String email, String userName, String password, String typeOfUser, String adminsId);
	void updatePerson(int id, String email, String userName, String password, String adminsId);
	void deletePerson(String userName);
	Person findByPersonUsername(String personsUsername);
	List<Person> displayPersons();
	
}
