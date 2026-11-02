package cinema.abstractfactory;

import cinema.builder.Screening;

/**
 * Роль у Abstract Factory: AbstractFactory.
 * Одна фабрика = один формат: усі три елементи набору створюються лише разом
 * і лише одним класом, тому змішати формати неможливо.
 */
public interface HallEquipmentFactory {
    /** Назва формату (2D, 3D, Dolby Atmos, ...). */
    String formatName();

    FilmCopy createFilmCopy(Screening screening);

    ProjectorProfile createProjectorProfile();

    SoundProfile createSoundProfile();
}
