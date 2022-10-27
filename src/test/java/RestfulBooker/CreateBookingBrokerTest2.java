package RestfulBooker;

import java.io.File;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POjO.CreateBookingResponse;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateBookingBrokerTest2 {

	@Test

	public void createBookingTest() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		RequestSpecification request = RestAssured.given();
		request.contentType(ContentType.JSON);
		request.body(new File("./src/test/resources/JsonFiles/CreateBooking.json"));

		Response response = request.post("/booking");

		System.out.println(response.getBody().asString());

		String bookingId = response.path("bookingid").toString();

		System.out.println(bookingId);

		CreateBookingResponse cbmapper = response.getBody().as(CreateBookingResponse.class, ObjectMapperType.JACKSON_2);

		CreateBookingResponse cb = response.getBody().as(new TypeRef<CreateBookingResponse>() {
		});

		System.out.println(cb.toString());

		System.out.println(cbmapper.getBooking().getAdditionalneeds());

		System.out.println("====================");

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
		System.out.println("====================PUT========================");

		cbmapper.getBooking().setAdditionalneeds("Lunch");
		RestAssured.given().log().all().contentType(ContentType.JSON)
				.body(new File("./src/test/resources/JsonFiles/CreateBooking.json")).when().log().all()
				.put("/booking/" + bookingId).then().log().all().assertThat().statusCode(200);

	}

}
