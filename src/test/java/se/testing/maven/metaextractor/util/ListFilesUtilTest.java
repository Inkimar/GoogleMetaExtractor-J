/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author ingimar
 */
public class ListFilesUtilTest {

    public ListFilesUtilTest() {
        System.out.println("Testing");
    }

    @Test
    public void TEST_EXISTENCE_OF_DIR_IN_PROPERTY_FILE() {
        String expectedResult = "/home/ingimar/tmp/test-images/From-gunvi-17juni";

        final String directoryLinuxMac = FilePropertiesHelper.getFilePath();
        assertEquals(expectedResult, directoryLinuxMac);
        String directoryName = "";
        ListFilesUtil.parseFiles(directoryName);
    }

    final private String fileNameWithFaceView = "NHRS-GULI000004114_face.tif";

    @Test
    public void GET_CATALOGNUMBER_FROM_FileName() {
        String expectedResult = "NHRS-GULI000004114";

        String delimiter = ListFilesUtil.DELIMITER_CATALOGNUMBER;

        String[] parsedFileName = ListFilesUtil.parseString(fileNameWithFaceView, delimiter);
        String catalogNumber = ListFilesUtil.getCatalogeNumberFromFile(parsedFileName);


        assertEquals(expectedResult, catalogNumber);
    }

    @Test
    public void GET_VIEW_FROM_FileName() {
        String expectedResult = "face";
        System.out.println("expected " + expectedResult);

        String delimiter = ListFilesUtil.DELIMITER_CATALOGNUMBER;;
        String[] secondPartOfFileName = ListFilesUtil.parseString(fileNameWithFaceView, delimiter);

        String view = ListFilesUtil.getViewFromFile(secondPartOfFileName);

        assertEquals(expectedResult, view);
    }

    @Test @Ignore
    public void GET_CATALOGNUMBER_AND_VIEW_FROM_FILENAME() {
        Map<String, List<String>> mapForMultipleFileName = new HashMap();
        List<String> views = new ArrayList();

        // face
        String faceFile = "NHRS-GULI000004114_face.tif";
        String expectedCatalogResult = "NHRS-GULI000004114";
        String expectedViewResult = "face";


    }
}