package Utitility;

import static libapiresthelper.RestResponse.getRestResponse;

import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import libapiresthelper.RequestBuilder;
import libapiresthelper.RestResponse;

public class YaapitaUtil {

	public static RestResponse restResponseWithRetry(String method, String url, String auth, String body,
			String expectedStatusCode, String serviceName, String corr_id, Headers headers, int numReties,
			int RetryWaitTime) {
		RestAssured.useRelaxedHTTPSValidation();
		int retries = 0;

		RestResponse response;
		RequestBuilder builder = new RequestBuilder(method, corr_id, url, auth, body, headers);

		do {
			if (retries > 0)
				try {
					TimeUnit.SECONDS.sleep(RetryWaitTime);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			response = getRestResponse(builder);
			retries++;
		} while ((retries <= numReties) && !String.valueOf(response.getStatusCode()).equals(expectedStatusCode));
		return response;
	}

}
