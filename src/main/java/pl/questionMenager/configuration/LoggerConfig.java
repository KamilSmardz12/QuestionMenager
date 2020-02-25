package pl.questionMenager.configuration;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerConfig {
    private static final String FILE_PATH = "src/resources/questionMenager.log";

    public static Logger log(){
        Logger logger = Logger.getLogger("LOGGER");

        try {
            FileHandler fileHandler = new  FileHandler(FILE_PATH,true);
            logger.addHandler(fileHandler);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logger;
    }
}
