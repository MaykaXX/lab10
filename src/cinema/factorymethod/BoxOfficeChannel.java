package cinema.factorymethod;

import cinema.builder.Screening;

/** ConcreteCreator: каса. */
public class BoxOfficeChannel extends SalesChannel {
    public BoxOfficeChannel(SalesLedger ledger) {
        super(ledger);
    }

    @Override
    protected String channelName() {
        return "Каса";
    }

    @Override
    protected Ticket createTicket(Screening screening, String seat) {
        return new PaperTicket(screening, seat);
    }
}
