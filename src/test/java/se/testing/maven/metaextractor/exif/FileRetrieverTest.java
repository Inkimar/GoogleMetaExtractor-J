/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.exif;

import com.google.common.collect.Maps;
import java.io.File;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import se.testing.maven.metaextractor.ListFilesUtil;
import se.testing.maven.metaextractor.util.FilePropertiesHelper;

/**
 *
 * @author ingimar
 */
public class FileRetrieverTest {

    final String fileNameSuffix = ".CR2";

    public FileRetrieverTest() {
    }

    @Test
    public void testGetSingleMappedTags() {
        FileRetriever instance = new FileRetriever();

        try {
            File file = getFileFilterOnSuffix();
            final String name = file.getName();
            assertTrue(name.contains(fileNameSuffix));

            LinkedHashMap<String, String> map = instance.getMappedTags(file, true);
            assertEquals("Canon", map.get("Make"));
            assertTrue(map.containsKey("Make"));
            assertTrue(map.containsKey("Artist"));
            assertTrue(map.containsValue("Gunvi Lindberg"));


        } catch (Exception ex) {
            Logger.getLogger(FileRetrieverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Skapade en map med en map i ...
     * Så (<Filnam>, <dessa metadata i map> )
     */
    @Test
    public void mappedTags_FOR_Multiple_files() {
        FileRetriever instance = new FileRetriever();

        try {
            File[] allFiles = getAllFiles();
            LinkedHashMap<String, LinkedHashMap> mapAllFiles = Maps.newLinkedHashMap();
            LinkedHashMap<String, LinkedHashMap> mapFilteredFiles = Maps.newLinkedHashMap();

            for (File file : allFiles) {
                LinkedHashMap<String, String> mapMetaData = instance.getMappedTags(file, true);
                mapAllFiles.put(file.getName(), mapMetaData);
                if ( file.getName().contains(fileNameSuffix)){
                    mapFilteredFiles.put(file.getName(), mapMetaData);
                }
            }
            
            assertEquals(allFiles.length,mapAllFiles.size());
            Collection<LinkedHashMap> values = mapFilteredFiles.values();
            for (LinkedHashMap map : values) {
                System.out.println("filtered map "+map.get("Artist"));   
            }
            


        } catch (Exception ex) {
            Logger.getLogger(FileRetrieverTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private File getFileFilterOnSuffix() {
        final String directoryLinuxMac = FilePropertiesHelper.getFilePath();
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);
        for (File file : files) {
            if (file.isFile() && file.getName().contains(fileNameSuffix)) {
                return file;
            }
        }
        return null;
    }

    private File[] getAllFiles() {
        final String directoryLinuxMac = FilePropertiesHelper.getFilePath();
        File[] files = ListFilesUtil.getFiles(directoryLinuxMac);

        return files;
    }
}