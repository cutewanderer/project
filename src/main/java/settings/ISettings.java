package settings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface ISettings {
  Map<String, String> getSettings(String file) throws IOException;
}
