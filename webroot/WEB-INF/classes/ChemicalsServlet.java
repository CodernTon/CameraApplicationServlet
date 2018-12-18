
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

//import domain.Chemical;

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
    //out.println("<html><head><title>Chemicals servlet</title></head>");
    //out.println("<body>");

    Map<String, String[]> map =
    request.getParameterMap();
    List<Chemical> chemicals = new ArrayList<>();
    List<String> names = new ArrayList<>();

    for (Map.Entry<String, String[]> entry : map.entrySet()) {
      out.println(" * key / value: " + entry.getKey() + " / " + entry.getValue()[0] + "<br />");
      if (entry.getKey().equals("name")) {
        for (int i=0; i<entry.getValue().length; i++) {
          String value = entry.getValue()[i];
          out.println(" ----" + value);
          names.add(value);
        }
      }
    }
    System.out.println("   names:" + names);

    try{
      Connection con = DriverManager.getConnection("jdbc:sqlite:kemi.db");
      Statement stm  = con.createStatement();
      for (String chemical : names) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * from chemical WHERE name='");
        sb.append(chemical);
        sb.append("';");
        String sql = sb.toString();
        System.out.println(sql);
        ResultSet rs   = stm.executeQuery(sql);
        System.out.println("SQL: " + sql );
        while(rs.next()){
          Chemical chem = new Chemical(rs.getString("name"), rs.getString("chemical_group"));
          chemicals.add(chem);
          System.out.println(" * " + rs.getString("name"));
        }
        System.out.println();
      }
    }catch(SQLException sqle)
    {out.println("Database error: " + sqle.getMessage());
  }
  JsonFormatter formatter = new JsonFormatter();
  String JSONString = formatter.format(chemicals);
  out.println(JSONString);

  //out.println("</body></html>");
  out.close();
}

}
