package POjO;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class RestResponse {

	Response response;
	Headers headers;
	Long responseTime;
	int statusCode;

	public static RestResponse getRestResponse(RequestBuilders requestBuilder) {
		RestResponse restResponse = new RestResponse();
		Headers headers = requestBuilder.getHeaders();

		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

		if (headers != null) {
			for (Header header : headers) {
				requestSpecBuilder.addHeader(header.getName(), header.getValue());
			}
		}

		if (requestBuilder.getContentType() != null) {
			requestSpecBuilder.addHeader("Content-Type", requestBuilder.getContentType());
		}

		RequestSpecification rspec = requestSpecBuilder.build();

		if (requestBuilder.getMethod().equalsIgnoreCase("post")) {
			Response restResponseObject;
			if (requestBuilder.getBody() != null) {
				restResponseObject = given().spec(rspec).body(requestBuilder.getBody()).when()
						.post(requestBuilder.getUrl()).then().extract().response();
			} else {
				restResponseObject = given().spec(rspec).when().post(requestBuilder.getUrl()).then().extract()
						.response();

			}

			restResponse.response = restResponseObject;
			restResponse.headers = restResponseObject.getHeaders();
			restResponse.statusCode = restResponseObject.getStatusCode();
			restResponse.responseTime = restResponseObject.getTime();

		}

		return restResponse;

	}

}
