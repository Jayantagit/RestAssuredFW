package crud;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MapUsage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, String> payload = new HashMap<String, String>();
		payload.put("id","101");
		payload.put("name","Jayanta");
		
		Response response=
				RestAssured.given().log().all().body(payload).get().then().extract().response();
		
		System.out.println(response.prettyPrint());
		

	}

}
