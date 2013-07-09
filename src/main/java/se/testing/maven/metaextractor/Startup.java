package se.testing.maven.metaextractor;

import se.testing.maven.metaextractor.util.ListFilesUtil;
import java.io.File;
import java.util.List;
import java.util.Map;
import se.testing.maven.metaextractor.util.FilePropertiesHelper;
import se.testing.maven.metaextractor.exif.ExifExtract;
import se.testing.maven.metaextractor.util.MapWrapper;

/**
 * Credit to : http://java.dzone.com/articles/java-example-list-all-files
 *
 * @author ingimar
 */
public class Startup {

    public static void main(String[] args) {

        System.out.println("Main");

        final String directoryLinuxMac = FilePropertiesHelper.getImagesFilePath();
        System.out.println("Path is " + directoryLinuxMac);
        final int numberOfFiles = getNumberOfFiles(directoryLinuxMac);
        System.out.println("Number of files in directory " + numberOfFiles);

       writeOutNameOfFiles(directoryLinuxMac);
        
        MapWrapper container = populateMapWrapper(directoryLinuxMac);
        final int sizeOfCatalogNumbers = container.getSizeOfCatalogNumbers();
        System.out.println("Size "+sizeOfCatalogNumbers);


    }
    

    private static MapWrapper populateMapWrapper(String directoryLinuxMac) {
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
        List<String> fileNames = ListFilesUtil.getFileNames(files);
        return MapWrapper.getPopulatedMapWrapper(fileNames);
    }

    private static void getMetaDate(String directoryLinuxMac) {
        ExifExtract retriever = new ExifExtract();
        // Fetches a single filter from property-file
        final String filter = FilePropertiesHelper.getImageFilter();

        // Antingen använder du ett filter ( ex. endast .CR2-bilder eller inte )
        //retriever.fetchMetaDataFromImage(directoryLinuxMac, filter);
        retriever.fetchMetaDataFromImage(directoryLinuxMac);
    }

    private static void writeOutNameOfFiles(String directoryLinuxMac) {
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
        ListFilesUtil.printFileNamesToConsole(files);
    }

    private static int getNumberOfFiles(String directoryLinuxMac) {
        int numberOfFiles = ListFilesUtil.getNumberOfFiles(directoryLinuxMac);
        return numberOfFiles;
    }
}
