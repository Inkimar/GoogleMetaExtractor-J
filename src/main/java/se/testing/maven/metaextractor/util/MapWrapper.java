package se.testing.maven.metaextractor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapWrapper {

    private Map<String, List<String>> map;

    private static MapWrapper instance = null;

    protected MapWrapper() {
        map = new HashMap<>();
    }

    public static MapWrapper getInstance() {
        if (instance == null) {
            instance = new MapWrapper();
        }
        return instance;
    }

    public List<String> get(String key) {
        return map.get(key);
    }

    public void transformMap(Map<String, String> incomingMap) {
        Set<String> keySet = incomingMap.keySet();
        for (String key : keySet) {

            // Nyckeln finns
            if (map.containsKey(key)) {
                List<String> existingList = map.get(key);
                existingList.add(incomingMap.get(key));
                map.put(key, existingList);
            } else {
                List<String> views = new ArrayList<>();
                String view = incomingMap.get(key);
                views.add(view);

                map.put(key, views);
            }
        }
    }

    public Map<String, List<String>> getMap() {
        return map;
    }

    public void close() {
        map.clear();
    }

    public int size() {
        int size = map.size();
        return size;
    }
}