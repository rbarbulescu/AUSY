package crud.ovp.persistence.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import crud.ovp.persistence.dao.StudentDAO;
import crud.ovp.persistence.model.Student;

public class StudentDAOImpl implements StudentDAO{
	
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	//method 1 is to insert a new student into database
	public void createStudent(Student student) {
		
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			sessionObj.beginTransaction();
			
			//create transaction entities			
			sessionObj.persist(student);
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
	
	//method 2 is to display the records from the database
	@SuppressWarnings("unchecked")
	public List<Student> displayStudents() {
		List<Student> studentList = null;
		
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			studentList = (List<Student>) sessionObj.createCriteria(Student.class).list();				
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
		return studentList;
	}
	
	//Select a student using his CNP
	public Student checkStudentByCNP(String CNP) {
	    Student stdObj = null;
	    try {
			//getting session object from session factory
	    	sessionObj = this.sessionFactory.openSession();
	    	//getting transaction object from session object
	    	sessionObj.beginTransaction();
	    	
	    	stdObj = (Student) sessionObj.get(Student.class, CNP);

	    	if(stdObj != null) {
	    		System.out.println("Student found!\n" + stdObj.toString());
	    	}else {
	    		System.out.println("Student does not exists!");
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
	    return stdObj;
    }
		
	//method 3 is used to update a student in the database table
	public void updateStudent(String firstName, String lastName, String phone, String address, String CNP, String birthday, int trips, String personsID) {
		
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
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
	
	//method 4 is used to delete a student using CNP from the table in database
	public void deleteStudent(String CNP) {
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			Student studentObj = findByStudentCNP(CNP);
			sessionObj.delete(studentObj);
			
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
	
	
	public Student findByStudentCNP(String find_studentCNP) {
        Student findStudentObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = this.sessionFactory.openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            findStudentObj = (Student) sessionObj.load(Student.class, find_studentCNP);
        } catch(Exception sqlException) {
            if(sessionObj.getTransaction() != null) {
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
            sessionObj = this.sessionFactory.openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            Query queryObj = sessionObj.createQuery("DELETE FROM Student");
            queryObj.executeUpdate();
             
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
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
