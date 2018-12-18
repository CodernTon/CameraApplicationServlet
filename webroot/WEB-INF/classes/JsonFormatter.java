
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

  public String format(List<Chemical> chemicals) {
    JSONArray JSON = new JSONArray();
    for (Chemical chemical : chemicals) {
      JSON.put(JSONChemical(chemical));
    }
    return JSON.toString(2);
  }

//  String JSONString = format(chemicals);
//s§  System.out.println(JSONString);

}
