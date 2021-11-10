package com.qsmy.test.delay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author qsmy
 */
@Data
@ToString
@NoArgsConstructor
public class DelayObject implements Delayed {

    private String name;
    private LocalDateTime time;

    public DelayObject(String name, Long time) {
        this.name = name;
        this.time = LocalDateTime.now().plus(time, ChronoUnit.MILLIS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        int diff = time.compareTo(LocalDateTime.now());
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return time.compareTo(((DelayObject) o).time);
    }
}
