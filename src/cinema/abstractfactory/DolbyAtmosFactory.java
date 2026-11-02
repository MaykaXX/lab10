package cinema.abstractfactory;

import cinema.builder.Screening;

/** ConcreteFactory: формат Dolby Atmos. */
public class DolbyAtmosFactory implements HallEquipmentFactory {
    private static final String FORMAT = "Dolby Atmos";

    @Override
    public String formatName() {
        return FORMAT;
    }

    @Override
    public FilmCopy createFilmCopy(Screening screening) {
        return new CopyAtmos(screening);
    }

    @Override
    public ProjectorProfile createProjectorProfile() {
        return new ProjectorAtmos();
    }

    @Override
    public SoundProfile createSoundProfile() {
        return new SoundAtmos();
    }

    private static class CopyAtmos implements FilmCopy {
        private final Screening screening;

        CopyAtmos(Screening screening) {
            this.screening = screening;
        }

        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Копія Dolby Atmos (DCP + об'єктний звук): " + screening;
        }
    }

    private static class ProjectorAtmos implements ProjectorProfile {
        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Профіль проектора Atmos (високий контраст, лазерне джерело)";
        }
    }

    private static class SoundAtmos implements SoundProfile {
        public String format() {
            return FORMAT;
        }

        public String describe() {
            return "Об'єктний звук Dolby Atmos (до 64 каналів)";
        }
    }
}
