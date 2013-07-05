package se.testing.maven.metaextractor.exif;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.testing.maven.metaextractor.ListFilesUtil;
import se.testing.maven.metaextractor.Startup;

/**
 *
 * @author ingimar
 */
public class FileRetriever {

    public List fetchMetaDataFromImage(String directoryLinuxMac, String filter) {

        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
        List<String> list = new ArrayList<>();

        boolean isFilteredFromUnknown = true;

        for (File file : files) {
            if (file.isFile() && file.getName().contains(filter)) {
                System.out.println(file.getName());
                try {
                    list = getAllTags(file, isFilteredFromUnknown);

                } catch (ImageProcessingException | IOException ex) {
                    Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(FileRetriever.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return list;
    }

    public List fetchMetaDataFromImage(String directoryLinuxMac) {
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
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
                    Logger.getLogger(FileRetriever.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    private ExifSubIFDDirectory getExifDirectory(Metadata metadata) {
        ExifSubIFDDirectory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
        return directory;

    }

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
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                String tagName = tag.getTagName();


                if (isFiltered) {
                    if (!tagName.contains("Unknown")) {
                        String tagDescription = tag.getDescription();
                        map.put(tagName, tagDescription);
                        System.out.println("tag is : " + tag);
                        tagList.add(tagName);
                    }
                } else {
                    tagList.add(tagName);
                }
            }
        }
        return tagList;
    }

    /**
     * If filtering is true then it filters away alla 'Unkown' tags. Handles one
     * file at a time.
     *
     * @param metadata
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
