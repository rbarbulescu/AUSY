package crud.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DbOperations {
	
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	
	public final static Logger logger = Logger.getLogger(DbOperations.class);
	
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
	
	//method 1 is to insert a new admin into database
	public static void createAdmin(String adminId, String institution) {
		Admin admin = null;
		
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();
			
			//create transaction entities
			admin = new Admin();
			admin.setAdminId(adminId);
			admin.setInstitution(institution);
			
			sessionObj.save(admin);
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
	
	//method 2 is to display the records from the database
	@SuppressWarnings("unchecked")
	public static List<Admin> displayAdmins() {
		List<Admin> adminsList = new ArrayList<Admin>();
		
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			adminsList = sessionObj.createQuery("FROM admins").list();
			logger.info("The admins available");			
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
		return adminsList;
	}
	
	//method 3 is used to update a record in the database table
	public static void updateAdmin(String adminId, String institution) {
		
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			//creating transaction entity
			Admin adminObj = (Admin) sessionObj.get(Admin.class, adminId);
			adminObj.setInstitution(institution);
			
			//commiting the transactions to the database
			sessionObj.getTransaction().commit();
			logger.info("\nAdmin with id " + adminId + " is successfully updated.");
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
	
	//method 4 is used to delete an admin using adminId from the table in db
	public static void deleteAdmin(String adminId) {
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			Admin adminObj = findByAdminId(adminId);
			sessionObj.delete(adminObj);
			
			// Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
            logger.info("\nStudent With Id?= " + adminId + " Is Successfully Deleted From The Database!\n");
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
	
	public static Admin findByAdminId(String find_adminId) {
        Admin findAdminObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            findAdminObj = (Admin) sessionObj.load(Admin.class, find_adminId);
        } catch(Exception sqlException) {
            if(sessionObj.getTransaction() != null) {
                logger.info("\nTransaction Is Being Rolled Back...\n");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } 
        return findAdminObj;
    }
	
	// Method 5: This Method Is Used To Delete All Records From The Database Table
    public static void deleteAllAdmins() {
        try {
            // Getting Session Object From SessionFactory
            sessionObj = buildSessionFactory().openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            Query queryObj = sessionObj.createQuery("DELETE FROM admins");
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
