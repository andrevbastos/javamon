package model.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is responsible for handling the logging of events in the game.
 * It provides methods to add messages to the log and create a log file.
 * The log file is created in the "logs" directory.
 * The log file is named "log.txt" and contains all the winners and moves
 * made during the game.
 * 
 * @see controller.Simulation
 */
public class LogHandler {
    private String logString = "";

    public void addToLog(String txt) {
        logString += txt;
    }

    public void createLog() {
        try {        
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            String logFilename = "logs/log.txt";
            try (BufferedWriter w = new BufferedWriter(new FileWriter(logFilename, false))) {
                w.write(logString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}