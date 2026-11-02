package cinema;

import cinema.builder.Order;
import cinema.builder.OrderDirector;
import cinema.builder.Screening;
import cinema.builder.StandardOrderBuilder;
import cinema.domain.Logger;

import java.io.PrintStream;
import java.time.LocalDateTime;

/** Клас-раннер лабораторної №10. Окремий метод для кожної ситуації. */
public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        task1Builder();
    }

    // ---------- Завдання 1: Builder ----------
    static void task1Builder() {
        System.out.println("=== Завдання 1. Замовлення глядача (Builder) ===");
        Screening evening = new Screening("Дюна", 2, LocalDateTime.of(2026, 11, 1, 19, 0));

        // Сценарій 1: замовлення на два місця з попкорном, напоями і промокодом (крок за кроком)
        Logger.log("Раннер", "Сценарій 1: замовлення на два місця з попкорном, напоями і промокодом");
        Order manual = new StandardOrderBuilder()
                .forScreening(evening)
                .addSeat("5-7")
                .addSeat("5-8")
                .withPopcorn(1)
                .addDrink("Кола")
                .addDrink("Лимонад")
                .withPromoCode("KADR10")
                .build();
        System.out.println(manual.describe());

        // Сценарій 2: типові замовлення через директора
        OrderDirector director = new OrderDirector();
        Logger.log("Раннер", "Сценарій 2: типове замовлення «Комбо на двох»");
        Order combo = director.buildComboForTwo(new StandardOrderBuilder(), evening, "6-3", "6-4");
        System.out.println(combo.describe());

        Logger.log("Раннер", "Типове замовлення «Сімейний вечір»");
        Order family = director.buildFamilyEvening(new StandardOrderBuilder(), evening, "7-1", "7-2", "7-3", "7-4");
        System.out.println(family.describe());

        // Сценарій 3: спроба завершити формування без місць (є лише попкорн і промокод)
        Logger.log("Раннер", "Сценарій 3: спроба завершити формування без місць");
        try {
            new StandardOrderBuilder()
                    .forScreening(evening)
                    .withPopcorn(2)
                    .withPromoCode("KADR10")
                    .build();
        } catch (IllegalStateException e) {
            Logger.log("Раннер", "Замовлення не створено, неповний об'єкт у систему не потрапив");
        }

        // Незмінність готового замовлення
        try {
            manual.getSeats().add("9-9");
        } catch (UnsupportedOperationException e) {
            Logger.log("Раннер", "Готове замовлення незмінне: додати місце не вдалося, місць лишилось "
                    + manual.getSeats().size());
        }
    }
}
