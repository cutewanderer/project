package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ITable {
  void create(List<String> columns) throws SQLException;
  void update() throws SQLException;
  ResultSet select() throws SQLException;
  void delete();
  ResultSet selectFilterByType() throws SQLException;

}
