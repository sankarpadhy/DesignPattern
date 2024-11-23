package structural.facade;

import structural.facade.subsystem.*;

/**
 * Facade class that simplifies the home theater system
 */
public class HomeTheaterFacade {
    private Amplifier amp;
    private DVDPlayer dvd;
    private Projector projector;
    private TheaterLights lights;

    public HomeTheaterFacade() {
        this.amp = Amplifier.getInstance();
        this.dvd = DVDPlayer.getInstance();
        this.projector = Projector.getInstance();
        this.lights = TheaterLights.getInstance();
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(10);
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setDvd(dvd);
        amp.setSurroundSound();
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down the movie theater...");
        lights.on();
        dvd.stop();
        dvd.eject();
        dvd.off();
        amp.off();
        projector.off();
    }

    public void listenToMusic() {
        System.out.println("Get ready for music experience...");
        lights.dim(20);
        amp.on();
        amp.setVolume(5);
        amp.setTwoChannelAudio();
    }

    public void endMusic() {
        System.out.println("Shutting down music system...");
        amp.off();
        lights.on();
    }
}
