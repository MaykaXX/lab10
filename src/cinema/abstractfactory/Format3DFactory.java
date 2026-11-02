package cinema.abstractfactory;

import cinema.builder.Screening;

/** ConcreteFactory: формат 3D. */
public class Format3DFactory implements HallEquipmentFactory {
    private static final String FORMAT = "3D";

    @Override
    public String formatName() {
        return FORMAT;
    }

    @Override
    public FilmCopy createFilmCopy(Screening screening) {
        return new Copy3D(screening);
    }

    @Override
    public ProjectorProfile createProjectorProfile() {
        return new Projector3D();
    }

    @Override
    public SoundProfile createSoundProfile() {
        return new Sound3D();
    }

    private static class Copy3D implements FilmCopy {
        private final Screening screening;

        Copy3D(Screening screening) {
            this.screening = screening;
        }

        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Копія 3D (стереопара, 48 к/с): " + screening;
        }
    }

    private static class Projector3D implements ProjectorProfile {
        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Профіль проектора 3D (поляризація, підвищена яскравість)";
        }
    }

    private static class Sound3D implements SoundProfile {
        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Звук 7.1";
        }
    }
}
