package model.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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