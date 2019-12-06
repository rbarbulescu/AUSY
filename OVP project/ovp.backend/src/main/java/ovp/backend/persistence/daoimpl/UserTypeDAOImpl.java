package ovp.backend.persistence.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ovp.backend.persistence.dao.UserTypeDAO;
import ovp.backend.persistence.model.User;
import ovp.backend.persistence.model.UserType;

public class UserTypeDAOImpl implements UserTypeDAO {

	static Session sessionObj;

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void createUserType(UserType userType) {
		// TODO Auto-generated method stub
		try {
			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			sessionObj.beginTransaction();

			// create transaction entities
			sessionObj.persist(userType);
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

	@Override
	public void updateUserType(String userId, String userType) {
		// TODO Auto-generated method stub
		try {
			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			// getting transaction object from session object
			sessionObj.beginTransaction();

			// creating transaction entity
			User userObj = (User) sessionObj.get(User.class, userId);

			if (userObj == null) {
				System.out.println("Admin does not exist!");
			} else {
				userObj.setPassword(userType);
			}

			// commiting the transactions to the database
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

	@Override
	public User findUserTypeByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserType> displayUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
