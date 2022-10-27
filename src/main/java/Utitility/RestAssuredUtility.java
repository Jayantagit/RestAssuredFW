package Utitility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssuredUtility {

	public static Response post(String host, String url, Object obj) {
		RestAssured.baseURI = host;
		return RestAssured.given().log().all().contentType(ContentType.JSON).body(obj).when().log().all().post(url)
				.then().log().all().extract().response();
	}

	public static String getSerializedJSON(Object body) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;

	}

}
