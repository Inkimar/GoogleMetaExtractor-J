package se.testing.maven.metaextractor.exif;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.testing.maven.metaextractor.ListFilesUtil;
import se.testing.maven.metaextractor.Startup;
import se.testing.maven.metaextractor.logger.LoggerFactory;

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
                    Metadata metadata = ImageMetadataReader.readMetadata(file);
                    ExifSubIFDDirectory exifDirectory = getExifDirectory(metadata);
                    list = getAllTags(metadata, isFilteredFromUnknown);

                } catch (ImageProcessingException | IOException ex) {
                    Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
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
                    Metadata metadata = ImageMetadataReader.readMetadata(file);
                    ExifSubIFDDirectory exifDirectory = getExifDirectory(metadata);

                    list = getAllTags(metadata, isFilteredFromUnknown);

                } catch (ImageProcessingException | IOException ex) {
                    Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
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
    private List<String> getAllTags(Metadata metadata, boolean isFiltered) {

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
    private LinkedHashMap<String, String> getMappedTags(Metadata metadata, boolean isFiltered) {

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

    /**
     * proof of concept. Testing the Logging-functionality, only.
     *
     * @param directoryLinuxMac
     * @param filter
     */
    @Deprecated
    private Logger logging() {
        LoggerFactory factory = LoggerFactory.getInstance();
        Logger logger = factory.getLogger();

        return logger;
    }
}
