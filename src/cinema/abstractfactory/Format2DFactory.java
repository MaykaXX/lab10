package cinema.abstractfactory;

import cinema.builder.Screening;

/** ConcreteFactory: формат 2D. Елементи набору — вкладені класи, назовні не видимі. */
public class Format2DFactory implements HallEquipmentFactory {
    private static final String FORMAT = "2D";

    @Override
    public String formatName() {
        return FORMAT;
    }

    @Override
    public FilmCopy createFilmCopy(Screening screening) {
        return new Copy2D(screening);
    }

    @Override
    public ProjectorProfile createProjectorProfile() {
        return new Projector2D();
    }

    @Override
    public SoundProfile createSoundProfile() {
        return new Sound2D();
    }

    private static class Copy2D implements FilmCopy {
        private final Screening screening;

        Copy2D(Screening screening) {
            this.screening = screening;
        }

        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Копія 2D (DCP, 24 к/с): " + screening;
        }
    }

    private static class Projector2D implements ProjectorProfile {
        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Профіль проектора 2D (одна лінза, стандартна яскравість)";
        }
    }

    private static class Sound2D implements SoundProfile {
        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Звук 5.1";
        }
    }
}
