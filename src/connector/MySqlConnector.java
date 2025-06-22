package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
  private static final String url = "jdbc:mysql://sql.home.kartushin.su:3306/test";
  private static final String user = "admin";
  private static final String pass = "711267";

  private ConnectionManager(){}

  public static Connection getConnect() throws SQLException {
    return DriverManager.getConnection(url, user, pass);
  }

}
