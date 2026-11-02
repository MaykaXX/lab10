package cinema.abstractfactory;

import cinema.abstractfactory.FilmCopy;
import cinema.abstractfactory.HallEquipmentFactory;
import cinema.abstractfactory.ProjectorProfile;
import cinema.abstractfactory.SoundProfile;
import cinema.builder.Screening;

/**
 * Новий формат IMAX. Додається окремим пакетом: ScreeningLauncher і наявні фабрики не змінювались.
 */
public class ImaxFactory implements HallEquipmentFactory {
    private static final String FORMAT = "IMAX";

    @Override
    public String formatName() {
        return FORMAT;
    }

    @Override
    public FilmCopy createFilmCopy(Screening screening) {
        return new ImaxCopy(screening);
    }

    @Override
    public ProjectorProfile createProjectorProfile() {
        return new ImaxProjector();
    }

    @Override
    public SoundProfile createSoundProfile() {
        return new ImaxSound();
    }

    private static class ImaxCopy implements FilmCopy {
        private final Screening screening;

        ImaxCopy(Screening screening) {
            this.screening = screening;
        }

        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Копія IMAX (розширене співвідношення 1.43:1): " + screening;
        }
    }

    private static class ImaxProjector implements ProjectorProfile {
        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Профіль проектора IMAX (двопроекторна лазерна система)";
        }
    }

    private static class ImaxSound implements SoundProfile {
        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Звук IMAX 12-канальний";
        }
    }
}
