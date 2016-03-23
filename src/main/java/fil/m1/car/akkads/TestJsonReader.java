package fil.m1.car.akkads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestJsonReader {
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
        final JSONParser parser = new JSONParser();
        final Object obj = parser.parse(new FileReader("tree.json"));
        final JSONObject jsonObject = (JSONObject) obj;
        
        final JSONObject tree = (JSONObject) jsonObject.get("tree");
        
        //final 
        
        System.out.println(tree.size());
        
    }

}
