package db.main;

import java.sql.*;

public class Main {

  public static void main (String[] args) {

    if (args.length==0) {

      System.out.println("Killing me softly");
      System.exit(1);
    }

    System.out.println("Trying to run db...");
    DBTest dbt = new DBTest();
    if(dbt.hasConnection()) {
      System.out.println("We have a connection.");
    } else {
      System.out.println("Couldn't get connection. :( ");
    }
    dbt.testQuery(args[0]);
  }
}
