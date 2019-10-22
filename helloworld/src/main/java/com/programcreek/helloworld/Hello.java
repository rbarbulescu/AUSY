package com.programcreek.helloworld;
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.programcreek.helloworld.services.HelloWorldService; 
 
public class Hello {
 
	@SuppressWarnings("resource")
	public static void main(String[] args) {
 
		// loading the definitions from the given XML file
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
 
		HelloWorldService service = (HelloWorldService) context
				.getBean("helloWorldService");
		String message = service.sayHello();
		System.out.println(message);
 
		//set a new name
		service.setName("Spring");
		message = service.sayHello();
		System.out.println(message);
	}
}