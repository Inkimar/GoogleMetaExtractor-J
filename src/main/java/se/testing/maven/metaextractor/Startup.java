package se.testing.maven.metaextractor;

import se.testing.maven.metaextractor.util.FilePropertiesHelper;
import se.testing.maven.metaextractor.exif.FileRetriever;

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
        
        retriever.fetchMetaDataFromImage(directoryLinuxMac, filter);
    }

    private void writeOutNameOfFiles(String directoryLinuxMac) {
        ListFilesUtil.listFiles(directoryLinuxMac);
    }
}