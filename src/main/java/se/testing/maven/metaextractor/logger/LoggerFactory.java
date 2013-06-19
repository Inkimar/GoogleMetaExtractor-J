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
import se.testing.maven.metaextractor.util.FilePropertiesHelper;

/**
 *
 * @author ingimar
 */
public class LoggerFactory {

    private final Level level = Level.INFO;

    private static LoggerFactory instance = new LoggerFactory();

    private LoggerFactory() {
    }

    public static synchronized LoggerFactory getInstance() {
        if (instance == null) {
            instance = new LoggerFactory();
        }
        return instance;
    }
    

    public Logger getLogger() {
        String filename = FilePropertiesHelper.getLogNameWithPrefix();
        Logger logger = getLogger(filename);
        return logger;
    }

    public Logger getLogger(String filename) {

        LogManager manager = LogManager.getLogManager();

        Logger logger;
        FileHandler handler = null;
        try {
            handler = new FileHandler(filename);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
        }

        logger = Logger.getLogger("GoogleMetaExtractor");

        manager.addLogger(logger);
        logger.setLevel(level);
        handler.setFormatter(new XMLFormatter());

        logger.addHandler(handler);
        logger.log(Level.INFO, "Starting up the logger");

        return logger;
    }
}
