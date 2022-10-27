package RestfulBooker;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class GetToken {

	@Test
	public void getAccessToken()
	{
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		RequestSpecBuilder rspec=new RequestSpecBuilder();
		rspec.setAccept(ContentType.JSON);
		rspec.setContentType(ContentType.JSON);
		
		RequestSpecification rs=rspec.build();
		
		String tokenID=
		RestAssured.given().log().all().spec(rs).body(new File("./src/test/resources/JsonFiles/CreateToken.json"))
		.when().log().all()
		.post("/auth")
		.then().extract().path("token");
		
		System.out.println("Token ID .."+tokenID);
		
		Assert.assertNotNull(tokenID);
		
	}

}
