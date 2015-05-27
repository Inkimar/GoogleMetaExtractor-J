package se.testing.maven.metaextractor.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Catalognr är nyckeln, äger en lista som är alla dess vyer.
 *
 * @author ingimar
 */
public class MapWrapper {

    private Map<String, List<String>> map;

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

    public List<String> get(String key) {
        return map.get(key);
    }

    public int getNumberOfCatalogs() {
        int size = map.size();
        return size;
    }

    /**
     * Fetching the Catalog-numbers.
     *
     * @return catalognumbers
     */
    public List<String> getListOfCatalogs() {
        Set<String> catalogs = getCatalogs();
        return new ArrayList<>(catalogs);
    }

    private Set<String> getCatalogs() {
        Set<String> keySet = map.keySet();
        return keySet;
    }

    /**
     * Fetching the Catalog-numbers, ordered.
     *
     * @return catalognumbers
     */
    public List<String> getSortedListOfCatalogs() {
        Set<String> catalogs = getSortedCatalogs();
        return new ArrayList<>(catalogs);
    }

    private Set<String> getSortedCatalogs() {
        TreeMap map = new TreeMap(getMap());
        return map.keySet();
    }

    /**
     * Hit kan jag mata alla xxxx bilderna och får tillbaka en full MapWrapper
     * ...
     *
     * @param fileNames
     * @return
     */
    public static MapWrapper getPopulatedMapWrapper(List<String> fileNames) {
        MapWrapper container = new MapWrapper();
        Map<String, String> map = new HashMap();
        for (String fileName : fileNames) {
            Map parsed = ListFilesUtil.parseFileName(fileName);
            // Map parsed = ListFilesUtil.parseTestFileName(fileName,map);
            container.transformMap(parsed);
        }
        return container;
    }

    public MapWrapper getThis() {
        return this;
    }
}