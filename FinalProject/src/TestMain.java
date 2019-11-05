import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.person.beans.Person;
import com.person.dao.PersonDAO;

public class TestMain {
	static JdbcTemplate template;
	static Connection conn = null;
	static String databaseName = "ovpdb";
	static String url = "jdbc:mysql://localhost:3306/" + databaseName;

	static String username = "root";
	static String password= "Password@123";
	
	 @Autowired
	static  
    PersonDAO dao;
	
		public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {	
		//Connection to my database
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(url, username, password);
		Person p = new Person();
		p.setEmail("sada");
		p.setUsername("usernameparalel");
		p.setPassword("dasdasdjkbs a");
		p.setTypeofuser("student");
		p.setAdminsId("admin1");	
		
		dao.save(p);
		String sql = "INSERT INTO persons(email, username, password, typeofuser, adminsId) VALUES('" + p.getEmail() + "','" + p.getUsername() + "','" + p.getPassword() + "', '" + p.getTypeofuser() + "', '" + p.getAdminsId() + "')";
		PreparedStatement ps = conn.prepareStatement(sql);
		//ps.executeUpdate();
	
	}

}
