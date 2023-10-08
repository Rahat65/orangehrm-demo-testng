package utils;

import config.EmployeeModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.json.Json;

import java.io.*;

public class Utils {
    public static void saveEmployeeInfo(EmployeeModel model) throws IOException, ParseException {
        String file = "./src/test/resources/employees.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader(file));
        JSONObject empObj = new JSONObject();
        empObj.put("firstName", model.getFirstname());
        empObj.put("lastName", model.getLastname());
        empObj.put("username", model.getUsertname());
        empObj.put("password", model.getPassword());
        jsonArray.add(empObj);

        FileWriter writer = new FileWriter(file);
        writer.write(jsonArray.toJSONString());
        writer.flush();
        writer.close();
    }
    public static JSONArray readJSONList(String filename) throws IOException, ParseException {
//        String file = "./src/test/resources/employees.json";
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filename));
        return jsonArray;
    }
}
