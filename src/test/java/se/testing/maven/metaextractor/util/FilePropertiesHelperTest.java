/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author ingimar
 */
public class FilePropertiesHelperTest {

    public FilePropertiesHelperTest() {
    }

    @Test @Ignore
    public void TEST_EXISTENCE_OF_IMAGE_DIR_IN_PROPERTY_FILE() {
        final String expectedResult = "/home/ingimar/tmp/test-images/From-gunvi-17juni";

        final String directoryLinuxMac = FilePropertiesHelper.getTestImagesFilePath();
        assertEquals(expectedResult, directoryLinuxMac);

    }

    @Test @Ignore
    public void TEST_EXISTENCE_OF_TOP_LEVEL_DIR_IN_PROPERTY_FILE() {
        final String expectedResult = "/home/ingimar/tmp/test-images";

        final String directoryLinuxMac = FilePropertiesHelper.getTopLevelFilePath();
        assertEquals(expectedResult, directoryLinuxMac);

    }

    /**
     * Test of getImageFilters method, of class FilePropertiesHelper.
     */
    @Test @Ignore
    public void testGetImageFilters() {
        System.out.println("getImageFilters");
        List expResult = new ArrayList();
        expResult.add(".tif"); // See config.properties
        expResult.add(".CR2");
        List result = FilePropertiesHelper.getImageFilters();

        assertEquals(expResult, result);

    }
}