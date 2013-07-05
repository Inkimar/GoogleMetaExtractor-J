package se.testing.maven.metaextractor;

import java.io.File;

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

        File directory = new File(directoryName);

        //get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList) {
            System.out.println(file.getName());
        }
    }

    /**
     * List all the files under a directory
     *
     * @param directoryName to be listed
     */
    public static void printFileNamesToConsole(String directoryName) {

         File[] fList = getFileListInFolder(directoryName);

        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }
    
    public static int getNumberOfFiles(String directoryName){
        File[] fList = getFileListInFolder(directoryName);
        return fList.length;
    }
    
    private static File[] getFileListInFolder(String directoryName){
        File directory = new File(directoryName);
        return directory.listFiles();
    }

    /**
     * Returns all the files under a directory
     *
     * @param directoryName to be listed
     */
    public static File[] getFiles(String directoryName) {
        File directory = new File(directoryName);
        return directory.listFiles();
    }

    /**
     * List all the folder under a directory
     *
     * @param directoryName to be listed
     */
    public void listFolders(String directoryName) {

        File directory = new File(directoryName);
        File[] fList = directory.listFiles();

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

        File directory = new File(directoryName);

        //get all the files from a directory
        File[] fList = directory.listFiles();

        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                listFilesAndFilesSubDirectories(file.getAbsolutePath());
            }
        }
    }
}
