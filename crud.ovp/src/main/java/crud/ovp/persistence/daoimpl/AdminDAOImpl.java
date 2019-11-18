package crud.ovp.persistence.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import crud.ovp.model.Admin;
import crud.ovp.persistence.dao.AdminDAO;

public class AdminDAOImpl implements AdminDAO{
	
	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	
	public final static Logger logger = Logger.getLogger(AdminDAOImpl.class);
	
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
	public void createAdmin(String adminId, String institution) {
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
	public List<Admin> displayAdmins() {
		//List<Admin> adminsList = new ArrayList<Admin>();
		List<Admin> adminsList = null;
		
		try {
			//getting session object from session factory
			sessionObj = buildSessionFactory().openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			adminsList = (List<Admin>) sessionObj.createCriteria(Admin.class).list();
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
	public void updateAdmin(String adminId, String institution) {
		
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
	public void deleteAdmin(String adminId) {
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
	
	public Admin findByAdminId(String find_adminId) {
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
	
}
