package se.testing.maven.metaextractor.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reading from the property-file.
 * 
 * @author ingimar
 */
public class FilePropertiesHelper {

    public static String getFilePath() {

        String filePath = "";
        Properties properties = new Properties();

        try {
            InputStream iStream = getInputStream();
            properties.load(iStream);
            filePath = properties.getProperty("images.filepath");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }

    public static String getImageFilter() {

        String filePath = "";
        Properties properties = new Properties();

        try {

            InputStream iStream = getInputStream();
            properties.load(iStream);
            filePath = properties.getProperty("images.filter");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }

    private static InputStream getInputStream() {
        InputStream iStream =
                FilePropertiesHelper.class.getClassLoader().getResourceAsStream("config.properties");
        return iStream;
    }
}