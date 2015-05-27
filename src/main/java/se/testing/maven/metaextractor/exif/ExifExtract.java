package se.testing.maven.metaextractor.exif;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import se.testing.maven.metaextractor.util.ListFilesUtil;
import se.testing.maven.metaextractor.Startup;

/**
 *
 * @author ingimar
 */
public class ExifExtract {

    public List<JSONObject>  transformToJSON(String directory, String filter) {

        File[] files = ListFilesUtil.getFiles(directory);
        JSONObject jsonObject = null;
        boolean isFilteredFromUnknown = true;

        List<JSONObject> listOfJSONobjects = new ArrayList<>();
        for (File file : files) {
            if (file.isFile() && file.getName().contains(filter)) {
                System.out.println(file.getName());
                try {
                    if (filter.equals(".mp4")) {

                    } else {
                        jsonObject = getExifTagAsJSON(file, isFilteredFromUnknown);
                        listOfJSONobjects.add(jsonObject);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ExifExtract.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return listOfJSONobjects;
    }

    public List fetchMetaDataFromImage(String directory, String filter) {

        File[] files = ListFilesUtil.getFiles(directory);
        List<String> list = new ArrayList<>();

        boolean isFilteredFromUnknown = true;

        for (File file : files) {
            if (file.isFile() && file.getName().contains(filter)) {
                System.out.println(file.getName());
                try {
                    list = getAllTags(file, isFilteredFromUnknown);
                } catch (Exception ex) {
                    Logger.getLogger(ExifExtract.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return list;
    }

    public List fetchMetaDataFromImage(String directory) {
        File[] files = ListFilesUtil.getFiles(directory);
        List<String> list = new ArrayList<>();

        boolean isFilteredFromUnknown = true;

        for (File file : files) {
            if (file.isFile()) {
                String filename = file.getName();
                System.out.println(filename); // prints out the name of the file inlcudes suffix
                try {
                    list = getAllTags(file, isFilteredFromUnknown);
                } catch (ImageProcessingException | IOException ex) {
                    Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ExifExtract.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

//    private ExifSubIFDDirectory getExifDirectory(Metadata metadata) {
//        ExifSubIFDDirectory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
//        return directory;
//
//    }
    /**
     * If filtering is true then it filters away alla 'Unkown' tags. Handles one
     * file at a time.
     *
     * @param metadata
     * @param isFiltered
     * @return only the present tags.
     */
    private List<String> getAllTags(File file, boolean isFiltered) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        List<String> tagList = new ArrayList<>();
        LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
        int count = 1;
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                if (isFiltered) {
                    if (!tagName.contains("Unknown")) {
                        String tagDescription = tag.getDescription();
                        map.put(tagName, tagDescription);
                        System.out.println(count + " " + tag);
                        tagList.add(tagName);
                    }
                } else {
                    tagList.add(tagName);
                }
                count++;
            }
        }

        return tagList;
    }

    private JSONObject getExifTagAsJSON(File file, boolean isFiltered) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String tagDescription = "";
                if (isFiltered) {
                    if (!tagName.contains("Unknown")) {
                        tagDescription = tag.getDescription();
                        map.put(tagName, tagDescription);
                    }
                } else {
                    map.put(tagName, tagDescription);
                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        return jsonObject;
    }
    
     private JSONObject xx(File file, boolean isFiltered) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String tagDescription = "";
                if (isFiltered) {
                    if (!tagName.contains("Unknown")) {
                        tagDescription = tag.getDescription();
                        map.put(tagName, tagDescription);
                    }
                } else {
                    map.put(tagName, tagDescription);
                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);

        try (java.io.FileWriter write = new java.io.FileWriter("/home/ingimar/mediaserver/demo-filer/test-extraction/test.json")) {
            write.write(jsonObject.toJSONString());
            write.flush();
        }

        return jsonObject;
    }

    /**
     * If filtering is true then it filters away alla 'Unkown' tags. Handles one
     * file at a time.
     *
     * @param isFiltered
     * @return only the present tags.
     */
    // ska vara private, protected så att jag kan test - möjligt att testa private metoder med groovy ...
    protected LinkedHashMap<String, String> getMappedTags(File file, boolean isFiltered) throws Exception {
        Metadata metadata = ImageMetadataReader.readMetadata(file);
        LinkedHashMap<String, String> map = Maps.newLinkedHashMap();
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();
                String tagDescription = tag.getDescription();

                if (isFiltered) {
                    if (!tagName.contains("Unknown")) {
                        map.put(tagName, tagDescription);
                    }
                } else {
                    map.put(tagName, tagDescription);
                }
            }
        }
        return map;
    }

}
