package connector;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnector {
  void execute(String sqlRequest) throws SQLException, IOException;
  ResultSet executeQuery (String sqlRequest) throws SQLException, IOException;

  PreparedStatement prepareStatement(String string) throws SQLException;
}
