package crud.ovp.persistence.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import crud.ovp.persistence.dao.PersonDAO;
import crud.ovp.persistence.model.Person;

public class PersonDAOImpl implements PersonDAO{
	
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	
	//method 1 is to insert a new person into database
	public void createPerson(Person person) {
		
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			sessionObj.beginTransaction();
			
			//create transaction entities
			sessionObj.persist(person);
			sessionObj.getTransaction().commit();
			
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
	}
		
	//method 2 is to display the records from the database displaying persons
	@SuppressWarnings("unchecked")
	public List<Person> displayPersons() {
		List<Person> personsList = null;
		
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			personsList = (List<Person>) sessionObj.createCriteria(Person.class).list();	
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
		return personsList;
	}
	
	//check if a username exists in the database
	public Person checkUserName(String userName) {
	    Person personObj = null;
	    try {
			//getting session object from session factory
	    	sessionObj = this.sessionFactory.openSession();
	    	//getting transaction object from session object
	    	sessionObj.beginTransaction();
	    	
	    	personObj = (Person) sessionObj.get(Person.class, userName);
	    	if(personObj != null) {
	    		System.out.println("Username found!\n" + personObj.toString());
	    	}else {
	    		System.out.println("Username does not exists!");
	    	}	    	
	    	sessionObj.getTransaction().commit();
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
	
	//method 3 is used to update a person in the database table
	public void updatePerson(String email, String userName, String password, String adminsId, String typeOfUser) {
		
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			//creating transaction entity
			Person personObj = (Person) sessionObj.get(Person.class, userName);
			if(personObj == null) {
				Person person = new Person();							
				person.setAdminsId(adminsId);
				person.setEmail(email);
				person.setPassword(password);
				person.setTypeOfUser(typeOfUser);
				person.setUserName(userName);
				createPerson(person);
			} else {
				personObj.setTypeOfUser(typeOfUser);
				personObj.setEmail(email);
				personObj.setPassword(password);
				personObj.setAdminsId(adminsId);
			}	
			//commiting the transactions to the database
			sessionObj.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			if(sessionObj.getTransaction() != null) {
				sessionObj.getTransaction().rollback();
			}
		} finally {
			if(sessionObj != null){
				sessionObj.close();
			}
		}
	}
	
	//method 4 is used to delete a person using id from the table in database
	public void deletePerson(String userName) {
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			Person personObj = findByPersonUsername(userName);
			sessionObj.delete(personObj);
			
			// Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
           
        } catch(Exception sqlException) {
            if(sessionObj.getTransaction() != null) {                
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
	}
	
	//finding a person after his id method
	public Person findByPersonUsername(String personsUsername) {
        Person findPersonObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = this.sessionFactory.openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
           
            findPersonObj = (Person) sessionObj.load(Person.class, personsUsername);
        } catch(Exception sqlException) {
            if(sessionObj.getTransaction() != null) {         
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } 
        return findPersonObj;
    }
	
}
