package pl.questionMenager.utils;

import lombok.NonNull;
import pl.questionMenager.model.Question;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


public class TransformerUtils {
    private TransformerUtils() {
    }

    /**
     * Function sets date and time in file to now
     * @param clock date and time now
     * @param dateTimeFormatter correct date and time format
     * @return {@link String}
     */
    public static String setPresentDateAndTime(Clock clock, String dateTimeFormatter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatter);

        return formatter.format(LocalDateTime.now(clock));
    }

    /**
     * Function calculate new version to save in file.
     * First version is 0.0.1. We will increase the version every 0.0.1
     * @param actualVersion version from file
     * @return {@link String} in '\d.\d.\d' format
     */
    public static String calculateVersion(@NonNull String actualVersion) {

        String[] splittedActualVersion = actualVersion.split("\\.");

        int lastValue = Integer.parseInt(splittedActualVersion[2]);
        int middleValue = Integer.parseInt(splittedActualVersion[1]);
        int firstValue = Integer.parseInt(splittedActualVersion[0]);

        if (middleValue == 99) {
            middleValue = 0;
            firstValue++;
        }

        if (lastValue == 9) {
            middleValue++;
            lastValue = 0;
        } else {
            lastValue++;
        }

        return String.format("%d.%d.%d", firstValue, middleValue, lastValue);
    }

    /**
     * Check if file is exist
     * @param filePath path to file
     */
    public static void validateFile(@NonNull String filePath){
        if(!isFileExist(filePath)){
            throw new IllegalStateException("You have entered the wrong path to the file!");
        }
    }

    /**
     * Check if there are questions in the file
     * @param question
     */
    public static void isEmptyMap(Map<Integer, Question> question){
        if (question.isEmpty()){
            throw new IllegalStateException("There are no questions!");
        }
    }

    private static boolean isFileExist(@NonNull String filePath) {
        return Files.exists(Paths.get(filePath));
    }
}
