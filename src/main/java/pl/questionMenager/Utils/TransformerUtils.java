package pl.questionMenager.Utils;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransformerUtils {

    private TransformerUtils(){};

    //TODO testes
    public static String setPresentDateAndTime(Clock clock, String dateTimeFormatter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatter);

        return formatter.format(LocalDateTime.now(clock));
    }

    //TODO testes
    public static String calculateVersion(String actualVersion) {

        String[] splitedActualVersion = actualVersion.split("\\.");

        int lastValue = Integer.parseInt(splitedActualVersion[2]);
        int middleValue = Integer.parseInt(splitedActualVersion[1]);
        int firstValue = Integer.parseInt(splitedActualVersion[0]);

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
}
