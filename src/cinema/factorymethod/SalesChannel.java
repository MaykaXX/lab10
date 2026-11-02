package cinema.factorymethod;

import cinema.builder.Screening;
import cinema.domain.Logger;

/**
 * Роль у Factory Method: Creator.
 * Процес продажу (sell) описаний один раз і оголошений final: резерв → оплата → квиток → запис.
 * Єдине, що відрізняється між каналами, — фабричний метод createTicket().
 * Тут немає жодного розгалуження за каналом і жодного конкретного класу квитка.
 */
public abstract class SalesChannel {
    protected static final int TICKET_PRICE = 150;

    private final SalesLedger ledger;

    protected SalesChannel(SalesLedger ledger) {
        this.ledger = ledger;
    }

    /** Назва каналу для журналу. */
    protected abstract String channelName();

    /** Фабричний метод: вид квитка визначає сам канал. */
    protected abstract Ticket createTicket(Screening screening, String seat);

    /** Загальний процес продажу. Не змінюється при підключенні нових каналів. */
    public final Ticket sell(Screening screening, String seat) {
        String src = channelName();
        Logger.log(src, "Початок продажу: " + screening + ", місце " + seat);
        reserveSeat(src, seat);
        acceptPayment(src, TICKET_PRICE);
        Ticket ticket = createTicket(screening, seat);
        Logger.log(src, "Видано квиток: " + ticket.render());
        ledger.add(src, seat, TICKET_PRICE);
        Logger.log(src, "Продаж записано в журнал, усього продажів: " + ledger.total());
        return ticket;
    }

    private void reserveSeat(String src, String seat) {
        Logger.log(src, "Місце " + seat + " зарезервовано");
    }

    private void acceptPayment(String src, int amount) {
        Logger.log(src, "Оплату прийнято: " + amount + " грн");
    }
}
