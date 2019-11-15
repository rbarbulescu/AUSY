package crud.operations.person;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class PersonOperations {
	
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	
	public final static Logger logger = Logger.getLogger(PersonOperations.class);
	
	//this method is use to create the hibernate's SessionFactory Object
	private static SessionFactory buildSessionFactory() {
		//creating configuration instance & passing hibernate's configuration file
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		
		//since hibernate version 4.x ServiceRegistry is being used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
		 
		//creating hibernate SessionFactory instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		
		return sessionFactoryObj;
	}
	
	//method 1 is to insert a new person into database
	public static void createPerson(String email, String username, String password, String typeofuser, String adminsId) {
		Person person = null;
		
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();
			
			//create transaction entities
			person = new Person();
			person.setEmail(email);
			person.setUsername(username);
			person.setPassword(password);
			person.setTypeofuser(typeofuser);
			person.setAdminsId(adminsId);
			
			sessionObj.save(person);
			sessionObj.getTransaction().commit();
			
			logger.info("\nAdmin created successfully!\n");
			
		} catch (Exception e) {
			// TODO: handle exception
			if(sessionObj.getTransaction() != null) {
				logger.info("\nTransaction is being rolled back...\n");
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
	public static List<Person> displayPersons() {
		List<Person> personsList = null;
		
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			personsList = (List<Person>) sessionObj.createCriteria(Person.class).list();
			logger.info("The persons available");			
		} catch (Exception e) {
			// TODO: handle exception
			if(sessionObj.getTransaction() != null) {
				logger.info("Transaction is being rolled back...");
				sessionObj.getTransaction().rollback();
			}
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
		return personsList;
	}
	
	//method 3 is used to update a person in the database table
	public static void updatePerson(int id, String email, String username, String password, String adminsId) {
		
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			//creating transaction entity
			Person personObj = (Person) sessionObj.get(Person.class, id);
			personObj.setEmail(email);
			personObj.setUsername(username);
			personObj.setPassword(password);
				
			//commiting the transactions to the database
			sessionObj.getTransaction().commit();
			logger.info("\nAdmin with id " + id + " is successfully updated.");
		} catch (Exception e) {
			// TODO: handle exception
			if(sessionObj.getTransaction() != null) {
				logger.info("The transaction is being rolled back...");
				sessionObj.getTransaction().rollback();
			}
		} finally {
			if(sessionObj != null){
				sessionObj.close();
			}
		}
	}
	
	//method 4 is used to delete a person using id from the table in database
	public static void deletePerson(int id) {
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			Person personObj = findByPersonId(id);
			sessionObj.delete(personObj);
			
			// Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nStudent With Id?= " + id + " Is Successfully Deleted From The Database!\n");
        } catch(Exception sqlException) {
            if(sessionObj.getTransaction() != null) {
                logger.info("\n.......Transaction Is Being Rolled Back.......\n");
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
	public static Person findByPersonId(int find_personsId) {
        Person findPersonObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            findPersonObj = (Person) sessionObj.load(Person.class, find_personsId);
        } catch(Exception sqlException) {
            if(sessionObj.getTransaction() != null) {
                logger.info("\nTransaction Is Being Rolled Back...\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } 
        return findPersonObj;
    }
	
}
