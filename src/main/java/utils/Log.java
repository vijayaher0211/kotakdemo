package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Log
 * 
 * Utility class to provide a centralized method for obtaining
 * SLF4J Logger instances for any given class.
 * 
 * Simplifies logger initialization by encapsulating LoggerFactory calls.
 */


public class Log {
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
