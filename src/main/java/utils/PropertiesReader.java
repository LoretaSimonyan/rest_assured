package utils;

import groovy.lang.MissingPropertyException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private final Properties properties;

    private PropertiesReader(String resourceLocation) {
        properties = new Properties();
        try {
            properties.load(getInputStream(resourceLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesReader of(String resourceLocation) {
        return new PropertiesReader(resourceLocation);
    }


    private static InputStream getInputStream(String filename) {
        return PropertiesReader.class.getClassLoader().getResourceAsStream(filename);

    }


    public String getProperty(String propertyName) {
        String output = properties.getProperty(propertyName);
        if (output != null) {
            return output;
        } else {
            throw new MissingPropertyException("Property " + propertyName + " is missing in target file");
        }

    }
}
