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

public class CreateBookingBrokerTest3 {

	CreateBookingResponse cb3 = null;

	@Test(priority = 0)

	public void createBookingTest(ITestContext context) {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		RequestSpecification request = RestAssured.given();
		request.contentType(ContentType.JSON);
		request.body(new File("./src/test/resources/JsonFiles/CreateBooking.json"));

		Response response = request.post("/booking");

		String bookingId = response.jsonPath().getString("bookingid");

		System.out.println(bookingId);

		System.out.println(response.getBody().asString());

		cb3 = new CreateBookingResponse();

		Response res = RestAssured.given().body(cb3).get();
		// System.out.println(res.getBody().asString());

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

		System.out.println(cb1.getBookingid());

		System.out.println(cb1.getBooking().getFirstname());

		int booking_Id = cb1.getBookingid();
		context.setAttribute("id", booking_Id);

		CreateBookingResponse cb = response.as(CreateBookingResponse.class, ObjectMapperType.JACKSON_2);

		System.out.println(cb.getBookingid());
		System.out.println(cb.getBooking().getFirstname());
		/*
		 * 
		 * ObjectMapper mapper=new ObjectMapper(); ObjectNode objectNode=null; try {
		 * objectNode=mapper.readValue(new
		 * File("./src/test/resources/JsonFiles/CreateBooking.json"), ObjectNode.class);
		 * } catch (StreamReadException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (DatabindException e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } ObjectNode
		 * node=mapper.createObjectNode(); node.put("additionalneeds","Lunch");
		 * objectNode.set("additionalneeds", node)
		 * 
		 * RequestSpecification request1=RestAssured.given();
		 * request1.contentType(ContentType.JSON); request1.body(new
		 * File("./src/test/resources/JsonFiles/CreateBooking.json")); Response
		 * resput=request1.put("/booking/"+bookingId);
		 * System.out.println(resput.prettyPrint());;
		 */

	}

	@Test(priority = 1)

	public void updateBookingTest(ITestContext context) {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		System.out.println("===========PUT================");

		int booking_Id = (int) context.getAttribute("id");

		System.out.println(booking_Id);

		System.out.println(cb3.getBooking().getAdditionalneeds());
		/*

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

		RequestSpecification request1 = RestAssured.given();
		request1.contentType(ContentType.JSON);
		request1.header("Cookie", "token=abc123");
		request1.body(jsonUpd);

		Response response1 = request1.put("/booking/" + booking_Id);

		System.out.println(response1.jsonPath().getString("additionalneeds"));*/

		// Assert.assertEquals(response1.jsonPath().getString("additionalneeds"),
		// book.getAdditionalneeds());
	}

}
