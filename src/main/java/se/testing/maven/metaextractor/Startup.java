package se.testing.maven.metaextractor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.testing.maven.metaextractor.util.FilePropertiesHelper;
import se.testing.maven.metaextractor.exif.FileRetriever;
import se.testing.maven.metaextractor.logger.LoggerFactory;

/**
 * Credit to : http://java.dzone.com/articles/java-example-list-all-files
 *
 * @author ingimar
 */
public class Startup {

    public static void main(String[] args) {
        FileRetriever retriever = new FileRetriever();

        System.out.println("Main");

        // Fetches path to directory from property-file
        final String directoryLinuxMac = FilePropertiesHelper.getFilePath();

        // Fetches a single filter from property-file
        final String filter = FilePropertiesHelper.getImageFilter();

        //retriever.fetchMetaDataFromImage(directoryLinuxMac, filter);
        retriever.fetchMetaDataFromImage(directoryLinuxMac);

        String logFileName = FilePropertiesHelper.getLogNameWithPrefix();
        Logger logger = LoggerFactory.getLogger(Level.INFO,logFileName );
        logger.log(Level.INFO, "INFO-1");
        logger.log(Level.INFO, "INFO-2");

    }

    private void writeOutNameOfFiles(String directoryLinuxMac) {
        ListFilesUtil.listFiles(directoryLinuxMac);
    }
}
