

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.sql.*;

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
    out.println("Stuff about chemicals." + "<br />");

    try{
      Connection con = DriverManager.getConnection("jdbc:sqlite:kemi.db");
      Statement stm  = con.createStatement();
      ResultSet rs   = stm.executeQuery("SELECT name FROM chemical");
      while(rs.next()){
        out.println(rs.getString("name") + "<br />");
      }
    }catch(SQLException sqle)
    {out.println("Database error: " + sqle.getMessage());
  }
  out.println("</body></html>");
  out.close();
}

}
