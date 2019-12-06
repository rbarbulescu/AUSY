package crud.ovp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;
public class Admin {
	
	//private static final String GET_ADMINS_ENDPOINT_URL = "http://localhost:8090/crud.ovp/rest/admins";
	private static final String GET_ADMIN_ENDPOINT_URL = "http://localhost:8090/crud.ovp/rest/admins/";
	
	public static void main(String[] args) {
		getAdmin();
	}
	
	private static void getAdmin() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("adminId","100");

		RestTemplate restTemplate = new RestTemplate();
		Admin result = restTemplate.getForObject(GET_ADMIN_ENDPOINT_URL + "100", Admin.class, params);

		System.out.println(result);
	}

}
