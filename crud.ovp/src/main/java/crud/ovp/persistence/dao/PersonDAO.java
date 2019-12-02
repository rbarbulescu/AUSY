package crud.ovp.persistence.dao;

import java.util.List;

import crud.ovp.persistence.model.Person;

public interface PersonDAO {

	void createPerson(Person person);
	void updatePerson(String email, String userName, String password, String adminsId, String typeOfUser);
	void deletePerson(String userName);
	Person findByPersonUsername(String personsUsername);
	List<Person> displayPersons();
	Person checkUserName(String userName);
	
}
