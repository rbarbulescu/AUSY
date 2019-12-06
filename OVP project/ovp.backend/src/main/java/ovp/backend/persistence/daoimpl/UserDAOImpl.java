package ovp.backend.persistence.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ovp.backend.persistence.dao.UserDAO;
import ovp.backend.persistence.model.User;

public class UserDAOImpl implements UserDAO {

	static Session sessionObj;

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void createUser(User user) {
		try {
			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			sessionObj.beginTransaction();

			// create transaction entities
			sessionObj.persist(user);
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

	public void updateUser(String userName, String password) {
		try {
			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			// getting transaction object from session object
			sessionObj.beginTransaction();

			// creating transaction entity
			User userObj = (User) sessionObj.get(User.class, userName);

			if (userObj == null) {
				System.out.println("Admin does not exist!");
			} else {
				userObj.setPassword(password);
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

	public void deleteUser(String userName) {
		try {
			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			// getting transaction object from session object
			sessionObj.beginTransaction();

			User userObj = findUserByUserName(userName);
			sessionObj.delete(userObj);

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

	public User findUserByUserName(String userName) {
		User findUserObj = null;
		try {
			// Getting Session Object From SessionFactory
			sessionObj = this.sessionFactory.openSession();
			// Getting Transaction Object From Session Object
			sessionObj.beginTransaction();

			findUserObj = (User) sessionObj.load(User.class, userName);
		} catch (Exception e) {
			if (sessionObj.getTransaction() != null) {
				sessionObj.getTransaction().rollback();
			}
			e.printStackTrace();
		}

		return findUserObj;
	}

	public User loginCheck(String userName, String password) {
		User userObj = null;

		try {
			System.out.println("finding user");

			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			// getting transaction object from session object
			sessionObj.beginTransaction();

			userObj = findUserByUserName(userName);
			System.out.println("Start search");
			System.out.println(userObj);
			if (userObj.getUserName().equals(userName) && userObj != null) {

				System.out.println("Correct username");
				System.out.println(userObj.toString());

				if (userObj.getPassword().equals(password)) {
					System.out.println("Well done!");
					return userObj;
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
			if (sessionObj.getTransaction() != null) {
				sessionObj.getTransaction().rollback();
			}
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
		return userObj;
	}

	@SuppressWarnings("unchecked")
	public List<User> displayUsers() {

		List<User> usersList = null;

		try {
			// getting session object from session factory
			sessionObj = this.sessionFactory.openSession();
			// getting transaction object from session object
			sessionObj.beginTransaction();
			usersList = (List<User>) sessionObj.createCriteria(User.class).list();
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
		return usersList;
	}

}
