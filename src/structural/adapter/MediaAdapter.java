package structural.adapter;

/**
 * Adapter class
 * 
 * This class adapts the AdvancedMediaPlayer interface to work with the MediaPlayer interface.
 * Key aspects of the Adapter pattern demonstrated here:
 * 1. Implements the target interface (MediaPlayer)
 * 2. Holds an instance of the adaptee (AdvancedMediaPlayer)
 * 3. Translates calls between the interfaces
 */
public class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
        // Other formats not supported
    }
}
