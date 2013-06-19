package se.testing.maven.metaextractor.exif;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        System.out.println("Fetching Files");
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

        System.out.println("List is " + list);
        return list;
    }
    public List fetchMetaDataFromImage(String directoryLinuxMac) {
        System.out.println("Fetching Files");
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
        List<String> list = new ArrayList<>();
        
        boolean isFilteredFromUnknown = true;

        for (File file : files) {
                if (file.isFile()) {
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

        System.out.println("List is " + list);
        return list;
    }

    private ExifSubIFDDirectory getExifDirectory(Metadata metadata) {
        ExifSubIFDDirectory directory = metadata.getDirectory(ExifSubIFDDirectory.class);
        return directory;

    }

    private List<String> getAllTags(Metadata metadata, boolean isFiltered) {

        List<String> tagList = new ArrayList<>();
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.println("tag is : " +tag);
                String tagName = tag.getTagName();

                if (isFiltered) {
                    if (!tagName.contains("Unknown")) {
                        tagList.add(tagName);
                    }
                } else {
                    tagList.add(tagName);
                }
            }
        }
        return tagList;
    }
}
