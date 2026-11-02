package cinema.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Замовлення глядача. Роль у Builder: Product.
 * Незмінний: усі поля final, списки доступні лише для читання, сеттерів немає.
 * Конструктор пакетний: створити замовлення може лише білдер (після перевірки).
 */
public final class Order {
    private static final int SEAT_PRICE = 150;
    private static final int POPCORN_PRICE = 80;
    private static final int DRINK_PRICE = 40;
    private static final int GLASSES_PRICE = 25;
    private static final int VIP_PRICE = 100;
    private static final double PROMO_DISCOUNT = 0.10;   // будь-який промокод умовно дає -10%

    private final Screening screening;
    private final List<String> seats;
    private final int popcornPortions;
    private final List<String> drinks;
    private final boolean glasses3D;
    private final String promoCode;      // null, якщо не заданий
    private final boolean vip;

    Order(Screening screening, List<String> seats, int popcornPortions, List<String> drinks,
          boolean glasses3D, String promoCode, boolean vip) {
        this.screening = screening;
        this.seats = Collections.unmodifiableList(new ArrayList<>(seats));
        this.popcornPortions = popcornPortions;
        this.drinks = Collections.unmodifiableList(new ArrayList<>(drinks));
        this.glasses3D = glasses3D;
        this.promoCode = promoCode;
        this.vip = vip;
    }

    public Screening getScreening() { return screening; }
    public List<String> getSeats() { return seats; }
    public int getPopcornPortions() { return popcornPortions; }
    public List<String> getDrinks() { return drinks; }
    public boolean hasGlasses3D() { return glasses3D; }
    public String getPromoCode() { return promoCode; }
    public boolean isVip() { return vip; }

    public double totalPrice() {
        double sum = seats.size() * SEAT_PRICE
                + popcornPortions * POPCORN_PRICE
                + drinks.size() * DRINK_PRICE
                + (glasses3D ? seats.size() * GLASSES_PRICE : 0)
                + (vip ? VIP_PRICE : 0);
        return promoCode != null ? sum * (1 - PROMO_DISCOUNT) : sum;
    }

    /** Читабельний опис складу замовлення. */
    public String describe() {
        StringBuilder sb = new StringBuilder("  Замовлення:\n");
        sb.append("    Сеанс:       ").append(screening).append('\n');
        sb.append("    Місця:       ").append(String.join(", ", seats)).append('\n');
        sb.append("    Попкорн:     ").append(popcornPortions > 0 ? popcornPortions + " порц." : "—").append('\n');
        sb.append("    Напої:       ").append(drinks.isEmpty() ? "—" : String.join(", ", drinks)).append('\n');
        sb.append("    3D-окуляри:  ").append(glasses3D ? "так (" + seats.size() + " шт.)" : "ні").append('\n');
        sb.append("    Промокод:    ").append(promoCode != null ? promoCode + " (-10%)" : "—").append('\n');
        sb.append("    VIP:         ").append(vip ? "так" : "ні").append('\n');
        sb.append("    Разом:       ").append(String.format("%.2f грн", totalPrice()));
        return sb.toString();
    }
}
