package cinema.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/** Консольний журнал подій у форматі [час] [джерело] подія. Час умовний. */
public final class Logger {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static LocalTime clock = LocalTime.of(18, 0, 0);

    private Logger() {}

    public static synchronized void log(String source, String event) {
        clock = clock.plusSeconds(1);
        System.out.printf("[%s] [%s] %s%n", clock.format(FMT), source, event);
    }
}
