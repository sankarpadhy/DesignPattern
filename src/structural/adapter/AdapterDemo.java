package structural.adapter;

/**
 * Demonstration of the Adapter pattern
 * Shows how to use an adapter to play different types of audio files
 */
public class AdapterDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        System.out.println("Playing different types of audio files:");
        System.out.println("=====================================");

        // Playing MP3 file (native support)
        System.out.println("\nTesting MP3 format (native):");
        audioPlayer.play("mp3", "song.mp3");

        // Playing MP4 file (using adapter)
        System.out.println("\nTesting MP4 format (adapted):");
        audioPlayer.play("mp4", "movie.mp4");

        // Playing VLC file (using adapter)
        System.out.println("\nTesting VLC format (adapted):");
        audioPlayer.play("vlc", "video.vlc");

        // Trying to play unsupported format
        System.out.println("\nTesting unsupported format:");
        audioPlayer.play("avi", "video.avi");
    }
}
