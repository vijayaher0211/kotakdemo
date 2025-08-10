package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;

/**
 * ReadConfigFile
 * 
 * Utility class to load and provide access to configuration properties
 * from environment-specific properties files (e.g., config.qa.properties).
 * 
 * Loads the configuration based on the "env" system property (default: "qa").
 * Provides typed getter methods for commonly used config keys like browser,
 * base URL, timeouts, retry count, etc.
 * 
 * Throws RuntimeException if config file is missing or a requested key is not found.
 */



public class ReadConfigFile {
	
    private static Properties properties;
    private static final Logger logger = Log.getLogger(ReadConfigFile.class);
	
    static {
        loadProperties();
    }
	
    private static void loadProperties() {
        String env = System.getProperty("env", "qa");
        String configPath = System.getProperty("user.dir") + "/src/main/resources/config." + env + ".properties";

        logger.info("Loading configuration from: {}", configPath);

        try {
        	FileInputStream fileInputStream = new FileInputStream(configPath);
            properties = new Properties();
            properties.load(fileInputStream);
            logger.info("Configuration loaded successfully for environment: {}", env);
        } catch (IOException e) {
            logger.error("Error loading configuration file: {}", configPath, e);
            throw new RuntimeException("Failed to load configuration file: " + configPath, e);
        }
    }
    
    
    /**
     * Generic method to get any property by key
     */
    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.error("Config key '{}' not found in properties file", key);
            throw new RuntimeException("Config key not found: " + key);
        }
        return value.trim();
    }
	
    
    public static String getBrowserName() {
        return get("browser");
    }

    public static String getBaseUrl() {
        return get("baseUrl");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(get("implicitlyWait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(get("pageLoadTimeout"));
    }

    public static String getResultFilePath() {
        return get("resultFilePath");
    }
    
    public static int getRetryCount() {
    	return Integer.parseInt(get("retryCount"));
    }

}
