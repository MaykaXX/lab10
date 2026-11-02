package cinema.abstractfactory;

import cinema.builder.Screening;
import cinema.domain.Logger;

/**
 * Роль у Abstract Factory: Client. Код запуску сеансу.
 * Знає лише абстракції (HallEquipmentFactory, FilmCopy, ProjectorProfile, SoundProfile),
 * жодного конкретного класу формату тут немає: новий формат не вимагає змін у цьому класі.
 */
public class ScreeningLauncher {
    private static final String SRC = "Запуск сеансу";

    public void launch(Screening screening, HallEquipmentFactory factory) {
        Logger.log(SRC, "Запуск сеансу: " + screening + ", формат " + factory.formatName());

        FilmCopy copy = factory.createFilmCopy(screening);
        ProjectorProfile projector = factory.createProjectorProfile();
        SoundProfile sound = factory.createSoundProfile();

        Logger.log(SRC, "Копія фільму:      " + copy.describe());
        Logger.log(SRC, "Профіль проектора: " + projector.describe());
        Logger.log(SRC, "Звуковий профіль:  " + sound.describe());

        String format = factory.formatName();
        boolean consistent = format.equals(copy.format())
                && format.equals(projector.format())
                && format.equals(sound.format());
        if (!consistent) {
            throw new IllegalStateException("Набір залу неузгоджений: " + copy.format() + " / "
                    + projector.format() + " / " + sound.format());
        }
        Logger.log(SRC, "Перевірка: усі елементи набору належать до формату " + format);
        Logger.log(SRC, "Зал налаштовано, сеанс розпочато");
    }
}
