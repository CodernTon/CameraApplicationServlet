import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.util.*;
import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_8;

import org.json.*;

public class JsonFormatter {

  //Creates a JSONObject from a Chemical-object.
  public JSONObject JSONChemical(Chemical chemical) {
    JSONObject JSONChemical = new JSONObject();
    JSONChemical.put("substance", chemical.substance());
    JSONChemical.put("cas_nr", chemical.casnr());
    JSONChemical.put("eg_nr", chemical.egnr());
    JSONChemical.put("priority_level", chemical.priolevel());
    JSONChemical.put("criteria", chemical.criteria());
    return JSONChemical;
  }

  //Creates a JSONObject from a message-object.
  public JSONObject JSONMessage(Message message) {
    JSONObject JSONMessage = new JSONObject();
    JSONMessage.put("name", message.name());
    JSONMessage.put("info", message.info());
    return JSONMessage;
  }

  //Creates a JSONArray of JSONMessage-objects
  public JSONArray createMessageArray(List<Message> messages) {
    JSONArray JSONMessageArray = new JSONArray();
    for (Message message : messages) {
      JSONMessageArray.put(JSONMessage(message));
    }
    return JSONMessageArray;
  }

  //Creates a JSONArray of JSONChemical-objects
  public JSONArray createChemicalArray(List<Chemical> chemicals) {
    JSONArray JSONChemicalArray = new JSONArray();
    for (Chemical chemical : chemicals) {
      JSONChemicalArray.put(JSONChemical(chemical));
    }
    return JSONChemicalArray;
  }

  //Creates the main JSONObject that contains the JSONArrays and  makes import junit.framework.TestCase;
  //into a String-object.
  public String createJSONString(JSONArray jsonA, JSONArray jsonB) {
    JSONObject jo = new JSONObject();
    jo.put("messages:", jsonA);
    jo.put("chemicals found:", jsonB);
    return jo.toString(2);
  }
}
