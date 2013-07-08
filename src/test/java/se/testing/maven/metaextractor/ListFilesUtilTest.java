/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import se.testing.maven.metaextractor.util.FilePropertiesHelper;

/**
 *
 * @author ingimar
 */
public class ListFilesUtilTest {

    public ListFilesUtilTest() {
        System.out.println("Testing");
    }

    @Test
    public void TEST_DIRECTORY_IN_PROPERTY_FILE() {
        String expectedResult = "/home/ingimar/tmp/test-images/From-gunvi-17juni";

        final String directoryLinuxMac = FilePropertiesHelper.getFilePath();
        assertEquals(expectedResult, directoryLinuxMac);
        String directoryName = "";
        ListFilesUtil.parseFiles(directoryName);
    }

    final private String fileName = "NHRS-GULI000004114_face.tif";

    @Test
    public void test_GET_CATALOGNUMBER_FROM_FileName() {
        String expectedResult = "NHRS-GULI000004114";

        String delimiter = ListFilesUtil.DELIMITER_CATALOGNUMBER;

        String[] parsedFileName = ListFilesUtil.parseString(fileName, delimiter);
        String catalogNumber = ListFilesUtil.getCatalogeNumberFromFile(parsedFileName);


        assertEquals(expectedResult, catalogNumber);
    }

    @Test
    public void test_GET_VIEW_FROM_FileName() {
        String expectedResult = "face";
        System.out.println("expected " + expectedResult);

        String delimiter = ListFilesUtil.DELIMITER_CATALOGNUMBER;;
        String[] secondPartOfFileName = ListFilesUtil.parseString(fileName, delimiter);

        String view = ListFilesUtil.getViewFromFile(secondPartOfFileName);

        assertEquals(expectedResult, view);
    }

    /**
     * 1) Parsing face 1.1 ) put face into map, key= x 2) Parsing dors 2.1) put
     * dors in map, key = x
     * 
     * Skicka in en lista ( getFiles )  och få tillbaka Map<String,List<String>> 
     * där key är catalognumber ... google guava ?
     */
    @Test
    public void test_GET_CATALOGNUMBER_AND_VIEW_FROM_FILENAME() {
        Map<String, List<String>> mapForMultipleFileName = new HashMap();
        List<String> views = new ArrayList();

        // face
        String faceFile = "NHRS-GULI000004114_face.tif";
        String expectedCatalogResult = "NHRS-GULI000004114";
        String expectedViewResult = "face";


        ListFilesUtil util = new ListFilesUtil();
        Map<String, String> faceMap = util.p(faceFile, ListFilesUtil.DELIMITER_CATALOGNUMBER);

        boolean containsFaceKey = faceMap.containsKey(expectedCatalogResult);

        String actualFaceView = faceMap.get(expectedCatalogResult);

        assertTrue(containsFaceKey);
        assertEquals(expectedViewResult, actualFaceView);

        // OBS - Nyckeln
        views.add(actualFaceView);
        Set<String> keySet = faceMap.keySet();
        for (String key : keySet) {
            mapForMultipleFileName.put(key, views); // borde finnas ett enklare sätt 
        }


        // dors
        String dorsFile = "NHRS-GULI000004114_dors.tif";
        String expectedDorsCatalogResult = "NHRS-GULI000004114";
        String expectedDorsViewResult = "dors";

        Map<String, String> dorsMap = util.p(dorsFile, ListFilesUtil.DELIMITER_CATALOGNUMBER);

        boolean containsDorsKey = dorsMap.containsKey(expectedDorsCatalogResult);

        String actualDorsView = dorsMap.get(expectedCatalogResult);

        assertTrue(containsFaceKey);
        assertEquals(expectedDorsViewResult, actualDorsView);

        // OBS Nyckeln
        views.add(actualDorsView);
        Set<String> dorsMapKeySet = dorsMap.keySet();
        for (String dorKey : dorsMapKeySet) {
            if (mapForMultipleFileName.containsKey(dorKey)) {
               // mapForMultipleFileName.remove(keySet);
                mapForMultipleFileName.put(dorKey, views);
            }
        }
        
        int size = mapForMultipleFileName.size();
        
        assertEquals(1, size);
        List<String> listOfviews = mapForMultipleFileName.get(expectedDorsCatalogResult);
        assertEquals(2, listOfviews.size());

    }
}