package cinema.factorymethod;

import cinema.builder.Screening;

/** Новий ConcreteCreator: термінал самообслуговування. Підключається без змін у SalesChannel. */
public class SelfServiceTerminalChannel extends SalesChannel {
    public SelfServiceTerminalChannel(SalesLedger ledger) {
        super(ledger);
    }

    @Override
    protected String channelName() {
        return "Термінал";
    }

    @Override
    protected Ticket createTicket(Screening screening, String seat) {
        return new TerminalTicket(screening, seat);
    }
}
