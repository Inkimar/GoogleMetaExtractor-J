package se.testing.maven.metaextractor.util;

import se.testing.maven.metaextractor.util.enu.View;
import se.testing.maven.metaextractor.util.enu.NoView;
import java.io.File;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import se.testing.maven.entities.Image;
import se.testing.maven.metaextractor.util.enu.SwedishSex;

/**
 * Contains some methods to list files and folders from a directory
 */
public class ListFilesUtil {

    // The delimiter between catalogNumber and view.
    final public static String DELIMITER_FOR_CATALOGNUMBER = "_";

    // a dot is a reserved character in regular expression, must write it like this.
    final public static String DELIMITER_FOR_VIEW = "\\.";

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
        int count = 0;

        for (File file : fList) {
            if (file.isFile()) {
                count++;
                System.out.println(count + " : " + file.getName());
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

    protected static String[] parseString(String name, String delimiter) {
        String[] split = name.split(delimiter);
        return split;
    }

    protected static Map parseFileName(String fileName) {
        return parseFileName(fileName, DELIMITER_FOR_CATALOGNUMBER, true);
    }

    protected static Map parseFileName(String fileName, String delimiter, boolean isLogged) {
        Map<String, String> map = new HashMap();

        // If there is an underscore 
        if (fileName.contains(delimiter)) {
            String[] split = fileName.split(delimiter);
            String cat = getCatalogeNumberFromFile(split);
            String view = getViewFromFile(split);

            // all the 'allowed' ( sofar ) are in the View-ENUM-class
            if (!View.contains(view) && isLogged) { // not a part of the map now ... what should we do?
                printoutStrangeViews(fileName, view);
            } else {

                map.put(cat, view);
            }
        } else { // If the is no underscore but a dot
            String[] split = fileName.split(DELIMITER_FOR_VIEW);
            String cat = getCatalogeNumberFromFile(split);

            map.put(cat, NoView.NO_VIEW.getText());
        }

        return map;
    }

    public static Image parseTestFileName(String fileName, String suffix) {
        EnumSet<SwedishSex> filter = SwedishSex.getSubset();
        Image image = new Image();

        boolean hasGender = false;
        // filtering out 'hane' or 'hone' from the filename.
        for (SwedishSex sex : filter) {
            if (fileName.contains(sex.name())) {
                image.setFileName(fileName);
                image.setSex(sex.name());
                String latinName = fileName.replaceFirst(sex.name(), "");

                String washedLatinName = latinName.replaceFirst(suffix, "");

                String washed = crunch_HL_XL_1(washedLatinName);
                image.setLatinName(washed);
                hasGender = true;
            }
        }
        if (!hasGender) {
            image.setFileName(fileName);
            String washedLatinName = fileName.replaceFirst(suffix, "");
            String washed = crunch_HL_XL_1(washedLatinName);
            image.setLatinName(washed);
            image.setSex(SwedishSex.blank.name());
        }

        return image;
    }

    private static String crunch_HL_XL_1(String name) {
        name = name.replaceFirst("HL", "");
        name = name.replaceFirst("XL", "");
        name = name.replaceFirst("1", "");
        return name;
    }

    private static void printoutStrangeViews(String fileName, String view) {
        WrappedFileWriter.writeToFile(fileName);
    }

    protected static String getViewFromFile(String[] fragments) {
        String viewAndSuffix = fragments[1];
        String[] fragment = parseString(viewAndSuffix, ListFilesUtil.DELIMITER_FOR_VIEW);
        return fragment[0];
    }

    /**
     * Returns all the files under a directory
     *
     * @param directoryName to be listed
     * @return 
     */
    public static File[] getFiles(String directoryName) {
        return getFileListInFolder(directoryName);
    }

    public static List<String> getFileNames(File[] files) {
        List<String> fileNames = new ArrayList<>();
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
}
