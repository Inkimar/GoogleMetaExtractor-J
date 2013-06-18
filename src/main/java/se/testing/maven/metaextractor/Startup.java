package se.testing.maven.metaextractor;

import se.testing.maven.metaextractor.util.FilePropertiesHelper;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.testing.maven.metaextractor.exif.FileRetriever;

/**
 * Credit to : http://java.dzone.com/articles/java-example-list-all-files
 *
 * @author ingimar
 */
public class Startup {

    public static void main(String[] args) {
        System.out.println("Main");
        final String directoryLinuxMac = FilePropertiesHelper.getFilePath();

        ListFilesUtil.listFiles(directoryLinuxMac);

        FileRetriever retriever = new FileRetriever();
        retriever.test(directoryLinuxMac);
    }
}