/*
 *      File: Utils.java
 *    Author: Esau Mendoza <esau.mendoza@amk-technologies.com>
 *      Date: Oct 04, 2017
 * Copyright: AMK Technologies, S.A. de C.V. 2017
 */
package com.sindelantal.prueba.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * Utils class.
  *
  * @author Esau Mendoza &lt;esau.mendoza@amk-technologies.com&gt;
  * @version 1.0.0
  * @since 1.0.0
  */
public class Utils {

    /** Delegate logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    /**
     * Method that gets the value via the key.
     *
     * @param key Key value.
     * @return value The retrieved value.
     */
    public static String getPropertieValue(final String key, final String filename) {
        String value = null;
        final Properties prop = new Properties();
        final String fileName = filename;
        final InputStream is;
        try {
            final Class<Utils> cls = Utils.class;
            final ClassLoader cLoader = cls.getClassLoader();
            is = cLoader.getResourceAsStream(fileName);
            prop.load(is);
            value = (String) prop.get(key);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        LOGGER.debug("Value for " + key + ":: " + value);
        return value;
    }
}
