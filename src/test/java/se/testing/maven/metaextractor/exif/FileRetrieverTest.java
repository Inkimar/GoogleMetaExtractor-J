/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.exif;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ingimar
 */
public class FileRetrieverTest {
    
    public FileRetrieverTest() {
    }

    @Test
    public void testFetchMetaDataFromImage_String_String() {
        System.out.println("fetchMetaDataFromImage");
        String directoryLinuxMac = "";
        String filter = "";
        FileRetriever instance = new FileRetriever();
        List expResult = null;
        List result = instance.fetchMetaDataFromImage(directoryLinuxMac, filter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testFetchMetaDataFromImage_String() {
        System.out.println("fetchMetaDataFromImage");
        String directoryLinuxMac = "";
        FileRetriever instance = new FileRetriever();
        List expResult = null;
        List result = instance.fetchMetaDataFromImage(directoryLinuxMac);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}