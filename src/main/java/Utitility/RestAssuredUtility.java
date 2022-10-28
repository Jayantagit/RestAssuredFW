package Utitility;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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

	public static Response post(String host, Map<String, String> headers, String request, String baseURL,
			String expectedCode) {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = host;
		RequestSpecification requestSpecification = RestAssured.given().log().all();
		requestSpecification.headers(headers);
		requestSpecification.body(request);
		Response response = requestSpecification.post(baseURL);

		return response;
	}

	public static Response put(String host, Map<String, String> headers, String request, String baseURL,
			String expectedCode) {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = host;
		RequestSpecification requestSpecification = RestAssured.given().log().all();
		requestSpecification.headers(headers);
		requestSpecification.body(request);
		Response response = requestSpecification.put(baseURL);

		return response;
	}

	public static Response get(String host, Map<String, String> headers, String baseURL, String expectedCode) {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = host;
		RequestSpecification requestSpecification = RestAssured.given().log().all();
		requestSpecification.headers(headers);

		Response response = requestSpecification.get(baseURL);

		return response;
	}

	public static Response delete(String host, Map<String, String> headers, String baseURL, String expectedCode) {
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = host;
		RequestSpecification requestSpecification = RestAssured.given().log().all();
		requestSpecification.headers(headers);

		Response response = requestSpecification.delete(baseURL);

		return response;
	}

}
