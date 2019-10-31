package pl.questionMenager.Utils;

import lombok.NonNull;
import pl.questionMenager.TimeTravelClock;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransformerUtils {
    private TransformerUtils() {
    }

    //TODO testes
    public static String setPresentDateAndTime(Clock clock, String dateTimeFormatter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatter);

        return formatter.format(LocalDateTime.now(clock));
    }

    //TODO testes
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

    public static void validateFile(@NonNull String filePath){
        if(!isFileExist(filePath)){
            throw new IllegalStateException("You have entered the wrong path to the file!");
        }
    }

    private static boolean isFileExist(@NonNull String filePath) {
        return Files.exists(Paths.get(filePath));
    }
}
