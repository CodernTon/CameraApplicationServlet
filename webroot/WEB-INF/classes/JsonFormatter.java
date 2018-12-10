
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.util.*;
import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.sql.*;

import org.json.*;

public class JsonFormatter {
  public static void main(String [] args) {

    try{
      Class.forName("org.sqlite.JDBC");
    }catch(ClassNotFoundException cnfe){
      System.err.println("Could not load driver: "+cnfe.getMessage());
    }

    String chemicalArg = args[0];
    List<Chemical> chemicals = new ArrayList<>();

    try{
      Connection con = DriverManager.getConnection("jdbc:sqlite:kemi.db");
      Statement stm  = con.createStatement();
      StringBuilder sb = new StringBuilder();
      sb.append("SELECT * from chemical WHERE name='");
      sb.append(chemicalArg);
      sb.append("';");
      String sql = sb.toString();
      System.out.println(sql);
      ResultSet rs   = stm.executeQuery(sql);
      while(rs.next()){
        Chemical chem = new Chemical(rs.getString("name"), rs.getString("chemical_group"));
        chemicals.add(chem);
      }
    }catch(SQLException sqle)  {
      System.out.println("Database error: " + sqle.getMessage());
    }
  }

  public JSONObject JSONChemical(Chemical chemical) {
    JSONObject JSONChemical = new JSONObject();
    JSONChemical.put("name", chemical.name());
    JSONChemical.put("chemical_group", chemical.chemGroup());
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
