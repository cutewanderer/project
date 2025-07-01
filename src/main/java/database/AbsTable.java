package database;

import connector.IDBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbsTable {
  protected IDBConnector dbConnect = null;
  private String tableName = "";
  protected Map<String, String> columns = new HashMap<>();

  public AbsTable(String tableName) {
    this.tableName = tableName;
  }

  public void create(List<String> columns) throws SQLException {
    dbConnect.execute(String.format("CREATE TABLE IF NOT EXISTS %s (%s)", this.tableName, String.join(",", columns)));
  }

  public ResultSet select() throws SQLException {
    return dbConnect.executeQuery(String.format("SELECT * FROM %s" + this.tableName));
  }

  public void update() throws SQLException {
    dbConnect.execute(String.format("Update %s" + this.tableName + "SET "));
  }

  public ResultSet selectFilterByType() throws SQLException {
    return dbConnect.executeQuery(String.format("SELECT * FROM %s" + this.tableName + "WHERE %s" + columns);
  }


}




