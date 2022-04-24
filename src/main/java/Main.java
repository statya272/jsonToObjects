import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        System.out.println("> Task :Main.main()");
        for (Employee e :
                list) {
            System.out.println(e);
        }
    }

    private static String readString(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder text = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                text.append(s);
            }
            return text.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Employee> jsonToList(String json) {
        List<Employee> employees = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(json);
            JSONArray jsonObject = (JSONArray) obj;
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for (Object o :
                    jsonObject) {
                employees.add(gson.fromJson(o.toString(), Employee.class));
            }
            return employees;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
