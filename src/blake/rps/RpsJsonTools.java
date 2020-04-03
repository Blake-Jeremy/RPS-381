package blake.rps;
/*******************************************************************
 *  RpsJsonTools class
 *  Description:  This is where all of the Json interactions are
 *  for the export of all of the results.
 *******************************************************************/

// Imported Libraries
import com.google.gson.*;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class RpsJsonTools {

    public static JsonArray jArray = new JsonArray();

    public static String toPrettyFormatObject(String jsonString)
    {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);
        return prettyJson;
    }

    public static String toPrettyFormatArray(String jsonString)
    {
        JsonParser parser = new JsonParser();
        JsonArray json = parser.parse(jsonString).getAsJsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);
        return prettyJson;
    }

    public static void addToJsonArray(String counter, String user, String comp, String userScore, String compScore, String result)
    {
        // build object and output to json
        Gson gsonObj = new Gson();
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("round", counter);
        inputMap.put("user", user);
        inputMap.put("comp", comp);
        inputMap.put("userScore", userScore);
        inputMap.put("compScore", compScore);
        inputMap.put("result", result);
        // convert map to JSON String and add to Array
        String jsonStr = gsonObj.toJson(inputMap);
        jArray.add(jsonStr);
        //System.out.println(jArray);
        //System.out.println(toPrettyFormat(jArray.toString()));
        //System.out.println(RpsJsonTools.toPrettyFormatArray(jArray.toString()));
    }

    public static void saveToFile() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = Files.newBufferedWriter(Paths.get("results.json"));
            gson.toJson(jArray, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
