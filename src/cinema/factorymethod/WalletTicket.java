package cinema.factorymethod;

import cinema.builder.Screening;

/** ConcreteProduct: електронний квиток у гаманці застосунку. */
public class WalletTicket implements Ticket {
    private final Screening screening;
    private final String seat;

    public WalletTicket(Screening screening, String seat) {
        this.screening = screening;
        this.seat = seat;
    }

    @Override
    public String render() {
        return "ЕЛЕКТРОННИЙ квиток у гаманці застосунку: " + screening + ", місце " + seat;
    }
}
