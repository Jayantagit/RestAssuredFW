package RestfulBooker;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import POjO.CreateBookingResponse;
import POjO.booking;
import Utitility.TokenUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateBookingBrokerTest8 extends BaseTest {

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

		cb1.getBooking().setTotalprice(2986);
				
		booking bookUpd=cb1.getBooking();

		

		Gson mapperUpd = new GsonBuilder().create();
		String jsonUpd = null;

		jsonUpd = mapperUpd.toJson(bookUpd);

		System.out.println(jsonUpd);
		String Token = (String) TokenUtil.getAccessToken().get("token");

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("Accept", "application/json");
		headers.put("Content_type", "application/json");
		//headers.put("cookie", Token);

		RequestSpecification request1 = RestAssured.given().log().all();
		request1.headers(headers);
		request1.body(jsonUpd);

		request1.pathParam("id", booking_Id);

		Response response1 = request1.log().all().put("/booking/{id}").andReturn();

		System.out.println(response1.getStatusCode());

		System.out.println("PUT Response==>" + response1.asString());
	}

}
