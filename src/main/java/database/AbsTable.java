package database;

import connector.IDBConnector;
import connector.MySqlConnector;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbsTable implements ITable {
  private String tableName = "";
  protected IDBConnector dbConnect;
  //protected Map<String, String> columns = new HashMap<>();

  public AbsTable(String tableName) {
    dbConnect = new MySqlConnector();
    this.tableName = tableName;
  }

  public void createTable(Map<String, String> columns) throws SQLException, IOException {
    List<String> columnsStr = new ArrayList<>();
    for (Map.Entry<String, String> entry : columns.entrySet()) {
      columnsStr.add(String.format("%s %s", entry.getKey(), entry.getValue()));
    }
    dbConnect.execute(String.format("CREATE TABLE IF NOT EXISTS %s (%s)", this.tableName, String.join(", ", columnsStr)));
  }

  @Override
  public void insert(Map<String, String> columnsAnimal) throws SQLException, IOException {
    List<String> columnsStr = new ArrayList<>();
    List<String> valueStr = new ArrayList<>();
    for (Map.Entry<String, String> entry : columnsAnimal.entrySet()) {
      columnsStr.add(entry.getKey());
      valueStr.add(String.format("\"%s\"", entry.getValue()));
    }
    dbConnect.execute(String.format("INSERT INTO %s (%s) VALUES (%s)", this.tableName, String.join(", ", columnsStr), String.join(", ", valueStr)));
  }

  public ResultSet select() throws SQLException, IOException {
    return dbConnect.executeQuery(String.format("SELECT * FROM %s;", this.tableName));
  }

  public void update(Map<String, String> changeValues, Map<String, String> conditions) throws SQLException{
    if (changeValues.isEmpty() || conditions.isEmpty()) {
      throw new IllegalArgumentException("SET и WHERE не должны быть пустыми");
    }
    StringBuilder sql = new StringBuilder("UPDATE ").append(this.tableName).append(" SET ");
    List<String> setClauses = new ArrayList<>();
    for (String column : changeValues.keySet()) {
      setClauses.add(column + " = ?");
    }
    sql.append(String.join(", ", setClauses));

    List<String> whereClauses = new ArrayList<>();
    for (String column : conditions.keySet()) {
      whereClauses.add(column + " = ?");
    }
    sql.append(" WHERE ").append(String.join(" AND ", whereClauses));
    try (PreparedStatement stmt = dbConnect.prepareStatement(sql.toString())) {
      int i = 1;

      // подставляем новые значения
      for (String value : changeValues.values()) {
        stmt.setString(i++, value);
      }
      // подставляем условия
      for (String value : conditions.values()) {
        stmt.setString(i++, value);
      }

      stmt.executeUpdate();

    }
  }


    public ResultSet selectFilterByType (String type) throws SQLException, IOException {
      String sql = String.format("SELECT * FROM %s WHERE type = ?;", this.tableName);
              PreparedStatement stmt = dbConnect.prepareStatement(sql);
      stmt.setString(1, type);

      return stmt.executeQuery(); // делать по аналогии выше
    }


  }






