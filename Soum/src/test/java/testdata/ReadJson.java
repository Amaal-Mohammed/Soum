package testdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;


public class ReadJson {
    public static JSONObject readJson_old(String filename) throws IOException {
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\java\\testdata\\" + filename);
            JSONObject obj = (JSONObject) JSONValue.parse(reader);
            return obj;
        } catch (IOException io) {
            throw new IOException("Can not open file");
        }
    }


    public static Map<String, String> readJson(String filePath) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File("C:\\Soum\\Soum\\src\\test\\java\\testdata\\" + filePath);
            Map<String, String> testData = objectMapper.readValue(file, new TypeReference<Map<String, String>>() {
            });
            return testData;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


}

