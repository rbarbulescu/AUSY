package crud.ovp.persistence.dao;

import java.util.List;

import crud.ovp.persistence.model.Admin;

public interface AdminDAO {
	
	void createAdmin(Admin admin);
	void updateAdmin(String adminId, String institution);
	void deleteAdmin(String adminId);
	Admin findByAdminId(String find_adminId);
	List<Admin> displayAdmins(); 
	void checkAdminId(String adminId, String institution);

}
