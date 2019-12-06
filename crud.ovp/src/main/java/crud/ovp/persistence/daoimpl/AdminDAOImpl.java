package crud.ovp.persistence.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import crud.ovp.persistence.dao.AdminDAO;
import crud.ovp.persistence.model.Admin;

public class AdminDAOImpl implements AdminDAO{
	
	static Session sessionObj;
	
	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	//method 1 is to insert a new admin into database
	public void createAdmin(Admin admin) {
		
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			sessionObj.beginTransaction();
			
			//create transaction entities
			sessionObj.persist(admin);
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
	public List<Admin> displayAdmins() {
		//List<Admin> adminsList = new ArrayList<Admin>();
		List<Admin> adminsList = null;
		
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			adminsList = (List<Admin>) sessionObj.createCriteria(Admin.class).list();
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
		return adminsList;
	}
	
	//method 3 is used to update a record in the database table
	public void updateAdmin(String adminId, String institution) {
	
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();

			//creating transaction entity
			Admin adminObj = (Admin) sessionObj.get(Admin.class, adminId);
			
			if(adminObj == null) {
//				Admin admin = new Admin();				
//				admin.setAdminId(adminId);
//				admin.setInstitution(institution);
//				createAdmin(admin);
				
				System.out.println("Admin does not exist!");
			} else {
				adminObj.setInstitution(institution);				
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
	
	//method 4 is used to delete an admin using adminId from the table in db
	public void deleteAdmin(String adminId) {
		try {
			//getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			//getting transaction object from session object
			sessionObj.beginTransaction();
			
			Admin adminObj = findByAdminId(adminId);
			sessionObj.delete(adminObj);
			
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
	
	public Admin findByAdminId(String adminId) {
        Admin findAdminObj = null;
        try {
            // Getting Session Object From SessionFactory
            sessionObj = this.sessionFactory.openSession();
            // Getting Transaction Object From Session Object
            sessionObj.beginTransaction();
 
            findAdminObj = (Admin) sessionObj.load(Admin.class, adminId);
        } catch(Exception sqlException) {
            if(sessionObj.getTransaction() != null) {
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } 
        return findAdminObj;
    }
	
	//check if an admin exists in the database
	public void checkAdminId(String adminId, String institution) {
	    
	    
	    try {
			//getting session object from session factory
	    	sessionObj = this.sessionFactory.openSession();
	    	//getting transaction object from session object
	    	sessionObj.beginTransaction();
	    	Admin adminObj = null;	    	
	    	adminObj = (Admin) sessionObj.get(Admin.class, adminId);
	    	if(adminObj != null) {
	    		System.out.println("Admin found!\n" + adminObj.toString());
	    	}else {
	    		Admin admin = new Admin();
	    		admin.setAdminId(adminId);
	    		admin.setInstitution(institution);
	    		
	    		createAdmin(admin);
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
    }
	
}
