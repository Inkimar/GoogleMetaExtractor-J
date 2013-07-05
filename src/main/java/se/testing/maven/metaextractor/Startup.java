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

        final String directoryLinuxMac = FilePropertiesHelper.getFilePath();
        System.out.println("Path is " + directoryLinuxMac);
        final int numberOfFiles = getNumberOfFiles(directoryLinuxMac);
        System.out.println("Number of files in directory " +numberOfFiles);
        writeOutNameOfFiles(directoryLinuxMac);

        // Fetches a single filter from property-file
//        final String filter = FilePropertiesHelper.getImageFilter();
//        System.out.println("Image filter is "+filter);

        // Antingen använder du ett filter ( ex. endast .CR2-bilder eller inte )
        //retriever.fetchMetaDataFromImage(directoryLinuxMac, filter);
        retriever.fetchMetaDataFromImage(directoryLinuxMac);
    }

    private static void writeOutNameOfFiles(String directoryLinuxMac) {
        ListFilesUtil.printFileNamesToConsole(directoryLinuxMac);
    }

    private static int getNumberOfFiles(String directoryLinuxMac) {
        int numberOfFiles = ListFilesUtil.getNumberOfFiles(directoryLinuxMac);
        return numberOfFiles;
    }
}
