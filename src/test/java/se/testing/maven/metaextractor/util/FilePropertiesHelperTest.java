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

    @Test // @Ignore
    public void testGetImageFilters() {
        List expResult = new ArrayList();
        expResult.add(".tif"); // See config.properties
        expResult.add(".CR2");
        List result = FilePropertiesHelper.getImageFilters();

        assertEquals(expResult, result);

    }
}