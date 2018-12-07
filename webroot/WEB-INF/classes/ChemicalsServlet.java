
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

import domain.Chemical;

public class ChemicalsServlet extends HttpServlet {

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
    out.println("<html><head><title>Chemicals servlet</title></head>");
    out.println("<body>");
  String chemical =  request.getParameter("name");

  Map<String, String[]> map =
     request.getParameterMap();

  for (Map.Entry<String, String[]> entry : map.entrySet()) {
    out.println(" * key / value: " + entry.getKey() + " / " + entry.getValue()[0] + "<br />");
    if (entry.getKey().equals("name")) {
      for (int i=0; i<entry.getValue().length; i++) {
        String value = entry.getValue()[i];
        out.println(" ----" + value);
      }
    }
  }

    try{
      Connection con = DriverManager.getConnection("jdbc:sqlite:kemi.db");
      Statement stm  = con.createStatement();
      StringBuilder sb = new StringBuilder();
      List<Chemical> chemList = new ArrayList<>();
      sb.append("SELECT * from chemical WHERE name='");
      sb.append(chemical);
      sb.append("';");
      String sql = sb.toString();
      System.out.println(sql);
      ResultSet rs   = stm.executeQuery(sql);
      while(rs.next()){
        Chemical chem = new Chemical(rs.getString("name"), rs.getString("chemical_group"));
        //out.println(chem);
        //JSONArray JSONArray = new JSONArray();
        JSONObject JSONChemical = new JSONObject();
        JSONChemical.put("name", chem.name());
        JSONChemical.put("chemical_group", chem.chemGroup());
        //JSONArray.put(JSONChemical);
        String json = JSONChemical.toString(2);
        out.println(json);

      }
    }catch(SQLException sqle)
    {out.println("Database error: " + sqle.getMessage());
  }
  out.println("</body></html>");
  out.close();
}

}
