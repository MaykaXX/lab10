package cinema.factorymethod;

import cinema.builder.Screening;
import cinema.factorymethod.Ticket;

/**
 * Новий ConcreteProduct: квиток термінала самообслуговування (чек із штрихкодом).
 * Лежить в окремому пакеті: наявний код продажу не змінювався.
 */
public class TerminalTicket implements Ticket {
    private final Screening screening;
    private final String seat;

    public TerminalTicket(Screening screening, String seat) {
        this.screening = screening;
        this.seat = seat;
    }

    @Override
    public String render() {
        return "ЧЕК-КВИТОК термінала зі штрихкодом: " + screening + ", місце " + seat;
    }
}
