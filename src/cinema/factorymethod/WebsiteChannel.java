package cinema.factorymethod;

import cinema.builder.Screening;

/** ConcreteCreator: сайт. */
public class WebsiteChannel extends SalesChannel {
    private int counter = 0;

    public WebsiteChannel(SalesLedger ledger) {
        super(ledger);
    }

    @Override
    protected String channelName() {
        return "Сайт";
    }

    @Override
    protected Ticket createTicket(Screening screening, String seat) {
        counter++;
        return new PdfQrTicket(screening, seat, "WEB-" + String.format("%04d", counter));
    }
}
