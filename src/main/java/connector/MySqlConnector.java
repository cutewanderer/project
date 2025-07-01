package connector;

import settings.ISettings;
import settings.PropertiesSettings;

import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class MySqlConnector implements IDBConnector {

  private  Map<String, String> dbSettings = null;
  private static Connection connection = null;
  private static Statement statement = null;

  private MySqlConnector() throws SQLException, IOException {
    dbSettings = new PropertiesSettings().getSettings("db.properties");
    getConnection();
  }

  private void getConnection() throws SQLException {
    if (connection == null) {
      connection = DriverManager.getConnection(
              this.dbSettings.get("url"),
              this.dbSettings.get("user"),
              this.dbSettings.get("pass")
      );
    }
    if (statement == null){
      statement = connection.createStatement();
    }
  }
  private void closeConnection() throws SQLException{
    if (statement!=null) {
      statement.close();
    }
    if (connection!=null) {
      connection.close();
    }
  }

  public void execute(String sqlRequest) throws SQLException {
    statement.execute(sqlRequest);
  }
  public ResultSet executeQuery (String sqlRequest) throws SQLException {
    return statement.executeQuery(sqlRequest);
  }
}