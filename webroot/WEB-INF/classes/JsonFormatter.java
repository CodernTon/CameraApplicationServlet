
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

  String chemical = args[0];

  try{
    Connection con = DriverManager.getConnection("jdbc:sqlite:kemi.db");
    Statement stm  = con.createStatement();
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * from chemical WHERE name='");
    sb.append(chemical);
    sb.append("';");
    String sql = sb.toString();
    System.out.println(sql);
    ResultSet rs   = stm.executeQuery(sql);
    while(rs.next()){
      String name = rs.getString("name");
      String chemGroup = rs.getString("chemical_group");
      //out.println(chem);
      //JSONArray JSONArray = new JSONArray();
      JSONObject JSONChemical = new JSONObject();
      JSONChemical.put("name", name);
      JSONChemical.put("chemical_group", chemGroup);
      //JSONArray.put(JSONChemical);
      //String json = JSONChemical.toString(2);
      System.out.println(JSONChemical.toString());

    }
  }catch(SQLException sqle)
  {System.out.println("Database error: " + sqle.getMessage());
}

  }
}
