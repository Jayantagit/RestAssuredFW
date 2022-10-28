package libapiresthelper;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestResponse {

	Response response;
	Headers headers;
	int statusCode;
	long responseTime;

	public static RestResponse getRestResponse(RequestBuilder RequestBuilder) {
		RestResponse RestResponse = new RestResponse();
		Headers requestHeaders = RequestBuilder.getHeaders();

		RequestSpecBuilder builder = new RequestSpecBuilder();
		if (requestHeaders != null) {
			for (Header header : requestHeaders) {
				builder.addHeader(header.getName(), header.getValue());
			}
		}
		if (RequestBuilder.getAccept() != null) {
			builder.addHeader("accept", RequestBuilder.getAccept());
		}
		if (RequestBuilder.getContentType() != null) {
			builder.addHeader("Content-Type", RequestBuilder.getContentType());
		}
		if (RequestBuilder.getCorrelation_id() != null) {
			builder.addHeader("x-correlation-id", RequestBuilder.getCorrelation_id());
		}

		if (RequestBuilder.getAuth() != null) {
			builder.addHeader("Authorization", RequestBuilder.getAuth());
		}
		if (RequestBuilder.getCookie() != null) {
			builder.addHeader("cookie", RequestBuilder.getCookie());
		}
		if (RequestBuilder.getUrl().contains("https://")) {
			builder.setPort(80);
		} else {
			builder.setPort(443);
		}
		
		RequestSpecification requestSpec=builder.build();
		if(RequestBuilder.getMethod().equalsIgnoreCase("post"))
		{
			Response restResponseObject;
			if(RequestBuilder.getBody()!=null)
			{
				restResponseObject=RestAssured.given().spec(requestSpec)
						.body(RequestBuilder.getBody())
						.when().post(RequestBuilder.getUrl())
						.then().extract().response();
			}
			else if(RequestBuilder.getContentType()=="multipart/form-data")
			{
				
				restResponseObject=RestAssured.given().spec(requestSpec)
						.multiPart("upload", new File(RequestBuilder.getMultiPartfile()))
						.when().post(RequestBuilder.getUrl())
						.then().extract().response();
				
			}
			else
			{
				restResponseObject=RestAssured.given().spec(requestSpec).
						when().post(RequestBuilder.getUrl())
						.then().extract().response();
			}
			
			RestResponse.response=restResponseObject;
			RestResponse.headers=restResponseObject.headers();
			RestResponse.statusCode=restResponseObject.getStatusCode();
			RestResponse.responseTime=restResponseObject.getTime();
		}
		
		else if(RequestBuilder.getMethod().equalsIgnoreCase("delete"))
		{
			Response restResponseObject;
			if(RequestBuilder.getBody()!=null)
			{
				restResponseObject=RestAssured.given().spec(requestSpec)
						.body(RequestBuilder.getBody())
						.when().delete(RequestBuilder.getUrl())
						.then().extract().response();
			}
			
			else
			{
				restResponseObject=RestAssured.given().spec(requestSpec).
						when().delete(RequestBuilder.getUrl())
						.then().extract().response();
			}
			
			RestResponse.response=restResponseObject;
			RestResponse.headers=restResponseObject.headers();
			RestResponse.statusCode=restResponseObject.getStatusCode();
			RestResponse.responseTime=restResponseObject.getTime();
		}
		
		else if(RequestBuilder.getMethod().equalsIgnoreCase("put"))
		{
			Response restResponseObject;
			if(RequestBuilder.getBody()!=null)
			{
				restResponseObject=RestAssured.given().spec(requestSpec)
						.body(RequestBuilder.getBody())
						.when().put(RequestBuilder.getUrl())
						.then().extract().response();
			}
			
			else
			{
				restResponseObject=RestAssured.given().spec(requestSpec).
						when().put(RequestBuilder.getUrl())
						.then().extract().response();
			}
			
			RestResponse.response=restResponseObject;
			RestResponse.headers=restResponseObject.headers();;
			RestResponse.statusCode=restResponseObject.getStatusCode();
			RestResponse.responseTime=restResponseObject.getTime();
		}
		else if(RequestBuilder.getMethod().equalsIgnoreCase("patch"))
		{
			Response restResponseObject;
			if(RequestBuilder.getBody()!=null)
			{
				restResponseObject=RestAssured.given().spec(requestSpec)
						.body(RequestBuilder.getBody())
						.when().patch(RequestBuilder.getUrl())
						.then().extract().response();
			}
			
			else
			{
				restResponseObject=RestAssured.given().spec(requestSpec).
						when().patch(RequestBuilder.getUrl())
						.then().extract().response();
			}
			
			RestResponse.response=restResponseObject;
			RestResponse.headers=restResponseObject.headers();;
			RestResponse.statusCode=restResponseObject.getStatusCode();
			RestResponse.responseTime=restResponseObject.getTime();
		}
		else if(RequestBuilder.getMethod().equalsIgnoreCase("get"))
		{
			Response restResponseObject;
			
				restResponseObject=RestAssured.given().spec(requestSpec)
						.when().get(RequestBuilder.getUrl())
						.then().extract().response();
						
			RestResponse.response=restResponseObject;
			RestResponse.headers=restResponseObject.headers();;
			RestResponse.statusCode=restResponseObject.getStatusCode();
			RestResponse.responseTime=restResponseObject.getTime();
		}
		
		return RestResponse;
	}

	public Response getResponse() {
		return response;
	}

	public Headers getHeaders() {
		return headers;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public long getResponseTime() {
		return responseTime;
	}

}
