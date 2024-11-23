package behavioral.command;

/**
 * Demonstration of the Command pattern
 * Shows how to use commands with undo functionality in a smart home context
 */
public class CommandDemo {
    public static void main(String[] args) {
        // Create the receivers (lights)
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");

        // Create the commands
        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
        Command kitchenLightOn = new LightOnCommand(kitchenLight);
        Command kitchenLightOff = new LightOffCommand(kitchenLight);

        // Create the invoker (remote control)
        RemoteControl remote = new RemoteControl();

        // Set up the remote control slots
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, kitchenLightOn, kitchenLightOff);

        // Show remote control setup
        System.out.println("Remote Control Setup:");
        System.out.println(remote);

        // Test the remote control
        System.out.println("Testing Remote Control:");
        System.out.println("------------------------");

        System.out.println("\nTurning on Living Room Light:");
        remote.onButtonPressed(0);

        System.out.println("\nTurning on Kitchen Light:");
        remote.onButtonPressed(1);

        System.out.println("\nTurning off Kitchen Light:");
        remote.offButtonPressed(1);

        System.out.println("\nUndo last operation (Kitchen Light Off):");
        remote.undoButtonPressed();

        System.out.println("\nUndo previous operation (Kitchen Light On):");
        remote.undoButtonPressed();

        System.out.println("\nUndo previous operation (Living Room Light On):");
        remote.undoButtonPressed();

        System.out.println("\nTrying to undo with no more commands:");
        remote.undoButtonPressed();
    }
}
