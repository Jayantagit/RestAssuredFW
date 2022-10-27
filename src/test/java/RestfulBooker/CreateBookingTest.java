package RestfulBooker;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.awaitility.Awaitility;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import POjO.Users;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateBookingTest {

	public Response response = null;

	@Test
	public void gsonTest() {
		String responseval = "{\r\n" + "   \"Members\" : \r\n" + "	[\r\n" + "		{\r\n"
				+ "			\"memberName\": \"Amit\",\r\n" + "		        \"memberId\": 101,\r\n"
				+ "			\"memberAddress\": \r\n" + "			{\r\n" + "				\"PinCode\": 410057,\r\n"
				+ "				\"State\": \"Gurugram\"\r\n" + "			}\r\n" + "		},\r\n" + "		{\r\n"
				+ "			\"memberName\": \"Jayanta\",\r\n" + "	        \"memberId\": 102,\r\n"
				+ "			\"memberAddress\": {\r\n" + "				\"PinCode\": 410057,\r\n"
				+ "				\"State\": \"Delhi\"\r\n" + "			}\r\n" + "		}\r\n" + "	\r\n" + "	]\r\n"
				+ "}";
		Users user = new Users(111, "Jayanta");
		Gson gson = new Gson();
		String jsonVal = gson.toJson(user);
		System.out.println(jsonVal);
		user.setName("Sounak");
		String updatejsonVal = gson.toJson(user);
		Users u = gson.fromJson(updatejsonVal, Users.class);
		System.out.println(u.getName());
	}

	@Test
	public void retryTest() {
		Awaitility.await().atMost(Duration.ofSeconds(20)).pollInterval(Duration.ofSeconds(2)).until(() -> {

			response = CreateBooking.createBooking();
			if (response != null) {
				return true;
			} else {
				return false;
			}

		});

		System.out.println(response.asPrettyString());
	}

	@Test
	public void bookingExtractTest() {
		int bookingID = RestAssured.given().contentType(ContentType.JSON)
				.body("{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				.when().post("https://restful-booker.herokuapp.com/booking").then().extract().jsonPath()
				.getInt("bookingid");

		System.out.println("Booking ID is" + bookingID);
	}

	@Test
	public void memeberTest() {
		String response = "{\r\n" + "   \"Members\" : \r\n" + "	[\r\n" + "		{\r\n"
				+ "			\"memberName\": \"Amit\",\r\n" + "		        \"memberId\": 101,\r\n"
				+ "			\"memberAddress\": \r\n" + "			{\r\n" + "				\"PinCode\": 410057,\r\n"
				+ "				\"State\": \"Gurugram\"\r\n" + "			}\r\n" + "		},\r\n" + "		{\r\n"
				+ "			\"memberName\": \"Jayanta\",\r\n" + "	        \"memberId\": 102,\r\n"
				+ "			\"memberAddress\": {\r\n" + "				\"PinCode\": 410057,\r\n"
				+ "				\"State\": \"Delhi\"\r\n" + "			}\r\n" + "		}\r\n" + "	\r\n" + "	]\r\n"
				+ "}";

		JsonPath js = new JsonPath(response);

		List<Object> responseList = js.getList("Members");

		// System.out.println(responseList);
		for (int i = 0; i < responseList.size(); i++) {

			String state = js.get("Members[" + i + "].memberAddress.State").toString();
			if (state.equals("Delhi")) {
				String id = js.get("Members[" + i + "].memberId").toString();
				System.out.println(id);
				break;
			}

		}
		
		System.out.println("=========================");

		JsonPath js1 = new JsonPath(response);

		List<Map<String, Object>> responseListMap = js.getList("Members");

		// System.out.println(responseList);
		for (int i = 0; i < responseListMap.size(); i++) {
			String memberAddr=responseListMap.get(i).get("memberAddress").toString();
			String stateVal=js1.getMap(memberAddr).get("State").toString();
			if(stateVal.equals("Delhi"))
			{
				String memberID=responseListMap.get(i).get("memberId").toString();
				System.out.println(memberID);
				break;
			}

		}

	}

	@Test
	public void requestTest() {
		RequestSpecBuilder rspec = new RequestSpecBuilder();
		rspec.setBaseUri("https://restful-booker.herokuapp.com/booking");
		rspec.setContentType(ContentType.JSON);
		RequestSpecification rspec_200 = rspec.build();
		RequestSpecification request = RestAssured.given().spec(rspec_200);
		Response res = request.get();
		System.out.println(res.getStatusCode());
	}

}
