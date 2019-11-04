package com.javatpoint.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.javatpoint.beans.Persons;

public class PersonDAO {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(Persons p) {
		String sql = "INSERT INTO persons(email, username, password, typeofuser, adminsId) VALUES('" + p.getEmail() + "','" + p.getUsername() + "','" + p.getPassword() + "', '" + p.getTypeofuser() + "', '" + p.getAdminsId() + "')";
		return template.update(sql);
	}

	public int update(Persons p) {
		String sql = "UPDATE persons SET email='" + p.getEmail() + "', password=" + p.getPassword() + ",typeofuser='" + p.getTypeofuser() + "' WHERE username=" + p.getUsername() + "'";
		return template.update(sql);
	}

	public int delete(int id) {
		String sql = "DELETE FROM persons WHERE id=" + id + "";
		return template.update(sql);
	}

	public Persons getPersonById(int id) {
		String sql = "SELECT * FROM persons WHERE id=?";
		return template.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Persons>(Persons.class));
	}

	public List<Persons> getPersons() {
		return template.query("SELECT * FROM ovpdb.persons", new RowMapper<Persons>() {
			public Persons mapRow(ResultSet rs, int row) throws SQLException {
				Persons p = new Persons();
				p.setId(rs.getLong(1));
				p.setEmail(rs.getString(2));
				p.setUsername(rs.getString(3));
				p.setPassword(rs.getString(4));
				p.setTypeofuser(rs.getString(5));
				p.setAdminsId(rs.getString(6));
				return p;
			}
		});
	}
}