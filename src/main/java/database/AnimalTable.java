package database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnimalTable extends AbsTable{
  private String tableName;

  public AnimalTable(String tableName) throws SQLException, IOException {
    super(tableName);
    this.tableName = tableName;
    Map<String, String> columns = new HashMap<>();
    columns.put("id","bigint PRIMARY KEY AUTO_INCREMENT");
    columns.put("type", "varchar(15)");
    columns.put("name", "varchar(15)");
    columns.put("age", "int");
    columns.put("weight", "int");
    columns.put("color", "varchar(15)");
    createTable(columns);
  }


  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

}
