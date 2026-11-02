package cinema.factorymethod;

import cinema.builder.Screening;

/** ConcreteProduct: паперовий квиток, який друкує каса. */
public class PaperTicket implements Ticket {
    private final Screening screening;
    private final String seat;

    public PaperTicket(Screening screening, String seat) {
        this.screening = screening;
        this.seat = seat;
    }

    @Override
    public String render() {
        return "ПАПЕРОВИЙ квиток (друк на касовому принтері): " + screening + ", місце " + seat;
    }
}
