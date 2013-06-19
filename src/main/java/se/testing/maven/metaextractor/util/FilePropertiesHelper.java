package se.testing.maven.metaextractor.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        String filter = "";
        Properties properties = new Properties();

        try {

            InputStream iStream = getInputStream();
            properties.load(iStream);
            filter = properties.getProperty("images.filter");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return filter;
    }

    public static List getImageFilters() {

        String[] split = null;
        Properties properties = new Properties();

        try {

            InputStream iStream = getInputStream();
            properties.load(iStream);
            String filePath = properties.getProperty("images.filters");
            split = filePath.split(",");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Arrays.asList(split);
    }

    private static InputStream getInputStream() {
        InputStream iStream =
                FilePropertiesHelper.class.getClassLoader().getResourceAsStream("config.properties");
        return iStream;
    }
}