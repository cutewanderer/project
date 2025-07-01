package database;

import connector.IDBConnector;

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


}




