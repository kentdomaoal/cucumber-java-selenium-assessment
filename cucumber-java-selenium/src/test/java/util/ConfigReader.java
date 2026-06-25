package util;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;

    // Static block executes once when the class is first loaded into memory
    static {
        String fileLocation = "config/config.properties";
        try {
            properties = new Properties();

            // Load the file out of the resources root directory
            ClassLoader classLoader = ConfigReader.class.getClassLoader();
            try (InputStream inputStream = classLoader.getResourceAsStream(fileLocation)) {

                if (inputStream == null) {
                    throw new RuntimeException("Error: " + fileLocation + " not found in resources folder.");
                }

                // Parse the properties layout
                properties.load(inputStream);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration properties.");
        }
    }

    /**
     * Retrieves a value matching the given key from the properties configuration map.
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key '" + key + "' not found in config.properties!");
        }
        return value.trim();
    }
}
