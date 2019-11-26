package crud.ovp.persistence.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import crud.ovp.persistence.dao.StudentDAO;
import crud.ovp.persistence.model.Student;

public class StudentDAOImpl implements StudentDAO{
	
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	
	public final static Logger logger = Logger.getLogger(StudentDAOImpl.class);
	
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
	
	//method 1 is to insert a new student into database
	public void createStudent(String firstName, String lastName, String phone, String address, String CNP, String birthday, int trips, String personsId) {
		Student student = null;
		
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();
			
			//create transaction entities
			student = new Student();
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setPhone(phone);
			student.setAddress(address);
			student.setCNP(CNP);
			student.setBirthday(birthday);
			student.setTrips(trips);
			student.setPersonsId(personsId);
			
			sessionObj.save(student);
			sessionObj.getTransaction().commit();
			
			logger.info("\nStudent created successfully!\n");
			
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
	
	//method 2 is to display the records from the database
	@SuppressWarnings("unchecked")
	public List<Student> displayStudents() {
		List<Student> studentList = null;
		
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			studentList = (List<Student>) sessionObj.createCriteria(Student.class).list();
			logger.info("The students available");			
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
		return studentList;
	}
	
	//Select a student using his CNP
	public void selectStudentByCNP(String CNP) {
	    Student stdObj = null;
	    try {
			//getting session object from session factory
	    	sessionObj = buildSessionFactory().openSession();
	    	//getting transaction object from session object
	    	sessionObj.beginTransaction();
	    	
	    	stdObj = (Student) sessionObj.get(Student.class, CNP);

	    	if(stdObj != null) {
	    		System.out.print("Student found!" + stdObj.toString());
	    	}else {
	    		System.out.println("Student does not exists!");
	    	}
	    	sessionObj.getTransaction().commit();	    	
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
    }
		
	//method 3 is used to update a student in the database table
	public void updateStudent(String firstName, String lastName, String phone, String address, String CNP, String birthday, int trips, String personsID) {
		
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			//creating transaction entity
			Student studentObj = (Student) sessionObj.get(Student.class, CNP);
			studentObj.setFirstName(firstName);
			studentObj.setLastName(lastName);
			studentObj.setPhone(phone);
			studentObj.setAddress(address);
			studentObj.setBirthday(birthday);
			studentObj.setTrips(trips);
			studentObj.setPersonsId(personsID);
			
			//commiting the transactions to the database
			sessionObj.getTransaction().commit();
			logger.info("\nAdmin with id " + CNP + " is successfully updated.");
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
	
	//method 4 is used to delete a student using CNP from the table in database
	public void deleteStudent(String CNP) {
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			Student studentObj = findByStudentCNP(CNP);
			sessionObj.delete(studentObj);
			
			// Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nStudent With Id?= " + CNP + " Is Successfully Deleted From The Database!\n");
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
	
	
	public Student findByStudentCNP(String find_studentCNP) {
        Student findStudentObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            findStudentObj = (Student) sessionObj.load(Student.class, find_studentCNP);
        } catch(Exception sqlException) {
            if(sessionObj.getTransaction() != null) {
                logger.info("\nTransaction Is Being Rolled Back...\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } 
        return findStudentObj;
    }
	
	// Method 5: This Method Is Used To Delete All Students From The Database Table
    public void deleteAllStudents() {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            Query queryObj = sessionObj.createQuery("DELETE FROM Student");
            queryObj.executeUpdate();
             
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nSuccessfully Deleted All Records From The Database Table!\n");
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                logger.info("\nTransaction Is Being Rolled Back...\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }
    
    

}
