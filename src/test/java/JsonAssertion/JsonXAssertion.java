package JsonAssertion;

import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.Test;

public class JsonXAssertion {
	
	/**
	 * Write JSON tests as if you are comparing a string. Under the covers, 
	 * JSONassert converts your string into a JSON object and compares the logical structure and data with the actual JSON. When strict is set to false (recommended),
	 *  it forgives reordering data and extending results (as long as all the expected elements are there), making tests less brittle.
	 *  yaapita-seed-Framework
	 * @throws JSONException 
	 */

	@Test
	public void validateJson() throws JSONException
	{
		
	
		String expected = "{friends:[{id:123,name:\"Corby Page\"},{id:456,name:\"Carter Page\"}]}";
		String actual = "{friends:[{id:123,name:\"Corby Page\"},{id:456,name:\"Carter Page\"}]}";
		
		JSONObject exp=new JSONObject();
		exp.put("name", "Jayanta");
		exp.put("gender", "male");
		
		
		JSONObject act=new JSONObject();
		act.put("gender", "male");
		act.put("name", "Jayanta");
		
		try {
			JSONAssert.assertEquals(expected, actual, false);
			JSONAssert.assertEquals(exp, act, true);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
