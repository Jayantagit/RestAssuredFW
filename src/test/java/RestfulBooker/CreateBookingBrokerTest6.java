package RestfulBooker;

import java.io.File;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POjO.CreateBookingResponse;
import POjO.booking;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateBookingBrokerTest6 extends BaseTest {

	CreateBookingResponse cb1 = null;

	@Test(priority = 0)

	public void createBookingTest(ITestContext context) {
		

		RequestSpecification request = RestAssured.given();
		request.contentType(ContentType.JSON);
		request.body(new File("./src/test/resources/JsonFiles/CreateBooking.json"));

		Response response = request.post("/booking");

		String bookingId = response.jsonPath().getString("bookingid");

		System.out.println(bookingId);

		System.out.println(response.getBody().asString());

		cb1 = new CreateBookingResponse();

		Response res = RestAssured.given().body(cb1).get();
		

		ObjectMapper mapper = new ObjectMapper();
		
		try {
			cb1 = mapper.readValue(response.getBody().asString(), CreateBookingResponse.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(cb1.getBookingid());

		System.out.println(cb1.getBooking().getFirstname());

		int booking_Id = cb1.getBookingid();
		context.setAttribute("id", booking_Id);

		CreateBookingResponse cb = response.as(CreateBookingResponse.class, ObjectMapperType.JACKSON_2);

		System.out.println(cb.getBookingid());
		System.out.println(cb.getBooking().getFirstname());
		
	}

	@Test(priority = 1)

	public void updateBookingTest(ITestContext context) {
		

		System.out.println("===========PUT================");

		int booking_Id = (int) context.getAttribute("id");

		System.out.println(booking_Id);

		System.out.println(cb1.getBooking().getAdditionalneeds());
		booking bookUpd= cb1.getBooking();

		bookUpd.setAdditionalneeds("Lunch");

		ObjectMapper mapperUpd = new ObjectMapper();
		String jsonUpd = null;

		try {
			jsonUpd = mapperUpd.writeValueAsString(bookUpd);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(jsonUpd);

		RequestSpecification request1 = RestAssured.given().log().all();
		request1.contentType(ContentType.JSON);
		request1.header("Cookie", "token=abc123");
		request1.body(jsonUpd);
		request1.pathParam("id",booking_Id);

		Response response1 = request1.put("/booking/{id}");
		
		System.out.println(response1.getSessionId());

		System.out.println(response1.jsonPath().getString("additionalneeds"));
	}

}
