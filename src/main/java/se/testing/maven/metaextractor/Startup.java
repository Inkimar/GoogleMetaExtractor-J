package se.testing.maven.metaextractor;

import se.testing.maven.metaextractor.util.ListFilesUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONObject;
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

        System.out.println("length  for args is "+args.length);

        final String fileDirectory = FilePropertiesHelper.getImagesFilePath();
        if (fileDirectory == null || fileDirectory.isEmpty() ){
            throw new IllegalArgumentException("Not a valid path");
        }
 
        final int numberOfFiles = getNumberOfFiles(fileDirectory);
        System.out.println("path "+ fileDirectory + " #" + numberOfFiles +" image(s)");

        writeOutNameOfFiles(fileDirectory);

        MapWrapper container = populateMapWrapper(fileDirectory);
        // List<String> listOfCatalogs = container.getListOfCatalogs();
        List<String> listOfCatalogs = container.getSortedListOfCatalogs();
        printCatalogs(listOfCatalogs);

        final int sizeOfCatalogNumbers = container.getNumberOfCatalogs();
        System.out.println("Size from container-method :  " + sizeOfCatalogNumbers);

        getMetaData(fileDirectory, true);

    }

    private static MapWrapper populateMapWrapper(String directory) {
        File[] files = ListFilesUtil.getFiles(directory);
        List<String> fileNames = ListFilesUtil.getFileNames(files);
        return MapWrapper.getPopulatedMapWrapper(fileNames);
    }

    private static void getMetaData(String directory, boolean withFilter) {
        ExifExtract retriever = new ExifExtract();
        final String filter = FilePropertiesHelper.getImageFilter();

        List<JSONObject> listOfJSONobjects = null;

        if (withFilter) {
            retriever.fetchMetaDataFromImage(directory, filter);
            listOfJSONobjects = retriever.transformToJSON(directory, filter);
        } else {
            retriever.fetchMetaDataFromImage(directory);
        }
        FileWriter fileWriter = null;
        final String pathToJSONfile = FilePropertiesHelper.getJsonPath();
        int count = 1;
        try {
            fileWriter = new FileWriter(pathToJSONfile);
            for (JSONObject json : listOfJSONobjects) {

                fileWriter.write(json.toJSONString());
                System.out.println(json + "\n");

                fileWriter.flush();
                count++;
            }
        } catch (IOException ex) {
        } finally {
        }

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
