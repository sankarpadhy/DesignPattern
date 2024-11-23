package structural.adapter;

/**
 * Client class that uses the MediaPlayer interface
 * 
 * This class shows how the Adapter pattern allows incompatible interfaces to work together:
 * - Directly plays mp3 files
 * - Uses MediaAdapter for vlc and mp4 formats
 */
public class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        // Built-in support for mp3 music files
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        }
        // MediaAdapter provides support for other formats
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else {
            System.out.println("Invalid media type: " + audioType + " format not supported");
        }
    }
}
