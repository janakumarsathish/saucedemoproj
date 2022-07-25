package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
@ClassName = PropertyFileReader
@Description = This class is used to read a properties file
 */
public class PropertyFileReader {
    public static Properties readPropertiesFile(String fileName) {
        Properties properties = new Properties();
        try{
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream inputStream = loader.getResourceAsStream(fileName);
            properties.load(inputStream);
        } catch (IOException e){
        }
        return properties;
    }
}
