package behavioral.command.animation;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages sound effects for the Command Pattern animation
 */
public class SoundManager {
    private static final Map<String, Clip> soundCache = new HashMap<>();
    private static final int SAMPLE_RATE = 44100;
    
    /**
     * Generates and plays a click sound
     */
    public static void playClickSound() {
        playSound("click", generateClickSound());
    }
    
    /**
     * Generates and plays a fan sound
     */
    public static void playFanSound() {
        playSound("fan", generateFanSound());
    }
    
    /**
     * Generates and plays a stereo sound
     */
    public static void playStereoSound() {
        playSound("stereo", generateStereoSound());
    }

    private static void playSound(String key, byte[] soundData) {
        try {
            if (!soundCache.containsKey(key)) {
                AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
                AudioInputStream ais = new AudioInputStream(
                    new ByteArrayInputStream(soundData),
                    format,
                    soundData.length
                );
                
                Clip clip = AudioSystem.getClip();
                clip.open(ais);
                soundCache.put(key, clip);
            }
            
            Clip clip = soundCache.get(key);
            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] generateClickSound() {
        // Generate a short click sound
        byte[] data = new byte[SAMPLE_RATE / 10]; // 0.1 second
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte)(Math.sin(i / 10.0) * 127.0 * Math.exp(-i / 1000.0));
        }
        return data;
    }

    private static byte[] generateFanSound() {
        // Generate a whooshing fan sound
        byte[] data = new byte[SAMPLE_RATE / 2]; // 0.5 second
        for (int i = 0; i < data.length; i++) {
            double t = i / (double)SAMPLE_RATE;
            data[i] = (byte)(
                Math.sin(2 * Math.PI * 100 * t) * // Base frequency
                Math.sin(2 * Math.PI * 5 * t) *   // Modulation
                127.0 * Math.exp(-t)              // Decay
            );
        }
        return data;
    }

    private static byte[] generateStereoSound() {
        // Generate a musical tone
        byte[] data = new byte[SAMPLE_RATE / 4]; // 0.25 second
        for (int i = 0; i < data.length; i++) {
            double t = i / (double)SAMPLE_RATE;
            data[i] = (byte)(
                (Math.sin(2 * Math.PI * 440 * t) +  // A4 note
                Math.sin(2 * Math.PI * 554.37 * t)) * // C#5 note
                63.5 * Math.exp(-t * 2)               // Decay
            );
        }
        return data;
    }
}
