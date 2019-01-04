
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.util.*;
import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_8;

import org.json.*;

public class JsonFormatter {

  public JSONObject JSONChemical(Chemical chemical) {
    JSONObject JSONChemical = new JSONObject();
    JSONChemical.put("substance", chemical.substance());
    JSONChemical.put("cas_nr", chemical.casnr());
    JSONChemical.put("eg_nr", chemical.egnr());
    JSONChemical.put("priority_level", chemical.priolevel());
    JSONChemical.put("criteria", chemical.criteria());
    return JSONChemical;
  }

  public JSONObject JSONMessage(Message message) {
    JSONObject JSONMessage = new JSONObject();
    JSONMessage.put("name", message.name());
    JSONMessage.put("info", message.info());
    return JSONMessage;
  }


  /*public String formatChemical(List<Chemical> chemicals) {
    if (chemicals.isEmpty()) {
      return null;
    } else {
    JSONArray JSONChemical = new JSONArray();
    for (Chemical chemical : chemicals) {
      JSONChemical.put(JSONChemical(chemical));
    }
    return JSONChemical.toString(2);
  }
}

public String formatMessage(List<Message> messages) {
  JSONArray JSONMessage = new JSONArray();
  for (Message message : messages) {
    JSONMessage.put(JSONMessage(message));
  }
  return JSONMessage.toString(2);

}*/

 public JSONArray createMessageArray(List<Message> messages) {
   JSONArray JSONMessage = new JSONArray();
   for (Message message : messages) {
     JSONMessage.put(JSONMessage(message));
   }
   return JSONMessage;
 }

 public JSONArray createChemicalArray(List<Chemical> chemicals) {
   JSONArray JSONChemical = new JSONArray();
   for (Chemical chemical : chemicals) {
     JSONChemical.put(JSONChemical(chemical));
   }
   return JSONChemical;
 }

 public String createJSONString(JSONArray jsonA, JSONArray jsonB) {
   JSONObject jo = new JSONObject();
   jo.put("messages:", jsonA);
   jo.put("chemicals found:", jsonB);
   return jo.toString(2);
 }

}
