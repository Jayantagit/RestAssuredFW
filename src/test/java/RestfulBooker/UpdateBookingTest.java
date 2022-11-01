package RestfulBooker;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateBookingTest extends BaseTest  {
	
	@Test
	
	public void UpdateBookingTest()
	{
		RequestSpecification request = RestAssured.given();
		request.contentType(ContentType.JSON);
		request.accept(ContentType.JSON);
		request.body(new File("./src/test/resources/JsonFiles/UpdateBooking.json"));

		Response response = request.put("/7755");

		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
	}

}
