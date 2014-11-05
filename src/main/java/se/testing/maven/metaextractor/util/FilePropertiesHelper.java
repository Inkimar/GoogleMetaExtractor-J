package se.testing.maven.metaextractor.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Reading from the property-file.
 *
 * @author ingimar
 */
public class FilePropertiesHelper {

    public static String getJsonPath() {
        Properties properties = getPropertyFile();
        String filePath = properties.getProperty("file.for.json");
        return filePath;
    }

    public static String getImagesFilePath() {
        Properties properties = getPropertyFile();
        String filePath = properties.getProperty("filepath.images");
        return filePath;
    }

    public static String getTestImagesFilePath() {
        Properties properties = getPropertyFile();
        String filePath = properties.getProperty("filepath.images.test");
        return filePath;
    }

    public static String getTopLevelFilePath() {
        Properties properties = getPropertyFile();
        String filePath = properties.getProperty("filepath.top");
        return filePath;
    }

    public static String getImageFilter() {

        Properties properties = getPropertyFile();
        String typeOfFilter = properties.getProperty("images.filter");
        return typeOfFilter;
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

    public static String getLogNameWithPrefix(boolean detailed) {
        String logFileName = getLogFileName();
        String prefix;
        if (detailed) {
            prefix = getDetailedFormattedDate();
        } else {
            prefix = getDate();
        }

        return prefix + "-" + logFileName;
    }

    private static String getLogFileName() {
        
         Properties properties = getPropertyFile();
        String typeOfFilter = properties.getProperty("log.filename");
        return typeOfFilter;
    }

    private static String getDetailedFormattedDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String formattedDate = format.format(date);
        return formattedDate;
    }

    private static String getDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = format.format(date);
        return formattedDate;
    }

    private static InputStream getInputStream() {
        InputStream iStream
                = FilePropertiesHelper.class.getClassLoader().getResourceAsStream("config.properties");
        return iStream;
    }

    private static Properties getPropertyFile() {
        InputStream iStream = getInputStream();
        Properties properties = new Properties();
        try {
            properties.load(iStream);
        } catch (IOException ex) {
            Logger.getLogger(FilePropertiesHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return properties;
    }
}
