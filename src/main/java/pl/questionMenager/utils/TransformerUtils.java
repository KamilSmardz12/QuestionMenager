package pl.questionMenager.utils;

import lombok.NonNull;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import pl.questionMenager.crud.database.DataBaseCrud;
import pl.questionMenager.crud.file.JsonCrud;
import pl.questionMenager.model.DataType;
import pl.questionMenager.model.Question;
import pl.questionMenager.transformer.database.DataBaseTransformerFactory;
import pl.questionMenager.transformer.file.JsonTransformerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Log
public class TransformerUtils {
    private TransformerUtils() {
    }

    /**
     * Function sets date and time in file to now
     *
     * @param clock             date and time now
     * @param dateTimeFormatter correct date and time format
     * @return {@link String}
     */
    public static String setPresentDateAndTime(Clock clock, String dateTimeFormatter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatter);
        String dateAndTime = formatter.format(LocalDateTime.now(clock));
        log.info("Save date and time: " + dateAndTime);

        return dateAndTime;
    }

    /**
     * Function calculate new version to save in file.
     * First version is 0.0.1. We will increase the version every 0.0.1
     *
     * @param actualVersion version from file
     * @return {@link String} in '\d.\d.\d' format
     */
    public static String calculateVersion(@NonNull String actualVersion) {
        String[] splittedActualVersion = actualVersion.split("\\.");

        int lastValue = Integer.parseInt(splittedActualVersion[2]);
        int middleValue = Integer.parseInt(splittedActualVersion[1]);
        int firstValue = Integer.parseInt(splittedActualVersion[0]);

        String versionBeforeUpdate = String.format("%d.%d.%d", firstValue, middleValue, lastValue);

        if (lastValue == 9) {
            middleValue++;
            lastValue = 0;
        } else {
            lastValue++;
        }

        if (middleValue == 10) {
            middleValue = 0;
            firstValue++;
        }

        String updatedVersion = String.format("%d.%d.%d", firstValue, middleValue, lastValue);
        log.info(String.format("%s%s%s%s", "Update version: ", versionBeforeUpdate, "-->", updatedVersion));

        return updatedVersion;
    }

    /**
     * Check if file is exist
     *
     * @param filePath path to file
     */
    public static void validateFile(@NonNull String filePath) {
        if (!isFileExist(filePath)) {
            throw new IllegalStateException("You have entered the wrong path to the file!");
        }
    }

    /**
     * Check if there are questions in the file
     *
     * @param questions questions from data
     */
    public static void isEmptyMap(Map<Integer, Question> questions) {
        if (questions.isEmpty()) {
            throw new IllegalStateException("There are no questions!");
        }
    }

    private static boolean isFileExist(@NonNull String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    public static boolean isJsonData(DataType dataType) {
        return dataType.equals(DataType.JSON);
    }

}
