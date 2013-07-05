/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;
import se.testing.maven.metaextractor.util.FilePropertiesHelper;

/**
 *
 * @author ingimar
 */
public class ListFilesUtilTest {
    
    public ListFilesUtilTest() {
    }


    @Test
    public void testParseFiles() {
        System.out.println("parseFiles");
         final String directoryLinuxMac = FilePropertiesHelper.getFilePath();
         assertEquals("/home/ingimar/tmp/test-images/From-gunvi-17juni", directoryLinuxMac);
        String directoryName = "";
        ListFilesUtil.parseFiles(directoryName);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

}