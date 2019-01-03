
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


  public String formatChemical(List<Chemical> chemicals) {
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
}
