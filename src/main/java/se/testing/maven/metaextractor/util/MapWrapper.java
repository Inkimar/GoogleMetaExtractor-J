package se.testing.maven.metaextractor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapWrapper {

    private Map<String, List<String>> map;

//    private static MapWrapper instance = null;
    public MapWrapper() {
        map = new HashMap<>();
    }

    public void transformMap(Map<String, String> incomingMap) {
        Set<String> keySet = incomingMap.keySet();

        for (String key : keySet) {
            if (map.containsKey(key)) {
                List<String> existingList = map.get(key);
                String value = incomingMap.get(key);
                existingList.add(value);
                map.put(key, existingList);
            } else {
                List<String> views = new ArrayList<>();
                String view = incomingMap.get(key);
                views.add(view);
                map.put(key, views);
            }
        }
    }

    public void addToWrapper(String key, String value) {
        Map<String, String> map = new HashMap<>();
        map.put(key, value);
        transformMap(map);
    }

    public Map<String, List<String>> getMap() {
        return map;
    }
    public List<String> get(String key){
        return map.get(key);
    }

    public int size() {
        int size = map.size();
        return size;
    }

    public MapWrapper getThis() {
        return this;
    }
}