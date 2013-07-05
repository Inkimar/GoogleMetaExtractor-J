/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor;

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
        System.out.println("parseFiles");
        final String directoryLinuxMac = FilePropertiesHelper.getFilePath();
        assertEquals("/home/ingimar/tmp/test-images/From-gunvi-17juni", directoryLinuxMac);
        String directoryName = "";
        ListFilesUtil.parseFiles(directoryName);
    }

    final private String fileName = "NHRS-GULI000004114_face.tif";
    
    @Test
    public void test_GET_CATALOGNUMBER_FROM_FileName() {
        String expectedResult ="NHRS-GULI000004114";
        
        String delimiter = ListFilesUtil.DELIMITER_CATALOGNUMBER;
        
        String [] parsedFileName = ListFilesUtil.parseString(fileName,delimiter);
        String catalogNumber = ListFilesUtil.getCatalogeNumberFromFile(parsedFileName);
    
       
        assertEquals(expectedResult, catalogNumber);
    }
    
    @Test
    public void test_GET_VIEW_FROM_FileName() {
        String expectedResult ="face";
        System.out.println("expected "+expectedResult);
        
        String delimiter = ListFilesUtil.DELIMITER_CATALOGNUMBER;;
        String [] secondPartOfFileName = ListFilesUtil.parseString(fileName,delimiter);
        
        String view = ListFilesUtil.getViewFromFile(secondPartOfFileName);
       
        assertEquals(expectedResult, view);
    }
}