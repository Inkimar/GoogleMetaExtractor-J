/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ingimar
 */
public class MapWrapperTest {

    public MapWrapperTest() {
    }

    @Test
    public void oneViewOneKey() {
        System.out.println("one view one key");
        MapWrapper instance = new MapWrapper();

        final String commonKey = "NHRS-GULI000004114";
        final String value = "face";

        Map<String, String> catalogAndViewFacemap = new HashMap<>();
        catalogAndViewFacemap.put(commonKey, value);
        instance.transformMap(catalogAndViewFacemap);

        Map<String, List<String>> bigMap = instance.getMap();

        assertTrue(bigMap.containsKey(commonKey));

        int catalogSize = bigMap.size();
        assertEquals(1, catalogSize);

        List<String> views = bigMap.get(commonKey);
        assertTrue(views.contains(value));

        int viewSize = views.size();
        for (String view : views) {
            System.out.println("1st test " + view);

        }
        assertEquals(1, viewSize);

    }

    @Test
    public void twoViewsOneKey() {
        System.out.println("two views one key");
        MapWrapper instance = new MapWrapper();

        final String commonKey = "NHRS-GULI000004114";

        final String faceValue = "face";
        final String dorsValue = "dors";

        instance = addToWrapper(instance, commonKey, faceValue);
        instance = addToWrapper(instance, commonKey, dorsValue);

        Map<String, List<String>> bigMap = instance.getMap();

        assertTrue(bigMap.containsKey(commonKey));

        List<String> views = bigMap.get(commonKey);

        assertEquals(2, views.size());
        assertTrue(views.contains(faceValue));

    }

    @Test
    public void manyViewsManyKeys() {
        MapWrapper instance = new MapWrapper();

        final String catalog_key_4114 = "NHRS-GULI000004114";

        final String faceValue = "face";
        final String dorsValue = "dors";
        instance = addToWrapper(instance, catalog_key_4114, faceValue);
        instance = addToWrapper(instance, catalog_key_4114, dorsValue);
        List<String> list4114Contains = Arrays.asList(faceValue, dorsValue);

        final String catalog_key_4118 = "NHRS-GULI000004118";

        final String abdoValue = "abdo";
        instance = addToWrapper(instance, catalog_key_4118, faceValue);
        instance = addToWrapper(instance, catalog_key_4118, dorsValue);
        instance = addToWrapper(instance, catalog_key_4118, abdoValue);
        List<String> list4118Contains = Arrays.asList(faceValue, dorsValue,abdoValue);

        Map<String, List<String>> bigMap = instance.getMap();

        // Are the keys there ?
        assertTrue(bigMap.containsKey(catalog_key_4114));
        assertTrue(bigMap.containsKey(catalog_key_4118));

        int numberOfCatalogs = 2;
        assertEquals(numberOfCatalogs, bigMap.size());

        assertEquals(list4114Contains, bigMap.get(catalog_key_4114));
        
        assertEquals(list4118Contains, bigMap.get(catalog_key_4118));




    }

    /**
     * convenient-method
     *
     */
    private MapWrapper addToWrapper(MapWrapper instance, String key, String value) {
        instance.addToWrapper(key, value);
        return instance.getThis();
    }
}