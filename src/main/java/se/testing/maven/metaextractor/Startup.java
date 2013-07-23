package se.testing.maven.metaextractor;

import se.testing.maven.metaextractor.util.ListFilesUtil;
import java.io.File;
import java.util.List;
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
        // List<String> listOfCatalogs = container.getListOfCatalogs();
        List<String> listOfCatalogs = container.getSortedListOfCatalogs();
        printCatalogs(listOfCatalogs);

        final int sizeOfCatalogNumbers = container.getNumberOfCatalogs();
        System.out.println("Size from container-method :  " + sizeOfCatalogNumbers);


        // getMetaData(directoryLinuxMac);


    }

    private static MapWrapper populateMapWrapper(String directoryLinuxMac) {
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
        List<String> fileNames = ListFilesUtil.getFileNames(files);
        return MapWrapper.getPopulatedMapWrapper(fileNames);
    }

    private static void getMetaData(String directoryLinuxMac) {
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

    private static void printCatalogs(List<String> listOfCatalogs) {
        int count = 0;
        for (String catalog : listOfCatalogs) {
            count++;
            System.out.println("count:= " + count + ": Catalognr : " + catalog);
        }
    }
}
