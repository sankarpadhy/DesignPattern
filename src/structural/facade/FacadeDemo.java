package structural.facade;

/**
 * Demonstration of Facade Pattern
 */
public class FacadeDemo {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();

        // Watch a movie
        System.out.println("=== Starting Movie Night ===");
        homeTheater.watchMovie("Inception");
        
        System.out.println("\n=== Movie Finished ===");
        homeTheater.endMovie();

        // Listen to music
        System.out.println("\n=== Starting Music Session ===");
        homeTheater.listenToMusic();
        
        System.out.println("\n=== Music Session Finished ===");
        homeTheater.endMusic();
    }
}
