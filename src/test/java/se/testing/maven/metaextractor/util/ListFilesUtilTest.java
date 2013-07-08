/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
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
   // @Ignore
    public void GET_CATALOGNUMBER_AND_VIEW_FROM_FILENAME() {

        final String directoryLinuxMac = FilePropertiesHelper.getImagesFilePath();
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
        List<String> fileNames = ListFilesUtil.getFileNames(files);

        MapWrapper container = new MapWrapper();
        for (String fileName : fileNames) {
            Map parsed = ListFilesUtil.parseFileName(fileName);
            container.transformMap(parsed);
        }
        final String expectedCatKey4112 = "NHRS-GULI000004112";
        final List<String> actualCat4112Views = Arrays.asList("abdo", "dors", "face", "labe");

        final String actualCat4113 = "NHRS-GULI000004113";
        final List<String> actualCat4113Views;
        final String actualCat4114 = "NHRS-GULI000004114";
        final List<String> actualCat4114Views;
        final String actualCat4115 = "NHRS-GULI000004115";
        final List<String> actualCat4115Views;

        List<String> catalogList = Arrays.asList(expectedCatKey4112, actualCat4113, actualCat4114, actualCat4115);

        // To see if we have the four catalognumbers
        assertEquals(catalogList.size(), container.size());
       
        // 4112
        List containerList = container.get(expectedCatKey4112);

        assertThat(actualCat4112Views, containsInAnyOrder(containerList.toArray()));



    }

    /**
     * http://pragmaticqa.co.uk/blog/2012/10/comparing-lists-with-hamcrest/
     */
    @Test
    public void TEST_HAMCREST_listTestsWithoutOrder() {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        list1.add("red");
        list1.add("green");
        list1.add("orange");

        list2.add("green");
        list2.add("orange");
        list2.add("red");

        assertThat("List equality without order", list1, containsInAnyOrder(list2.toArray()));
    }
}