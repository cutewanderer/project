package database;

import java.util.List;

public interface ITable {
  void create(List<String> columns);
  void update();
  void select();
  void delete();

}
