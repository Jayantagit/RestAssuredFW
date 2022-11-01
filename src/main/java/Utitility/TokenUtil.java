package Utitility;

import java.io.File;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class TokenUtil {

	public static Map<Object, Object> getAccessToken() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		RequestSpecBuilder rspec = new RequestSpecBuilder();
		rspec.setAccept(ContentType.JSON);
		rspec.setContentType(ContentType.JSON);

		RequestSpecification rs = rspec.build();

		Map<Object, Object> appToken = RestAssured.given().log().all().spec(rs)
				.body(new File("./src/test/resources/JsonFiles/CreateToken.json")).when().log().all().post("/auth")
				.then().extract().jsonPath().getMap("");

		return appToken;

	}

}
