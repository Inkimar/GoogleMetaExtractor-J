/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.testing.maven.metaextractor.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;
import se.testing.maven.metaextractor.Startup;

/**
 *
 * @author ingimar
 */
public class LoggerFactory {

    private LoggerFactory() {
    }

    public static Logger getLogger(Level level, String filename) {
        LogManager manager = LogManager.getLogManager();
        
        Logger logger;
        FileHandler handler = null;
        try {
            handler = new FileHandler(filename);
        } catch (IOException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }

        logger = Logger.getLogger("GoogleMetaExtractor");

        manager.addLogger(logger);
        logger.setLevel(level);
        handler.setFormatter(new XMLFormatter());

        logger.addHandler(handler);
        logger.log(Level.INFO, "test 1");

        return logger;
    }
}
