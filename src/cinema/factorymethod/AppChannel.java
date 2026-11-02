package cinema.factorymethod;

import cinema.builder.Screening;

/** ConcreteCreator: мобільний застосунок. */
public class AppChannel extends SalesChannel {
    public AppChannel(SalesLedger ledger) {
        super(ledger);
    }

    @Override
    protected String channelName() {
        return "Застосунок";
    }

    @Override
    protected Ticket createTicket(Screening screening, String seat) {
        return new WalletTicket(screening, seat);
    }
}
