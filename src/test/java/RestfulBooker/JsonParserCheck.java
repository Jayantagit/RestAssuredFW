package RestfulBooker;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class JsonParserCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String response=
			"{\r\n" + 
			"				\"status\": true,\r\n" + 
			"				\"success\": true,\r\n" + 
			"				\"sceneType\": [\r\n" + 
			"				{\r\n" + 
			"				\"SceneTypeCategory\": \"Ambient\",\r\n" + 
			"				\"SceneTypeCode\": 9,\r\n" + 
			"				\"otherLanguage\": null,\r\n" + 
			"				\"isDisplay\": true,\r\n" + 
			"				\"SceneSubData\": [\r\n" + 
			"				{\r\n" + 
			"				\"SceneId\": 747,\r\n" + 
			"				\"CaptureCount\": 8,\r\n" + 
			"				\"SceneSubTypeCode\": 8,\r\n" + 
			"				\"SceneTypeName\": \"Ambient Display\",\r\n" + 
			"				\"otherLanguage\": null,\r\n" + 
			"				\"childSceneId\": 0,\r\n" + 
			"				\"isDisplay\": true,\r\n" + 
			"				\"defaultCamera\": 0,\r\n" + 
			"				\"inventoryConversion\": 0,\r\n" + 
			"				\"cameraQuality\": 0\r\n" + 
			"				},\r\n" + 
			"				{\r\n" + 
			"				\"SceneId\": 1738,\r\n" + 
			"				\"CaptureCount\": 8,\r\n" + 
			"				\"SceneSubTypeCode\": 20,\r\n" + 
			"				\"SceneTypeName\": \"Ambient Display/Rack\",\r\n" + 
			"				\"otherLanguage\": null,\r\n" + 
			"				\"childSceneId\": 1873,\r\n" + 
			"				\"isDisplay\": true,\r\n" + 
			"				\"defaultCamera\": 0,\r\n" + 
			"				\"inventoryConversion\": 1,\r\n" + 
			"				\"cameraQuality\": 1\r\n" + 
			"				}\r\n" + 
			"				]\r\n" + 
			"				},\r\n" + 
			"				{\r\n" + 
			"				\"SceneTypeCategory\": \"Price POP\",\r\n" + 
			"				\"SceneTypeCode\": 95,\r\n" + 
			"				\"otherLanguage\": null,\r\n" + 
			"				\"isDisplay\": true,\r\n" + 
			"				\"SceneSubData\": [\r\n" + 
			"				{\r\n" + 
			"				\"SceneId\": 1873,\r\n" + 
			"				\"CaptureCount\": 40,\r\n" + 
			"				\"SceneSubTypeCode\": 1,\r\n" + 
			"				\"SceneTypeName\": \"Price POP\",\r\n" + 
			"				\"otherLanguage\": null,\r\n" + 
			"				\"childSceneId\": 0,\r\n" + 
			"				\"isDisplay\": true,\r\n" + 
			"				\"defaultCamera\": 0,\r\n" + 
			"				\"inventoryConversion\": 0,\r\n" + 
			"				\"cameraQuality\": 0\r\n" + 
			"				}\r\n" + 
			"				]\r\n" + 
			"				}\r\n" + 
			"				]\r\n" + 
			"				}";
		
		System.out.println("Response --"+checkKeyInResponse(response,"cameraQuality"));
				

	}
	
	public static boolean checkKeyInResponse(String response,String key)
	{
		JsonParser parser=new JsonParser();
		JsonObject jsonObjet=parser.parse(response).getAsJsonObject();
		
		return jsonObjet.has(key);
		
	}

}
