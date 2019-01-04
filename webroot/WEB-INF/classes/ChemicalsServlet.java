//The servlet that receives the URL, checks the database and returns JSON.

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.sql.*;

import org.json.*;

public class ChemicalsServlet extends HttpServlet {

  //Loads the JDBC driver to get access to database.
  public void init() throws ServletException {
    try{
      Class.forName("org.sqlite.JDBC");
    }catch(ClassNotFoundException cnfe){
      System.err.println("Could not load driver: "+cnfe.getMessage());
    }
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws ServletException, IOException {
    response.setCharacterEncoding(UTF_8.name());
    PrintWriter out = response.getWriter();

    Map<String, String[]> map =request.getParameterMap();
    List<Chemical> chemicals = new ArrayList<>();
    List<String> values = new ArrayList<>();
    List<Message> messages = new ArrayList<>();

    //Loops through the parameters in the URL request to get all values with the key "substance".
    //Then stores the values in the ArrayList values.
    for (Map.Entry<String, String[]> entry : map.entrySet()) {
      //out.println(" * key / value: " + entry.getKey() + " / " + entry.getValue()[0] + "<br />");
      //this was a check to see which kay/value pairs we got.
      if (entry.getKey().equals("substance")) {
        for (int i=0; i<entry.getValue().length; i++) {
          String value = entry.getValue()[i];
          //out.println(" ----" + value);
          //this was a check to see that the value was stored propperly.
          values.add(value);
        }
      }
    }

    try{
      Connection con = DriverManager.getConnection("jdbc:sqlite:CSV/chems.db");
      Statement stm  = con.createStatement();
      //this for-loop takes the values stored in ArrayList names and creates an SQL-query for each.
      for (String chemical : values) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from chemicals WHERE substance='");
        sb.append(chemical);
        sb.append("';");
        String sql = sb.toString();
        //This was to check what was passed to the database.
        //System.out.println(sql);
        ResultSet rs   = stm.executeQuery(sql);
        //if the resultset is empty the client needs to know this.
        //This part of the code creates a Message-object to let the client know.
        if (!rs.isBeforeFirst()) {
          Message notFoundMsg = new Message(chemical, "was not found in database");
          messages.add(notFoundMsg);
        }
        //This while-loop creates a Chemical-object from the resultset by using a Builder.
        while(rs.next()){
          Chemical chem = new Chemical.ChemicalBuilder(rs.getString("substance"), rs.getString("criteria"))
          .casNr(rs.getString("CAS"))
          .egNr(rs.getString("EG"))
          .prioLevel(rs.getString("prio_level"))
          .build();
          chemicals.add(chem);
        }
      }
    } catch(SQLException sqle) {
      out.println("Database error: " + sqle.getMessage());
    }
    //This part formats the Message-objects and Chemical-objects to JSON.
    JsonFormatter formatter = new JsonFormatter();
    JSONArray jsonMessageArray = formatter.createMessageArray(messages);
    JSONArray jsonChemicalArray = formatter.createChemicalArray(chemicals);
    String JSONString = formatter.createJSONString(jsonMessageArray, jsonChemicalArray);
    out.println(JSONString);
    out.close();
  }
}
