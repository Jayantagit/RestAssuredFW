package RestfulBooker;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import POjO.AuthDetails;
import Utitility.RestAssuredUtility;
import io.restassured.response.Response;

public class GetTokenDD {

	@DataProvider
	public Object[][] getTokenData() {
		return new Object[][] {

				{ "admin", "password123" }, { "admin", "password123" }

		};

	}

	@Test(dataProvider = "getTokenData")
	public void getAccessToken(String userName, String password) {

		AuthDetails auth = new AuthDetails(userName, password);

		Response response = RestAssuredUtility.post("https://restful-booker.herokuapp.com", "/auth", auth);

		String token = response.jsonPath().get("token");

		Assert.assertNotNull(token);

	}

}
