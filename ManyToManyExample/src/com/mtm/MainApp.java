package com.mtm;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mtm.model.Laptop;
import com.mtm.model.Student;

@SuppressWarnings("deprecation")
public class MainApp {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		Configuration config = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
		ServiceRegistry registry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();
		
		session.beginTransaction();
		
		Student s = new Student();
		s.setRollNo(101);
		s.setName("Radu");
		s.setMarks(90);
		
		Student s2 = new Student();
		s2.setRollNo(102);
		s2.setName("Razvan");
		s2.setMarks(85);
		
		Student s3 = new Student();
		s3.setRollNo(103);
		s3.setName("Mihai");
		s3.setMarks(60);
				
		Laptop laptop = new Laptop();
		laptop.setlId(1);
		laptop.setlName("Dell");
		laptop.getStudent().add(s);
		s.getLaptop().add(laptop);
		s2.getLaptop().add(laptop);
		
		Laptop laptop2 = new Laptop();
		laptop2.setlId(2);
		laptop2.setlName("HP");
		laptop2.getStudent().add(s2);
				
		session.save(laptop);
		session.save(s);
		session.save(laptop2);
		session.save(s2);
		session.save(s3);
			
		String hql ="SELECT FROM laptop_student";		
		Query query = session.createQuery(hql);

		List<?> result = null;
		result = query.list();
		
		if(result != null && result.size() > 0) {
			for(Object lsObj : result) {
				System.out.println(lsObj.toString());
			}
		} else {
			System.out.println("No result!!!");
		}
		
		
		session.getTransaction().commit();
	}

}
