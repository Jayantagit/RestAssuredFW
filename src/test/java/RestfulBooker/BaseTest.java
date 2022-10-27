package RestfulBooker;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseTest {
	
	@BeforeClass
	
	public void setUp()
	{
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	}

}
