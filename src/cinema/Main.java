package cinema;

import cinema.abstractfactory.DolbyAtmosFactory;
import cinema.abstractfactory.Format2DFactory;
import cinema.abstractfactory.Format3DFactory;
import cinema.abstractfactory.ScreeningLauncher;
import cinema.abstractfactory.ImaxFactory;
import cinema.builder.Order;
import cinema.builder.OrderDirector;
import cinema.builder.Screening;
import cinema.builder.StandardOrderBuilder;
import cinema.domain.Logger;
import cinema.factorymethod.AppChannel;
import cinema.factorymethod.BoxOfficeChannel;
import cinema.factorymethod.SalesLedger;
import cinema.factorymethod.WebsiteChannel;
import cinema.factorymethod.SelfServiceTerminalChannel;

import java.io.PrintStream;
import java.time.LocalDateTime;

/** Клас-раннер лабораторної №10. Окремий метод для кожної ситуації. */
public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        task1Builder();
        task2FactoryMethod();
        System.out.println();
        task3AbstractFactory();
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

    // ---------- Завдання 2: Factory Method ----------
    static void task2FactoryMethod() {
        System.out.println("=== Завдання 2. Квитки для різних каналів продажу (Factory Method) ===");
        Screening evening = new Screening("Дюна", 2, LocalDateTime.of(2026, 11, 1, 19, 0));
        SalesLedger ledger = new SalesLedger();

        // Сценарій 1: по одному квитку через касу, сайт і застосунок
        Logger.log("Раннер", "Сценарій 1: продаж через касу, сайт і застосунок");
        new BoxOfficeChannel(ledger).sell(evening, "5-7");
        new WebsiteChannel(ledger).sell(evening, "5-8");
        new AppChannel(ledger).sell(evening, "5-9");

        // Сценарій 2-3: підключення термінала новими класами й продаж через нього
        Logger.log("Раннер", "Сценарій 2: підключено термінал самообслуговування (нові класи, SalesChannel не змінено)");
        Logger.log("Раннер", "Сценарій 3: продаж через термінал");
        new SelfServiceTerminalChannel(ledger).sell(evening, "6-1");

        Logger.log("Раннер", "Усього продажів у журналі: " + ledger.total());
        ledger.records().forEach(r -> Logger.log("Журнал", r));
    }

    // ---------- Завдання 3: Abstract Factory ----------
    static void task3AbstractFactory() {
        System.out.println("=== Завдання 3. Налаштування залу під формат показу (Abstract Factory) ===");
        Screening s2d = new Screening("Дюна", 1, LocalDateTime.of(2026, 11, 3, 17, 0));
        Screening s3d = new Screening("Аватар", 2, LocalDateTime.of(2026, 11, 3, 19, 0));
        Screening sAtmos = new Screening("Інтерстеллар", 3, LocalDateTime.of(2026, 11, 3, 21, 0));
        Screening sImax = new Screening("Оппенгаймер", 4, LocalDateTime.of(2026, 11, 3, 22, 30));

        ScreeningLauncher launcher = new ScreeningLauncher();

        Logger.log("Раннер", "Сценарій 1: сеанси 2D, 3D і Dolby Atmos одним кодом запуску");
        launcher.launch(s2d, new Format2DFactory());
        launcher.launch(s3d, new Format3DFactory());
        launcher.launch(sAtmos, new DolbyAtmosFactory());

        Logger.log("Раннер", "Сценарій 2: додано формат IMAX (нові класи, ScreeningLauncher не змінено)");
        launcher.launch(sImax, new ImaxFactory());
    }


}