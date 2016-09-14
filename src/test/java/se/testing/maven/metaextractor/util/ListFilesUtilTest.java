package se.testing.maven.metaextractor.util;

import se.testing.maven.metaextractor.util.enu.NoView;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.filter.ElementFilter;
import org.jdom.input.SAXBuilder;
import org.junit.Ignore;
import se.testing.maven.entities.Image;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Using hamcrest for comparing unsorted lists. - first time, good to know -
 *
 * @author ingimar
 */
public class ListFilesUtilTest {

    /**
     * Image in the filesystem is  'NHRS-GULI000004114_face.tif' , where 
     * 'NHRS-GULI000004114' is the catalogNumber
     * String[] parsedFileName NHRS-GULI000004114 and face.tif
     */
    @Test
    public void getTheCatalogNumber_fromTheFilename() {
        String fileNameWith_catalognumber_and_labe_View = "NHRS-GULI000004114_face.tif";
        String expectedCatalogNumber = "NHRS-GULI000004114";

        String delimiter = ListFilesUtil.DELIMITER_FOR_CATALOGNUMBER;

        String[] parsedFileName = ListFilesUtil.parseString(fileNameWith_catalognumber_and_labe_View, delimiter);
        String catalogNumber = ListFilesUtil.getCatalogeNumberFromFile(parsedFileName);

        assertEquals(expectedCatalogNumber, catalogNumber);
    }

    /**
     * Image in the filesystem is  'NHRS-GULI000004114_face.tif' , where 
     * 'NHRS-GULI000004114' is the catalogNumber
     * 
     * map should have the following key/value = 'NHRS-GULI000004114' => 'labe'
     *
     */
    @Test
    public void parseFilename_withCatalogNumberAndView() {
        final String FILENAME_WITH_VIEW = "NHRS-GULI000004114_labe.tif";
        Map map = ListFilesUtil.parseFileName(FILENAME_WITH_VIEW);

        String expectedCatalogNumber = "NHRS-GULI000004114";
        assertTrue(map.containsKey(expectedCatalogNumber));
        String expectedView = "labe";
        assertEquals(expectedView, map.get(expectedCatalogNumber));
    }

    /**
     * no view after the catalognumber, there is no underscore in the filename
     * map should have the following key/value = 'NHRS-GULI000004114' => 'no-view-no'
     * 
     */
    @Test // @Ignore
    public void parseFilename_withCatalogNumberAnd_noView() {
        final String FILENAME_WITH_NO_VIEW = "NHRS-GULI000004114.tif";
        String expectedCatalogNumber = "NHRS-GULI000004114";
        Map map = ListFilesUtil.parseFileName(FILENAME_WITH_NO_VIEW);

        assertTrue(map.containsKey(expectedCatalogNumber));

        String expectedView = NoView.NO_VIEW.getText();
        assertEquals(expectedView, map.get(expectedCatalogNumber));
    }

    @Test
    public void GET_VIEW_FROM_FileName() {
        String fileNameWithFaceView = "NHRS-GULI000004114_face.tif";
        String expectedResult = "face";
        System.out.println("expected " + expectedResult);

        String delimiter = ListFilesUtil.DELIMITER_FOR_CATALOGNUMBER;;
        String[] secondPartOfFileName = ListFilesUtil.parseString(fileNameWithFaceView, delimiter);

        String view = ListFilesUtil.getViewFromFile(secondPartOfFileName);

        assertEquals(expectedResult, view);
    }

    /**
     * Fetches Images from disk. - with cat.nr = NHRS-GULI000004112,
     * NHRS-GULI000004113, NHRS-GULI000004114, NHRS-GULI000004115
     */
//    @Test @Ignore
//    public void GET_CATALOGNUMBER_AND_VIEW_FROM_FILENAME() {
//
//        final String directoryLinuxMac = FilePropertiesHelper.getTestImagesFilePath();
//        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
//        List<String> fileNames = ListFilesUtil.getFileNames(files);
//
//        MapWrapper container = getPopulatedMapWrapper(fileNames);
//
//        {// 4112
//            final String key = "NHRS-GULI000004112";
//            final List<String> expectedViews = Arrays.asList("abdo", "dors", "face", "labe");
//
//            List actualViews = container.get(key);
//            assertThat(expectedViews, containsInAnyOrder(actualViews.toArray()));
//        }
//
//        {  // 4113
//            final String key = "NHRS-GULI000004113";
//            final List<String> expectedViews = Arrays.asList("dors", "labe", "vent");
//
//            List actualViews = container.get(key);
//            assertThat(expectedViews, containsInAnyOrder(actualViews.toArray()));
//        }
//
//        { //4114
//            final String key = "NHRS-GULI000004114";
//            final List<String> expectedViews = Arrays.asList("abdo", "dors", "face", "labe");
//
//            List actualViews = container.get(key);
//            assertThat(expectedViews, containsInAnyOrder(actualViews.toArray()));
//        }
//
//        { //4115
//            final String key = "NHRS-GULI000004115";
//            final List<String> expectedViews = Arrays.asList("abdo", "dors", "face", "labe");
//
//            List actualViews = container.get(key);
//            assertThat(expectedViews, containsInAnyOrder(actualViews.toArray()));
//        }
//    }

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

//    @Test @Ignore
//    public void parseTestfilename() {
//
//        final String directoryLinuxMac = FilePropertiesHelper.getTestImagesFilePath();
//        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
//        List<String> fileNames = ListFilesUtil.getFileNames(files);
//        List<Image> images = new ArrayList<>();
//        for (String fileName : fileNames) {
//            Image image = ListFilesUtil.parseTestFileName(fileName, ".jpg");
//            images.add(image);
//        }
//        System.out.println("Orginal imageList " + images.size());
//        List<Image> extendedImageList = new ArrayList<>();
//        for (Image image : images) {
//            // String latinName = "Lucanus%20cervus";
//            String api = "https://dina-web.net/naturalist/api/v1/spm/get/taxon/latin/" + image.getLatinName() + ".xml?locale=sv_SE ";
//            ElementFilter filter = new ElementFilter("taxonId");
//            URL url;
//            try {
//                url = new URL(api);
//                SAXBuilder builder = new SAXBuilder();
//                Document doc = builder.build(url);
//                Element root = doc.getRootElement();
//                List<Element> content = root.getContent(filter);
//                String value = "";
//                for (Element c : content) {
//                    value = c.getValue();
//                }
//                image.setNaturalistUUID(value);
//                extendedImageList.add(image);
//            } catch (MalformedURLException ex) {
//                Logger.getLogger(ListFilesUtilTest.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (JDOMException | IOException ex) {
//                Logger.getLogger(ListFilesUtilTest.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//        System.out.println("Extended imageList " + extendedImageList.size());
//
//        for (Image image : extendedImageList) {
//            if (image.getNaturalistUUID().isEmpty()) {
//                System.out.println(image);
//            }
//        }
//    }

}
