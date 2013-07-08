package se.testing.maven.metaextractor.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Reading from the property-file.
 *
 * @author ingimar
 */
public class FilePropertiesHelper {

    public static String getImagesFilePath() {

        String filePath = "";
        Properties properties = new Properties();

        try {
            InputStream iStream = getInputStream();
            properties.load(iStream);
            filePath = properties.getProperty("filepath.images");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }

    public static String getTopLevelFilePath() {

        String filePath = "";
        Properties properties = new Properties();

        try {
            InputStream iStream = getInputStream();
            properties.load(iStream);
            filePath = properties.getProperty("filepath.top");
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

    public static String getLogNameWithPrefix() {
        String logFileName = getLogFileName();
        String prefix = getFormattedDate();

        return prefix + "-" + logFileName;
    }

    private static String getLogFileName() {

        String fileName = "";
        Properties properties = new Properties();

        try {
            InputStream iStream = getInputStream();
            properties.load(iStream);
            fileName = properties.getProperty("log.filename");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    private static String getFormattedDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = format.format(date);
        return formattedDate;
    }

    private static InputStream getInputStream() {
        InputStream iStream =
                FilePropertiesHelper.class.getClassLoader().getResourceAsStream("config.properties");
        return iStream;
    }
}