package pl.questionMenager.TransformerUtils

import pl.questionMenager.TimeTravelClock
import pl.questionMenager.utils.TransformerUtils
import spock.lang.Specification

import java.time.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class TransformerUtilsTest extends Specification {

    LocalDateTime localDateTime = LocalDateTime.of(LocalDate.of(2019, 11, 1), LocalTime.of(23, 9, 00, 00))

    private final Clock CLOCK = new TimeTravelClock(localDateTime)
    private final String DATE_TIME_FORMATTER = "dd-MM-yy HH:mm"


    def "check if date and time will be set in correct format"() {
        given:
        String lastUpdate

        when:
        lastUpdate = TransformerUtils.setPresentDateAndTime(CLOCK, DATE_TIME_FORMATTER)

        then:
        lastUpdate == "01-11-19 23:09"
    }
}
