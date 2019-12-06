package ovp.backend.persistence.dao;

import java.util.List;

import ovp.backend.persistence.model.User;

public interface UserDAO {

	void createUser(User user);
	void updateUser(String userName, String password);
	void deleteUser(String userName);
	User loginCheck(String userName, String password);
	User findUserByUserName(String userName);
	List<User> displayUsers();
	
	
}
