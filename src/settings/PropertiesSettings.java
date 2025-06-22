package settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesSettings implements ISettings{

  @Override
  public Map<String, String> getSettings(String file) throws IOException {
    Properties properties = new Properties();

    String rootPath = System.getProperty("user.dir");
    File propertyFile = new File(rootPath +"/src/resources/" + file);
    properties.load(new FileInputStream(propertyFile));

    Map<String, String> settings = new HashMap<>();
    for(Map.Entry entry: properties.entrySet()){
      settings.put((String)entry.getKey(), (String)entry.getValue());
    }
    return settings;
  }
}
