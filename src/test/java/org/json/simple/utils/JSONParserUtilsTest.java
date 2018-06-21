package org.json.simple.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class JSONParserUtilsTest {

	@Test
	public void doASimpleTest() throws Exception {
		
		JSONParserUtils utils = new JSONParserUtils();
		String input = new String(Files.readAllBytes(Paths.get("src/test/resources/simple.json")));
		Assert.assertEquals(Boolean.FALSE,utils.extract(input, "boolval"));
		Assert.assertEquals("bar", utils.extract(input, "foo"));
		Assert.assertEquals(new Long(1),utils.extract(input,"numberval"));
		
	}
	
	@Test
	public void doAMoreComplexTest() throws Exception {
		
		JSONParserUtils utils = new JSONParserUtils();
		String input = new String(Files.readAllBytes(Paths.get("src/test/resources/random.json")));
		JSONArray array = (JSONArray) utils.extract(input, "");
		Assert.assertTrue(array.size() > 0);
		JSONObject firstObject = (JSONObject) utils.extract(input, "0");
		Assert.assertTrue("e06d1ad1-24ad-4a6d-87d1-5a5c318c00dd".equals(firstObject.get("guid")));
		Assert.assertTrue(utils.cardinality(input, "0.tags") == 7);
		
	}
	
	@Test(expected=ParseException.class)
	public void checkExceptionIsThrown() throws ParseException {
		
		JSONParserUtils utils = new JSONParserUtils();
		utils.extract("not_a_json", "random.tag");
		
	}

}
