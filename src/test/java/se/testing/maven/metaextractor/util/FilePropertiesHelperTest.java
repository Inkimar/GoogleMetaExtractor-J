/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ingimar
 */
public class FilePropertiesHelperTest {
    
    public FilePropertiesHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getImageFilters method, of class FilePropertiesHelper.
     */
    @Test
    public void testGetImageFilters() {
        System.out.println("getImageFilters");
        List expResult = new ArrayList();
        expResult.add(".tif"); // See config.properties
        expResult.add(".CR2");
        List result = FilePropertiesHelper.getImageFilters();
        assertEquals(expResult, result);
       
    }
}