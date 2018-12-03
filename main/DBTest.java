package db.main;

import java.sql.*;

public class DBTest {

  static{

    try{
      Class.forName("org.sqlite.JDBC");
    }catch(ClassNotFoundException cnfe){
      System.err.println("Could not load driver: " + cnfe.getMessage());
    }

  }
  private final static String DB_CONN_STR="jdbc:sqlite:kemi.db";
  private static Connection con;

  public DBTest() {
    getConnection();
  }

  private void getConnection() {
    try {
      con = DriverManager.getConnection(DB_CONN_STR);
    } catch(Exception e) {
      System.err.println("Error getting connection to: " + DB_CONN_STR);
    }
  }

  public boolean hasConnection() {
    return con !=null;
  }

/*  public String createQuery(String args[]) {
    String argument = args[0];
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT * FROM chemical WHERE name=''");
    sb.insert(35, argument);
    String argQuery = sb.toString();
    return argQuery;
  }*/ //

  public void testQuery(String name) {
    if (hasConnection()) {
      Statement stm = null;
      ResultSet rs = null;

      try {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM chemical WHERE name='");
        sb.append(name);
        sb.append("'");
        String query = sb.toString();
        stm = con.createStatement();
        rs = stm.executeQuery(query);
        while(rs.next()) {
          System.out.println(rs.getString("name"));
        }
      }catch(SQLException sqle) {
        System.err.println(sqle.getMessage());
      }finally{
        try{
          rs.close();
          stm.close();
        }catch(Exception e) {}
        }
      }
    }
  }
