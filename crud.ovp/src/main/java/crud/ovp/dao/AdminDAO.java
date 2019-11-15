package crud.ovp.dao;

import java.util.List;

import crud.ovp.model.Admin;

public interface AdminDAO {
	
	void createAdmin(String adminId, String institution);
	void updateAdmin(String adminId, String institution);
	void deleteAdmin(String adminId);
	Admin findByAdminId(String find_adminId);
	List<Admin> displayAdmins(); 

}
