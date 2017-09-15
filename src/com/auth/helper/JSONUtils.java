package com.honda.vmc.utc.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author Prakash.Gour
 * 
 */
public class JSONUtils {
	/**
	 * 
	 * @param jsonfile
	 * @return JSONObject
	 * 
	 */
	//Read JSON file and return JSONObject
	public static JSONObject jsonReader(String jsonfile) {
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(jsonfile));
		} catch (IOException | ParseException e) {

			e.printStackTrace();
		}
		JSONObject jsonObject = (JSONObject) obj;
		return jsonObject;
	}
	/**
	 * 
	 * @param json
	 * @throws IOException
	 */
	//Write JSON into  file
	public static void jsonWriter(JSONObject json) throws IOException {
		
			FileWriter wr = new FileWriter("D:\\Users\\prakash.gour\\workspace\\Test_context\\Data\\com\\vmc\\json\\write.json");
			wr.write(json.toString());
			wr.flush();
			wr.close();
		}
	/**
	 * 
	 * @param map
	 * @return JSONObject
	 */
	//  Map and to JSONObject
	public static JSONObject mapToJson(Map map) {
		JSONObject jsonObj = new JSONObject();
		Iterator itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry entry = (Map.Entry) itr.next();
			jsonObj.put(entry.getKey(), entry.getValue());

		}
		
		return jsonObj;
	}
	/**
	 * 
	 * @param jsonFile
	 * @return Map<String, Object>
	 * 
	 */
	// JSON file data into Map
	public static Map<String, Object> jsonToMap(String jsonFile) throws FileNotFoundException, IOException, ParseException {
		if (!jsonFile.equals(null)) {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(jsonFile));
			JSONObject json = (JSONObject) obj;
			return JSONUtils.jsonToMap(json);
		}
		return null;
	}
/**
 * 
 * @param JSONObject
 * @return Map<String, Object>
 */
	// JSONObject to Map
	public static Map<String, Object> jsonToMap(JSONObject json)
			throws FileNotFoundException, IOException, ParseException {

		Map<String, Object> map = new HashMap<String, Object>();
		if (json != JsonObject.NULL) {
			map = toMap(json);
		}
		return map;
	}

	public static Map<String, Object> toMap(JSONObject object) throws JsonException {
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<String> keysItr = object.keySet().iterator();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if (value instanceof JsonArray) {
				value = toList((JsonArray) value);
			}

			else if (value instanceof JsonObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}
/**
 * 
 * @param JsonArray
 * @return List<Object> 
 */
	// JsonArray to List
	public static List<Object> toList(JsonArray array) {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.size(); i++) {
			Object value = array.get(i);
			if (value instanceof JsonArray) {
				value = toList((JsonArray) value);
			} else if (value instanceof JsonObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

}
