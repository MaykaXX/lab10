package cinema.builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Сеанс (спрощено: фільм, зала, час). Незмінний. */
public final class Screening {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd.MM HH:mm");

    private final String film;
    private final int hall;
    private final LocalDateTime start;

    public Screening(String film, int hall, LocalDateTime start) {
        this.film = film;
        this.hall = hall;
        this.start = start;
    }

    @Override
    public String toString() {
        return film + ", зала " + hall + ", " + start.format(FMT);
    }
}
