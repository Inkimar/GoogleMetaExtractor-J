/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import se.testing.maven.metaextractor.util.enu.NoView;
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
 * Using hamcrest for comparing unsorted lists. - first time, good to know -
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
        
        String expectedView = NoView.NO_VIEW.getText();
        assertEquals(expectedView, map.get(expectedCatalogNumber));
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

    /**
     * Fetches Images from disk. - with cat.nr = NHRS-GULI000004112,
     * NHRS-GULI000004113, NHRS-GULI000004114, NHRS-GULI000004115
     */
    @Test
    public void GET_CATALOGNUMBER_AND_VIEW_FROM_FILENAME() {

        final String directoryLinuxMac = FilePropertiesHelper.getTestImagesFilePath();
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
        List<String> fileNames = ListFilesUtil.getFileNames(files);

        MapWrapper container = getPopulatedMapWrapper(fileNames);

        {// 4112
            final String key = "NHRS-GULI000004112";
            final List<String> expectedViews = Arrays.asList("abdo", "dors", "face", "labe");

            List actualViews = container.get(key);
            assertThat(expectedViews, containsInAnyOrder(actualViews.toArray()));
        }

        {  // 4113
            final String key = "NHRS-GULI000004113";
            final List<String> expectedViews = Arrays.asList("dors", "labe", "vent");

            List actualViews = container.get(key);
            assertThat(expectedViews, containsInAnyOrder(actualViews.toArray()));
        }

        { //4114
            final String key = "NHRS-GULI000004114";
            final List<String> expectedViews = Arrays.asList("abdo", "dors", "face", "labe");

            List actualViews = container.get(key);
            assertThat(expectedViews, containsInAnyOrder(actualViews.toArray()));
        }

        { //4115
            final String key = "NHRS-GULI000004115";
            final List<String> expectedViews = Arrays.asList("abdo", "dors", "face", "labe");

            List actualViews = container.get(key);
            assertThat(expectedViews, containsInAnyOrder(actualViews.toArray()));
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
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        list1.add("red");
        list1.add("green");
        list1.add("orange");

        list2.add("green");
        list2.add("orange");
        list2.add("red");

        assertThat("List equality without order", list1, containsInAnyOrder(list2.toArray()));
    }
}