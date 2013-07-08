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

    @Test
    public void oneViewOneKey() {
        System.out.println("one view one key");
       MapWrapper firstInstance = new MapWrapper();

        final String commonKey = "NHRS-GULI000004114";
        final String value = "face";

        Map<String, String> incomingMap = new HashMap<>();
        incomingMap.put(commonKey, value);

        firstInstance.transformMap(incomingMap);
        Map<String, List<String>> bigMap = firstInstance.getMap();

        assertTrue(bigMap.containsKey(commonKey));

        int catalogSize = bigMap.size();
        assertEquals(1, catalogSize);

        List<String> views = bigMap.get(commonKey);
        assertTrue(views.contains(value));

        int viewSize = views.size();
        for (String view : views) {
        System.out.println("1st test "+view);
            
        }
        assertEquals(1, viewSize);

    }

    @Test
    public void twoViewsOneKey() {
        System.out.println("two views one key");
        MapWrapper secondInstance = new MapWrapper();

        final String commonKey = "NHRS-GULI000004114";

        final String faceValue = "face";
        {
            Map<String, String> faceMap = new HashMap<>();
            faceMap.put(commonKey, faceValue);
            secondInstance.transformMap(faceMap);
        }

        final String dorsValue = "dors";
        {
            Map<String, String> dorsMap = new HashMap<>();
            dorsMap.put(commonKey, dorsValue); // ersatte den förra
            secondInstance.transformMap(dorsMap);
        }

        Map<String, List<String>> bigMap = secondInstance.getMap();

        assertTrue(bigMap.containsKey(commonKey));

        List<String> views = bigMap.get(commonKey);
        assertEquals(2, views.size());
        assertTrue(views.contains(faceValue));

    }
    
}