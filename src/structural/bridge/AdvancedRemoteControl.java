package structural.bridge;

/**
 * Refined Abstraction adding more functionality to the remote control
 */
public class AdvancedRemoteControl extends RemoteControl {
    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
    }

    public void setChannel(int channel) {
        device.setChannel(channel);
    }
}
