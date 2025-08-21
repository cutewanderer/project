package database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface ITable {
  void createTable(Map<String, String> columns) throws SQLException, IOException;
  void insert(Map<String, String> columnsAnimal) throws SQLException, IOException;
  void update(Map<String, String> changeValues, Map<String, String> conditions) throws SQLException, IOException;
  ResultSet select() throws SQLException, IOException;
  ResultSet selectFilterByType(String type) throws SQLException, IOException ;

}
