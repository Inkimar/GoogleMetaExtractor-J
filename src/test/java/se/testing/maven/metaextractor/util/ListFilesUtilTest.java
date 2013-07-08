/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
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

    @Test
    public void GET_CATALOGNUMBER_AND_VIEW_FROM_FILENAME() {

        final String directoryLinuxMac = FilePropertiesHelper.getImagesFilePath();
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
        List<String> fileNames = ListFilesUtil.getFileNames(files);

        MapWrapper container = new MapWrapper();
        for (String fileName : fileNames) {
            Map parsed = ListFilesUtil.parseFileName(fileName);
            container.transformMap(parsed);
        }
        String cat4112 ="NHRS-GULI000004112";
        String cat4113 ="NHRS-GULI000004113";
        String cat4114 ="NHRS-GULI000004114";
        String cat4115 ="NHRS-GULI000004115";
        
        List<String> catalogList = Arrays.asList(cat4112,cat4113,cat4114,cat4115);
        
        assertEquals(catalogList.size(), container.size());
        
        


    }
}