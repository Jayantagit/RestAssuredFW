package libapiresthelper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import POjO.UserRole;

public class JsonUtil {

	public static List getUserListfromJson(String path) {
		JsonReader jsonReader = null;
		Gson gson = new Gson();
		try {
			jsonReader = new JsonReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Type userType = new TypeToken<List<UserRole>>() {

		}.getType();
		List<UserRole> users = gson.fromJson(jsonReader, userType);
		return users;

	}

	public static void writeJson(List list, String path) {
		Gson gson = new GsonBuilder().create();

		String jsonString = gson.toJson(list);
		FileWriter writer;
		try {
			writer = new FileWriter(path);
			writer.write(jsonString);
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

	}

}
