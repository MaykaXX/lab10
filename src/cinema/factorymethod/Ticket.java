package cinema.factorymethod;

/** Роль у Factory Method: Product. Спільна абстракція квитка; процес продажу знає лише її. */
public interface Ticket {
    /** Вигляд квитка для журналу. */
    String render();
}
