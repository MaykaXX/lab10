package cinema.builder;

import cinema.domain.Logger;

/**
 * Роль у Builder: Director. Описує типові замовлення в одному місці:
 * послідовність кроків для кожного набору записана тут і більше ніде.
 */
public class OrderDirector {
    private static final String SRC = "Директор";

    /** «Комбо на двох»: 2 місця, 1 попкорн, 2 напої. */
    public Order buildComboForTwo(OrderBuilder builder, Screening screening, String seatA, String seatB) {
        Logger.log(SRC, "Формується типове замовлення «Комбо на двох»");
        return builder.forScreening(screening)
                .addSeat(seatA)
                .addSeat(seatB)
                .withPopcorn(1)
                .addDrink("Кола")
                .addDrink("Кола")
                .build();
    }

    /** «Сімейний вечір»: усі передані місця, 2 попкорни, напій на кожне місце, промокод FAMILY. */
    public Order buildFamilyEvening(OrderBuilder builder, Screening screening, String... seats) {
        Logger.log(SRC, "Формується типове замовлення «Сімейний вечір»");
        builder.forScreening(screening);
        for (String seat : seats) {
            builder.addSeat(seat).addDrink("Сік");
        }
        return builder.withPopcorn(2)
                .withPromoCode("FAMILY")
                .build();
    }
}
