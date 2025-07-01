package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimalTable extends AbsTable implements ITable{
  public AnimalTable(String tableName) {
    super(tableName);
    columns.put("id","bigint PRIMARY KEY AUTO_INCREMENT");
    columns.put("type", "varchar(15)");
    columns.put("name", "varchar(15)");
    columns.put("age", "int");
    columns.put("weight", "int");
    columns.put("color", "varchar(15)");
    create();
  }

  private void create() {
  }

  @Override
  public void update() {

  }

  @Override
  public ResultSet select() throws SQLException {
    return super.select();
  }

  @Override
  public void delete() {

  }

  @Override
  public ResultSet selectFilterByType() throws SQLException {
    return super.selectFilterByType();
  }
}
