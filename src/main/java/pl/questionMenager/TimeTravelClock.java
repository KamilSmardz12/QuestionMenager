package pl.questionMenager;


import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@EqualsAndHashCode(callSuper = true)
@Value
public class TimeTravelClock extends Clock {
    private static final String ZONE_ID = "Europe/Paris";

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
        return localDateTime.atZone(ZoneId.of(ZONE_ID)).toInstant();
    }
}
