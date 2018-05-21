package com.sindelantal.prueba.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropertiesTools  {

    /** Delegate logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesTools.class);
 
    /**
     * Properties file name.
     */
    private static final String URL_SERVICES_FILE = "urlsServices.properties";

    /*
     * Singleton instance.
     */
    private static PropertiesTools instance;
 


    /**
     * Properties related to URL services.
     */
    private Properties urServicesProp = new Properties();

    /**
     * Constructor.
     */
    private PropertiesTools() { }

    static {
        LOGGER.info("|| PropertiesTools instanced ||");
        instance = new PropertiesTools();
        instance.urServicesProp = loadFileProperties(URL_SERVICES_FILE);
    }

   /**
    * Load file properties.
    * @param pathFileName  path and name file
    * @return An empty properties object if file doesn't exist
    */
    private static Properties loadFileProperties(final String pathFileName)  {
        final Properties prop = new Properties();
        final ClassLoader classLoader = PropertiesTools.class.getClassLoader();
        try {
            final InputStream inputStream = classLoader.getResourceAsStream(pathFileName);
            prop.load(inputStream);
        } catch (final IOException e) {
            LOGGER.error("An ERROR has ocurred when loading properties file {} ", pathFileName, e);
        }
        return prop;
    }


    /**
     * Gets property value.
     * @param key key to looking for.
     * @return null if the key is not found.
     */
    public static String getUrlProperty(final String key) {
        return instance.urServicesProp.getProperty(key);
    }

}
