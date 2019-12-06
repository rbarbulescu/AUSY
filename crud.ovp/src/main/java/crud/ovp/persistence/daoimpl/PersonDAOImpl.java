package crud.ovp.persistence.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import crud.ovp.persistence.dao.PersonDAO;
import crud.ovp.persistence.model.Person;

public class PersonDAOImpl implements PersonDAO {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// method 1 is to insert a new person into database
	public void createPerson(Person person) {

		try {
			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			sessionObj.beginTransaction();

			// create transaction entities
			sessionObj.persist(person);
			sessionObj.getTransaction().commit();

		} catch (Exception e) {
			// TODO: handle exception
			if (sessionObj.getTransaction() != null) {
				sessionObj.getTransaction().rollback();
			}
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// method 2 is to display the records from the database displaying persons
	@SuppressWarnings("unchecked")
	public List<Person> displayPersons() {
		List<Person> personsList = null;

		try {
			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			// getting transaction object from session object
			sessionObj.beginTransaction();
			personsList = (List<Person>) sessionObj.createCriteria(Person.class).list();
		} catch (Exception e) {
			// TODO: handle exception
			if (sessionObj.getTransaction() != null) {
				sessionObj.getTransaction().rollback();
			}
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
		return personsList;
	}

	// check if a username exists in the database
	public Person checkUser(String userName, String password) {
		Person personObj = null;
		

	    try {
	    	System.out.println("finding user");

			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			// getting transaction object from session object
			sessionObj.beginTransaction();

			personObj = findByPersonUsername(userName);
			System.out.println("Start search");
			System.out.println(personObj);
			if (personObj.getUserName().equals(userName) && personObj != null) {

				System.out.println("Correct username");
				System.out.println(personObj.toString());

				if (personObj.getPassword().equals(password)) {
					System.out.println("Well done!");
					return personObj;
				} else {
					System.out.println("Wrong password");
					return null;
				}

			} else {
				System.out.println("Wrong username or wrong password");
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			if(sessionObj.getTransaction() != null) {
				sessionObj.getTransaction().rollback();
			}
		} finally {
			if(sessionObj != null) {
				sessionObj.close();				
			}			
		}
		return personObj;	   
	}

	// method 3 is used to update a person in the database table
	public Person updatePerson(String email, String userName, String password, String adminsId, String typeOfUser) {
		Person personObj = null;

		try {
			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			// getting transaction object from session object
			sessionObj.beginTransaction();

			// creating transaction entity
			personObj = (Person) sessionObj.get(Person.class, userName);

			if (personObj.getUserName() != null) {
				System.out.println("Person found!");
				personObj.setTypeOfUser(typeOfUser);
				personObj.setEmail(email);
				personObj.setPassword(password);
				personObj.setAdminsId(adminsId);
				sessionObj.getTransaction().commit();

			} else {
				System.out.println("Person does not exist you should create a person before update!");
			}
			// commiting the transactions to the database

		} catch (Exception e) {
			// TODO: handle exception
			if (sessionObj.getTransaction() != null) {
				sessionObj.getTransaction().rollback();
			}
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
		return personObj;
	}

	// method 4 is used to delete a person using id from the table in database
	public void deletePerson(String userName) {
		try {
			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			// getting transaction object from session object
			sessionObj.beginTransaction();

			Person personObj = findByPersonUsername(userName);
			sessionObj.delete(personObj);

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();

		} catch (Exception sqlException) {
			if (sessionObj.getTransaction() != null) {
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	// finding a person after his id method
	public Person findByPersonUsername(String personsUsername) {
		Person findPersonObj = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = this.sessionFactory.openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			findPersonObj = (Person) sessionObj.get(Person.class, personsUsername);
		} catch (Exception sqlException) {
			if (sessionObj.getTransaction() != null) {
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		}
		return findPersonObj;
	}

}
