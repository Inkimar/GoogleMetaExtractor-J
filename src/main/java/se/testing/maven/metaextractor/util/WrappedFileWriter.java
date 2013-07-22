/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author ingimar
 */
public class WrappedFileWriter {

    public static void writeToFile(String text) {
        String topLevelFilePath = FilePropertiesHelper.getTopLevelFilePath();
        String logFileName = FilePropertiesHelper.getLogNameWithPrefix(false);
        String fileName = topLevelFilePath + "/" +logFileName;
        
        System.out.println("fileName :"+fileName);
       
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName), true);

            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(text);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
        }
    }
}
