package pl.questionMenager;


import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Class to mock time
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class TimeTravelClock extends Clock {

    private final LocalDateTime localDateTime;

    @Override
    public ZoneId getZone() {
        return ZoneId.systemDefault();
    }

    @Override
    public Clock withZone(ZoneId zoneId) {
        return null;
    }

    @Override
    public Instant instant() {
        return localDateTime.atZone(ZoneId.of("Europe/Paris")).toInstant();
    }
}
