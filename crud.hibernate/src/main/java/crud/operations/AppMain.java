package crud.operations;

import java.util.List;

import org.apache.log4j.Logger;

public class AppMain {
	
	public final static Logger logger = Logger.getLogger(AppMain.class);
	
	public static void main(String[] args) {
		logger.info("CRUD Examples");
		
		logger.info("Create record");
		DbOperations.createAdmin("admin", "ACE");
		DbOperations.createAdmin("admin2222", "ACE");
		
		//read records
		logger.info("Read records");
		List<Admin> admins = DbOperations.displayAdmins();
		if(admins != null && admins.size() > 0) {
			for(Admin adminObj : admins) {
				System.out.println(adminObj);;
			}
		}
		
		logger.info("Update record");
		DbOperations.updateAdmin("admin111", "UMFCV");
		logger.info("Read after update");
		List<Admin> updatedAdmins = DbOperations.displayAdmins();
        if(updatedAdmins != null & updatedAdmins.size() > 0) {
            for(Admin adminObj : updatedAdmins) {
                logger.info(adminObj.toString());
            }
        }
        
        logger.info("\nDelete record\n");
        DbOperations.deleteAdmin("admin111");;
        logger.info("\nRead after delete\n");
        List<Admin> deleteAdminRecord = DbOperations.displayAdmins();
        for(Admin adminObj : deleteAdminRecord) {
            logger.info(adminObj.toString());
        }
		
	}

}
