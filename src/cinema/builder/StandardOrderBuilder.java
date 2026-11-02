package cinema.builder;

import cinema.domain.Logger;

import java.util.ArrayList;
import java.util.List;

/** Роль у Builder: ConcreteBuilder. Збирає частини й перевіряє їх у build(). */
public class StandardOrderBuilder implements OrderBuilder {
    private static final String SRC = "Конструктор замовлення";

    private Screening screening;
    private final List<String> seats = new ArrayList<>();
    private int popcornPortions;
    private final List<String> drinks = new ArrayList<>();
    private boolean glasses3D;
    private String promoCode;
    private boolean vip;

    @Override
    public OrderBuilder forScreening(Screening screening) {
        this.screening = screening;
        Logger.log(SRC, "Обрано сеанс: " + screening);
        return this;
    }

    @Override
    public OrderBuilder addSeat(String seat) {
        if (seats.contains(seat)) {
            Logger.log(SRC, "Місце " + seat + " уже додано, пропущено");
            return this;
        }
        seats.add(seat);
        Logger.log(SRC, "Додано місце " + seat);
        return this;
    }

    @Override
    public OrderBuilder withPopcorn(int portions) {
        this.popcornPortions = portions;
        Logger.log(SRC, "Додано попкорн: " + portions + " порц.");
        return this;
    }

    @Override
    public OrderBuilder addDrink(String drink) {
        drinks.add(drink);
        Logger.log(SRC, "Додано напій: " + drink);
        return this;
    }

    @Override
    public OrderBuilder with3DGlasses() {
        this.glasses3D = true;
        Logger.log(SRC, "Додано 3D-окуляри");
        return this;
    }

    @Override
    public OrderBuilder withPromoCode(String code) {
        this.promoCode = code;
        Logger.log(SRC, "Застосовано промокод " + code);
        return this;
    }

    @Override
    public OrderBuilder withVip() {
        this.vip = true;
        Logger.log(SRC, "Додано VIP-обслуговування");
        return this;
    }

    @Override
    public Order build() {
        if (screening == null) fail("не обрано сеанс");
        if (seats.isEmpty()) fail("не обрано жодного місця");
        Order order = new Order(screening, seats, popcornPortions, drinks, glasses3D, promoCode, vip);
        Logger.log(SRC, "Замовлення сформовано, місць: " + seats.size());
        return order;
    }

    private void fail(String reason) {
        String message = "Неможливо сформувати замовлення: " + reason;
        Logger.log(SRC, message);
        throw new IllegalStateException(message);
    }
}
