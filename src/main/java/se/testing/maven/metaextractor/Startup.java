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

    /**
     * send in a path such as '/home/ingimar/Documents/gunvis-bilder/'
     * @TODO , send in filter : if '-f .jpg' the run exif . 
     * @param args 
     */
    public static void main(String[] args) {

        String directory;
        if (args.length > 0) {
            directory = args[0];
            System.out.println("incoming path to file  is '" + directory + "'");
        } else {
            directory = FilePropertiesHelper.getImagesFilePath();
            if (directory == null || directory.isEmpty()) {
                throw new IllegalArgumentException("Not a valid path");
            }
            System.out.println("path from 'config.properties' to file  is '" + directory + "'");
        }

        final int numberOfFiles = getNumberOfFiles(directory);
        System.out.println("\t has  #" + numberOfFiles + " image(s)");

        //writeFilenamesToConsole(directory);

        MapWrapper container = populateMapWrapper(directory);
        List<String> listOfCatalogs = container.getSortedListOfCatalogs();
        printCatalogs(listOfCatalogs);

        final int sizeOfCatalogNumbers = container.getNumberOfCatalogs();
        System.out.println("Number of catalognumbers:  " + sizeOfCatalogNumbers +" number of files "+ numberOfFiles);

        getMetaData(directory, true);

    }

    /**
     * example of returned mapper :
     * 
     * map[0] : NHRS-GULI000000004 => [labr, late, dors]
     * map[1] : NHRS-GULI000000003 => [late, dors]
     * map[2] : NHRS-GULI000000002 => [late, dors]
     * map[3] : NHRS-GULI000000008 => [dors, late, vari, no-view-no]
     * map[4] : NHRS-GULI000000007 => [pron, dors, late, dors, late, dors]
     * map[5] : NHRS-GULI000000006 => [pron, late, dors, dors]
     * map[6] : NHRS-GULI000000005 => [pron, late, late, dors, dors]
     * 
     * @param directory
     * @return 
     */
    private static MapWrapper populateMapWrapper(String directory) {
        File[] files = ListFilesUtil.getFiles(directory);
        List<String> fileNames = ListFilesUtil.getFileNames(files);
        return MapWrapper.getPopulatedMapWrapper(fileNames);
    }

    /**
     * Using the filter from the config.properties-file
     * 
     * @param directory
     * @param withFilter 
     */
    private static void getMetaData(String directory, boolean withFilter) {
        ExifExtract retriever = new ExifExtract();
        final String filter = FilePropertiesHelper.getImageFilter();
        System.out.println("\n Filter that is used "+filter);

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

    private static void writeFilenamesToConsole(String directoryLinuxMac) {
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
