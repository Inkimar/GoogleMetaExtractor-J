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
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Using hamcreast for comparing unsorted lists. - first time, good to know -
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
    public void parse_Filename_With_View() {
        String fileNameWith_labe_View = "NHRS-GULI000004114_labe.tif";
        String expectedCatalogNumber = "NHRS-GULI000004114";
        String expectedView = "labe";
        Map map = ListFilesUtil.parseFileName(fileNameWith_labe_View);

        assertTrue(map.containsKey(expectedCatalogNumber));
        assertEquals(expectedView, map.get(expectedCatalogNumber));
    }

    @Test
    public void parse_Filename_With_NO_View() {
        // det saknas 'under_score'
        final String FILENAME_WITH_NO_VIEW = "NHRS-GULI000004114.tif";
        String expectedCatalogNumber = "NHRS-GULI000004114";
        Map map = ListFilesUtil.parseFileName(FILENAME_WITH_NO_VIEW);

        assertTrue(map.containsKey(expectedCatalogNumber));
        assertEquals(ListFilesUtil.NO_VIEW, map.get(expectedCatalogNumber));
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

        final String directoryLinuxMac = FilePropertiesHelper.getTestImagesFilePath();
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
        List<String> fileNames = ListFilesUtil.getFileNames(files);

        MapWrapper container = getPopulatedMapWrapper(fileNames);

        {// 4112
            final String actualCatKey4112 = "NHRS-GULI000004112";
            final List<String> actualCat4112Views = Arrays.asList("abdo", "dors", "face", "labe");

            List actual4112List = container.get(actualCatKey4112);
            assertThat(actualCat4112Views, containsInAnyOrder(actual4112List.toArray()));
        }

        {  // 4113
            final String actualCat4113 = "NHRS-GULI000004113";
            final List<String> actualCat4113Views = Arrays.asList("dors", "labe", "vent");

            List actual4113List = container.get(actualCat4113);
            assertThat(actualCat4113Views, containsInAnyOrder(actual4113List.toArray()));
        }

        { //4114
            final String actualCat4114 = "NHRS-GULI000004114";
            final List<String> actualCat4114Views = Arrays.asList("abdo", "dors", "face", "labe");

            List actual4114List = container.get(actualCat4114);
            assertThat(actualCat4114Views, containsInAnyOrder(actual4114List.toArray()));
        }

        { //4115
            final String actualCat4115 = "NHRS-GULI000004115";
            final List<String> actualCat4115Views = Arrays.asList("abdo", "dors", "face", "labe");

            List actual4115List = container.get(actualCat4115);
            assertThat(actualCat4115Views, containsInAnyOrder(actual4115List.toArray()));
        }
    }

    private MapWrapper getPopulatedMapWrapper(List<String> fileNames) {
        return MapWrapper.getPopulatedMapWrapper(fileNames);
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