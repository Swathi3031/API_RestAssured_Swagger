package Utilities;


import org.json.simple.JSONObject;
import Utilities.JsonReader;

public class jsonutils {
    public static String requestBodyFromJson(String filePath) {
        JSONObject jsonObject = JsonReader.getJsonObject(filePath);
        return jsonObject != null ? jsonObject.toJSONString() : "{}";
    }
}

