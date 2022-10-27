package RestfulBooker;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class JsonTraverseTest {
	
	
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

		 System.out.println(responseList);
		for (int i = 0; i < responseList.size(); i++) {

			String state = js.get("Members[" + i + "].memberAddress.State").toString();
			if (state.equals("Delhi")) {
				String id = js.get("Members[" + i + "].memberId").toString();
				System.out.println(id);
				break;
			}

		}
		
		System.out.println("=========================");

		

		List<Map<String,Object>> responseListMap = js.getList("Members");

		 System.out.println(responseListMap);
		
		for (int j = 0; j < responseListMap.size(); j++) {
			String stateVal=js.getString("Members[" + j + "].memberAddress.State");
			if(stateVal.equals("Gurugram"))
			{
				String ID=js.getString("Members["+j+"].memberName");
				System.out.println(ID);
				break;
			}
		

		}

	}


}
