package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.json.Json;

import java.io.*;

public class Utils {
    public static void saveEmployeeInfo(String firstname, String lastname, String username, String password) throws IOException, ParseException {
        String file = "./src/test/resources/employees.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(file));
        JSONObject empObj = new JSONObject();
        empObj.put("firstName", firstname);
        empObj.put("lastName", lastname);
        empObj.put("username", username);
        empObj.put("password", password);
        jsonArray.add(empObj);

        FileWriter writer = new FileWriter(file);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();
    }
    public static JSONArray readEmployeeInfo() throws IOException, ParseException {
        String file = "./src/test/resources/employees.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(file));
        return jsonArray;
    }
}
