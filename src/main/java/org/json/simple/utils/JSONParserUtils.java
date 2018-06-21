package org.json.simple.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class attempts to simplify parsing and extraction of data from JSON documents by going to the requested element directly.
 * The hierarchy in this case describes from top to bottom how the parser should extract the requested object from the json document.
 * @author Cristi Bulugu
 *
 */
public class JSONParserUtils {

	private JSONParser jsonParser;
	
	public JSONParserUtils() {
		this.jsonParser = new JSONParser();
	}
	
	private JSONObject parseJsonObject(String json) throws ParseException {
		return (JSONObject) jsonParser.parse(json);
	}
	
	private JSONArray parseJsonArray(String json) throws ParseException {
		return (JSONArray) jsonParser.parse(json);
	}
	
	private Object parseJson(String json) throws ParseException {
		Object o = new Object();
		try {
			o = parseJsonObject(json);
		} catch (ClassCastException e) {
			o = parseJsonArray(json);
		}
		return o;
	}
	
	private String getJsonString(Object jsonObjectOrArray) throws ParseException {
		if (jsonObjectOrArray instanceof JSONObject) {
			return ((JSONObject) jsonObjectOrArray).toJSONString();
		}
		if(jsonObjectOrArray instanceof JSONArray) {
			return ((JSONArray) jsonObjectOrArray).toJSONString();
		}
		return null;
	}
	
	private Object getTag(String json, String tag) throws ParseException {
		Object o = new Object();
		if("".equals(tag)) {
			return parseJson(json);
		}
		o = parseJson(json);
		if(o instanceof JSONObject) {
			o = ((JSONObject) o).get(tag);
			return o;
		}
		if(o instanceof JSONArray) {
			o = ((JSONArray) o).get(Integer.parseInt(tag));
			return o;
		}
		return o;
	}
	
	/**
	 * This method will extract the value indicated in the hierarchy (ex: "foo.bar" or "somelist.2.somefield"
	 * @param json
	 * @param hierarchy 
	 * @return
	 * @throws ParseException
	 */
	public Object extract(String jsonString, String hierarchy) throws ParseException {
		Object o = parseJson(jsonString);
		String[] elements = hierarchy.split("\\.");
		for(int i = 0; i < elements.length; i++) {
			String tag = elements[i];
			o = getTag(getJsonString(o), tag);
		}
		return o;
	}
	
	/**
	 * This method will count how many values are under the indicated hierarchy.
	 * @param jsonObject
	 * @param hierarchy
	 * @return
	 * @throws ParseException
	 */
	public int cardinality(String jsonString, String hierarchy) throws ParseException {
		JSONArray array = (JSONArray) extract(jsonString, hierarchy);
		return array.size();
	}

}