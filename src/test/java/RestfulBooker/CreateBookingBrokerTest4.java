package RestfulBooker;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POjO.CreateBookingResponse;
import POjO.booking;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateBookingBrokerTest4 {

	@Test(priority = 0)

	public void createBookingTest() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		RequestSpecification request = RestAssured.given();
		request.contentType(ContentType.JSON);
		request.body(new File("./src/test/resources/JsonFiles/CreateBooking.json"));

		Response response = request.post("/booking");
		
		

		String bookingId = response.jsonPath().getString("bookingid");

		System.out.println(bookingId);

		System.out.println(response.getBody().asString());

		ObjectMapper mapper = new ObjectMapper();
		CreateBookingResponse cb1 = null;
		try {
			cb1 = mapper.readValue(response.getBody().asString(), CreateBookingResponse.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int booking_Id = cb1.getBookingid();
		
		System.out.println("==================PUT Call=======================");

		booking booking=cb1.getBooking();
		booking.setAdditionalneeds("Dinner");

		ObjectMapper mapperUpd = new ObjectMapper();
		String jsonUpd = null;

		try {
			jsonUpd = mapperUpd.writeValueAsString(booking);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(jsonUpd);

		RequestSpecification request1 = RestAssured.given().log().all();
		request1.header("Cookie", "token=abc123");
		request1.header("Accept", "application/json");
		request1.header("Content-Type", "application/json");
		request1.body(jsonUpd);

		Response response1 = request1.put("/booking/" + booking_Id);
		
		System.out.println("==================PUT Call Response=======================");

		System.out.println(response1.asString());
		
		//Assert.assertEquals(booking.getAdditionalneeds(),"Dinner" );
		
		//JsonPath js=JsonPath.from(response1.asString());
		
		//System.out.println("Value is"+js.getMap("$.bookingdates"));

	}

}
