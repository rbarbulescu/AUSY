package crud.ovp.persistence.dao;

import java.util.List;

import crud.ovp.persistence.model.Person;

public interface PersonDAO {

	void createPerson(String email, String userName, String password, String typeOfUser, String adminsId);
	void updatePerson(int id, String email, String userName, String password, String adminsId);
	void deletePerson(String userName);
	Person findByPersonUsername(String personsUsername);
	List<Person> displayPersons();
	void checkUserName(String userName);
	
}
