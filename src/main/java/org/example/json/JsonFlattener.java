package org.example.json;

import org.json.JSONObject;

import java.util.Optional;

public class JsonFlattener {
    public static final String DELIMITER = ".";

    public static String flattenJson(String json) {
        JSONObject result = new JSONObject();
        String parentKey = ""; //parent key for nested JSON
        return flattenJson(json, parentKey, result);
    }

    private static String flattenJson(String json, String parentKey, JSONObject result) {
        JSONObject jsonObject = new JSONObject(json);
        for (String key : jsonObject.keySet()) {
            Optional<JSONObject> optionalJSONObject = Optional.ofNullable(jsonObject.optJSONObject(key));
            if (optionalJSONObject.isPresent()) {
                //recurse for nested JSON
                flattenJson(optionalJSONObject.get().toString(), parentKey.concat(key), result);
            } else {
                //Add terminal elements to result JSON, construct nested key by delimiting with "."
                result.put(String.join(DELIMITER, (parentKey.concat(key)).split("")), jsonObject.get(key));
            }
        }
        return result.toString();
    }
}
