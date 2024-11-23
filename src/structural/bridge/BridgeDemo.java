package structural.bridge;

/**
 * Demonstration of Bridge pattern
 */
public class BridgeDemo {
    public static void main(String[] args) {
        testDevice(new TV());
        testDevice(new Radio());
    }

    public static void testDevice(Device device) {
        System.out.println("Tests with basic remote.");
        RemoteControl basicRemote = new RemoteControl(device);
        basicRemote.togglePower();
        device.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemoteControl advancedRemote = new AdvancedRemoteControl(device);
        advancedRemote.togglePower();
        advancedRemote.mute();
        advancedRemote.setChannel(5);
        device.printStatus();
    }
}
