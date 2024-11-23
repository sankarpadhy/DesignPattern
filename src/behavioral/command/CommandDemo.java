package behavioral.command;

/**
 * Demo class for Command Pattern
 */
public class CommandDemo {
    public static void main(String[] args) {
        // Create receivers
        Light livingRoomLight = new Light("Living Room");
        Fan livingRoomFan = new Fan("Living Room");
        Stereo livingRoomStereo = new Stereo("Living Room");
        
        // Create commands
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);
        Command fanHigh = new FanHighCommand(livingRoomFan);
        Command fanOff = new FanOffCommand(livingRoomFan);
        Command stereoOn = new StereoOnWithCDCommand(livingRoomStereo);
        Command stereoOff = new StereoOffCommand(livingRoomStereo);
        
        // Create remote control
        RemoteControl remote = new RemoteControl();
        
        // Set up commands
        remote.setCommand(0, lightOn, lightOff);
        remote.setCommand(1, fanHigh, fanOff);
        remote.setCommand(2, stereoOn, stereoOff);
        
        System.out.println(remote);
        
        // Test light
        remote.onButtonWasPushed(0);
        System.out.println(remote);
        
        // Test fan
        remote.onButtonWasPushed(1);
        System.out.println(remote);
        
        // Turn off fan
        remote.offButtonWasPushed(1);
        System.out.println(remote);
        
        // Test undo
        remote.undoButtonWasPushed();
        System.out.println(remote);
        
        // Test stereo
        remote.onButtonWasPushed(2);
        System.out.println(remote);
        
        // Turn off stereo
        remote.offButtonWasPushed(2);
        System.out.println(remote);
        
        // Test party mode
        Command[] partyOn = { lightOn, fanHigh, stereoOn };
        Command partyMacro = new MacroCommand(partyOn);
        remote.setCommand(3, partyMacro, null);
        
        System.out.println("--- Pushing Party Mode On---");
        remote.onButtonWasPushed(3);
        System.out.println(remote);
    }
}
