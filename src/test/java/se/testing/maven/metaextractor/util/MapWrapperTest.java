/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author ingimar
 */
public class MapWrapperTest {

    public MapWrapperTest() {
    }

    @Test @Ignore
    public void ADD_1_VIEW_TO_ONE_KEY_Map() {
        System.out.println("ADD_1_VIEW_TO_ONE_KEY_Map");
        MapWrapper instance = MapWrapper.getInstance();

        final String commonKey = "NHRS-GULI000004114";
        final String value = "face";

        Map<String, String> incomingMap = new HashMap<>();
        incomingMap.put(commonKey, value);

        instance.transformMap(incomingMap);
        Map<String, List<String>> bigMap = instance.getMap();

        assertTrue(bigMap.containsKey(commonKey));

        int catalogSize = bigMap.size();
        assertEquals(1, catalogSize);

        List<String> views = bigMap.get(commonKey);
        assertTrue(views.contains(value));

        int viewSize = views.size();
        assertEquals(1, viewSize);

        instance.close();
    }

    @Test
    public void ADD_2_VIEWS_TO_ONE_KEY_Map() {
        System.out.println("ADD_2_VIEW_TO_ONE_KEY_Map");
        MapWrapper instance = MapWrapper.getInstance();

        final String commonKey = "NHRS-GULI000004114";

        final String faceValue = "face";
        {
            Map<String, String> faceMap = new HashMap<>();
            faceMap.put(commonKey, faceValue);
            instance.transformMap(faceMap);
        }

        final String dorsValue = "dors";
        {
            Map<String, String> dorsMap = new HashMap<>();
            dorsMap.put(commonKey, dorsValue); // ersatte den förra
            instance.transformMap(dorsMap);
        }

        Map<String, List<String>> bigMap = instance.getMap();

        assertTrue(bigMap.containsKey(commonKey));

        List<String> views = bigMap.get(commonKey);
        assertEquals(2, views.size());
        assertTrue(views.contains(faceValue));

    }
}