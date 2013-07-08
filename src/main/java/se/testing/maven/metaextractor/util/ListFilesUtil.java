package se.testing.maven.metaextractor.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains some methods to list files and folders from a directory
 */
public class ListFilesUtil {

    public static String DELIMITER_CATALOGNUMBER = "_";

    // a dot is a reserved character in regular expression, must write like this.
    public static String DELIMITER_VIEW = "\\.";

    /**
     * List all the files and folders from a directory
     *
     * @param directoryName to be listed
     */
    public void listFilesAndFolders(String directoryName) {

        File[] fList = getFileListInFolder(directoryName);

        for (File file : fList) {
            System.out.println(file.getName());
        }
    }

    /**
     *
     * @param fList list of files to be printed
     */
    public static void printFileNamesToConsole(File[] fList) {

        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }

    public static int getNumberOfFiles(String directoryName) {
        File[] fList = getFileListInFolder(directoryName);
        return fList.length;
    }

    protected static String getCatalogeNumberFromFile(String[] fragments) {
        String catalogNumber = fragments[0];
        return catalogNumber;
    }

    protected static String getViewFromFile(String[] fragments) {
        String viewAndSuffix = fragments[1];
        String[] fragment = parseString(viewAndSuffix, ListFilesUtil.DELIMITER_VIEW);
        return fragment[0];
    }

    protected static String[] parseString(String name, String delimiter) {
        String[] split = name.split(delimiter);
        return split;
    }

    protected static Map parseFileName(String fileName) {
        return parseFileName(fileName, DELIMITER_CATALOGNUMBER);
    }

    protected static Map  parseFileName(String fileName, String delimiter) {
        Map<String, String> map = new HashMap();
        String[] split = fileName.split(delimiter);
        String cat = getCatalogeNumberFromFile(split);
        String view = getViewFromFile(split);

        map.put(cat, view);

        return map;

    }

    /**
     * Returns all the files under a directory
     *
     * @param directoryName to be listed
     */
    public static File[] getFiles(String directoryName) {
        return getFileListInFolder(directoryName);
    }
    
    public static List<String> getFileNames(File [] files){
        List <String> fileNames = new ArrayList<>();
        for (File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }

    /**
     * List all the folder under a directory
     *
     * @param directoryName to be listed
     */
    public void printFolders(String directoryName) {
        File[] fList = getFileListInFolder(directoryName);

        for (File file : fList) {
            if (file.isDirectory()) {
                System.out.println(file.getName());
            }
        }
    }

    private static File[] getFileListInFolder(String directoryName) {
        File directory = new File(directoryName);
        return directory.listFiles();
    }
    /**
     * List all files from a directory and its subdirectories
     *
     * @param directoryName to be listed
     */
//    public void listFilesAndFilesSubDirectories(String directoryName) {
//
//        File[] fList = getFileListInFolder(directoryName);
//
//        for (File file : fList) {
//            if (file.isFile()) {
//                System.out.println(file.getAbsolutePath());
//            } else if (file.isDirectory()) {
//                listFilesAndFilesSubDirectories(file.getAbsolutePath());
//            }
//        }
//    }
}
