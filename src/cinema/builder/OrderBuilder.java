package cinema.builder;

/** Builder: крок за кроком задає частини замовлення. Кожен крок повертає білдер (ланцюжок викликів). */
public interface OrderBuilder {
    OrderBuilder forScreening(Screening screening);
    OrderBuilder addSeat(String seat);
    OrderBuilder withPopcorn(int portions);
    OrderBuilder addDrink(String drink);
    OrderBuilder with3DGlasses();
    OrderBuilder withPromoCode(String code);
    OrderBuilder withVip();

    /** Завершальний крок: перевіряє обов'язкові частини й повертає готове замовлення. */
    Order build();
}
