package connector;

import settings.PropertiesSettings;

import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class MySqlConnector implements IDBConnector {

  //private  Map<String, String> dbSettings = null;
  private static Connection connection = null;
  private static Statement statement = null;

//  private MySqlConnector() throws SQLException, IOException {
//    getConnection();
//  }

  private void getConnection() throws IOException, SQLException {
    PropertiesSettings dbSettings = new PropertiesSettings();
    Map<String, String> dbData = dbSettings.getSettings("db.properties");
      if (connection == null) {
        connection = DriverManager.getConnection(
                dbData.get("url"),
                dbData.get("user"),
                dbData.get("pass")
        );
      }
      if (statement == null) {
        statement = connection.createStatement();
      }

  }
  public void closeConnection() throws SQLException{
    if (statement!=null) {
      statement.close();
    }
    if (connection!=null) {
      connection.close();
    }
  }

  public void execute(String sqlRequest) throws SQLException, IOException {
      this.getConnection();
      statement.execute(sqlRequest);
  }

  public ResultSet executeQuery (String sqlRequest) throws SQLException, IOException {
    this.getConnection();
    return statement.executeQuery(sqlRequest);
  }
  public PreparedStatement prepareStatement(String sql) throws SQLException {
    initConnection();
    return connection.prepareStatement(sql);
  }

  private void initConnection() {
  }
}