package org.example.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class JsonFlattenerTest {

    @Test
    public void testSimpleJson(){
        String json = """
                {
                	"a": 1,
                	"b":false
                }
                """;
        JSONObject jsonObject = getJsonObject(json);
        Assert.assertTrue(jsonObject.keySet().containsAll(Set.of("a","b")),
                "A valid simple JSON should contain all the valid keys");
        Assert.assertEquals(jsonObject.get("a"),1);
        Assert.assertEquals(jsonObject.get("b"),false);
    }

    private JSONObject getJsonObject(String json) {
        String resultJson = JsonFlattener.flattenJson(json);
        JSONObject jsonObject = new JSONObject(resultJson);
        return jsonObject;
    }

    @Test
    public void test2LevelNestedJson(){
        String json = """
                {
                	"b": {
                		"c": 2,
                		"d":true
                	}
                }
                """;
        JSONObject jsonObject = getJsonObject(json);
        Assert.assertTrue(jsonObject.keySet().containsAll(Set.of("b.c","b.d")),
                "A valid 2 level nested JSON should contain all the valid keys");
        Assert.assertEquals(jsonObject.get("b.c"),2);
        Assert.assertEquals(jsonObject.get("b.d"),true);
    }
    @Test
    public void test3LevelNestedJson(){
        String json = """
                {
                	"a": 1,
                	"b": {
                		"c": 2,
                		"d":3,
                		"e":{
                		    "f":false
                		}
                	}
                }
                """;
        JSONObject jsonObject = getJsonObject(json);
        Assert.assertTrue(jsonObject.keySet().containsAll(Set.of("a","b.c","b.d","b.e.f")),
               "A valid 3 level nested JSON should contain all the valid keys");
        Assert.assertEquals(jsonObject.get("a"),1);
        Assert.assertEquals(jsonObject.get("b.c"),2);
        Assert.assertEquals(jsonObject.get("b.d"),3);
        Assert.assertEquals(jsonObject.get("b.e.f"),false);

    }

    @Test(expectedExceptions = JSONException.class)
    public void testInvalidJson(){
        //invalid JSON, must throw JSONException
        String json = """
                <invalid-json>
                """;
        JSONObject jsonObject = getJsonObject(json);
    }
    @Test
    public void testEmptyJson(){
        String json = """
                {
                	
                }
            
                """;
        JSONObject jsonObject = getJsonObject(json);
        Assert.assertTrue(jsonObject.keySet().isEmpty());
    }
}
