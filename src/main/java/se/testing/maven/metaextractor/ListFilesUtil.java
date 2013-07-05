package se.testing.maven.metaextractor;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Contains some methods to list files and folders from a directory
 */
public class ListFilesUtil {

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
    
    public static HashMap<String,List> parseFiles(String directoryName){
        File[] fileListInFolder = getFileListInFolder(directoryName);
        return null;
    }

    /**
     * Returns all the files under a directory
     *
     * @param directoryName to be listed
     */
    public static File[] getFiles(String directoryName) {
        return getFileListInFolder(directoryName);
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

    /**
     * List all files from a directory and its subdirectories
     *
     * @param directoryName to be listed
     */
    public void listFilesAndFilesSubDirectories(String directoryName) {

        File[] fList = getFileListInFolder(directoryName);

        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                listFilesAndFilesSubDirectories(file.getAbsolutePath());
            }
        }
    }

    private static File[] getFileListInFolder(String directoryName) {
        File directory = new File(directoryName);
        return directory.listFiles();
    }
}
