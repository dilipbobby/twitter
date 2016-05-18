package aail.twitter.postgreseg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
  public static void main(String[] args) throws Exception {
    int tx = conn.getMetaData().getDefaultTransactionIsolation();
    System.out.println("NUMBER OF CONNECTIONS"+ tx);
    String txtxt=null;
    switch(tx) {
      case Connection.TRANSACTION_NONE :
        txtxt = "TRANSACTION_NONE"; break;
      case Connection.TRANSACTION_READ_COMMITTED :
        txtxt = "TRANSACTION_READ_COMMITTED"; break;
      case Connection.TRANSACTION_READ_UNCOMMITTED :
        txtxt = "TRANSACTION_READ_UNCOMMITTED"; break;
      case Connection.TRANSACTION_REPEATABLE_READ :
        txtxt = "TRANSACTION_REPEATABLE_READ"; break;
          case Connection.TRANSACTION_SERIALIZABLE :
        txtxt = "TRANSACTION_SERIALIZABLE"; break;
      default:
        txtxt = "UNKNOWN!!";
    }
    System.out.println(txtxt);
    conn.setTransactionIsolation(tx);
    System.out.println("Done");
    conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
    System.out.println("TX is now " + conn.getTransactionIsolation());
  }

  static Connection conn;

  static Statement st;

  static {
    try {
      // Step 1: Load the JDBC driver.
      Class.forName("org.postgresql.Driver");
      System.out.println("Driver Loaded.");
      // Step 2: Establish the connection to the database.
      String url = "jdbc:postgresql://localhost:5432/users";

      conn = DriverManager.getConnection(url, "storm", "");
      System.out.println("Got Connection.");

      st = conn.createStatement();
    } catch (Exception e) {
      System.err.println("Got an exception! ");
      e.printStackTrace();
      System.exit(0);
    }
  }
}