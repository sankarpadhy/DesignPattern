package behavioral.command;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Command Pattern implementation
 */
public class CommandPatternTest {
    private RemoteControl remote;
    private Light livingRoomLight;
    private Fan ceilingFan;
    private Stereo stereo;

    @Before
    public void setUp() {
        remote = new RemoteControl();
        livingRoomLight = new Light("Living Room");
        ceilingFan = new Fan("Living Room");
        stereo = new Stereo("Living Room");
    }

    @Test
    public void testLightCommands() {
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        remote.setCommand(0, lightOn, lightOff);
        
        // Test turning light on
        remote.onButtonWasPushed(0);
        assertTrue(livingRoomLight.isOn());

        // Test turning light off
        remote.offButtonWasPushed(0);
        assertFalse(livingRoomLight.isOn());

        // Test undo functionality
        remote.undoButtonWasPushed();
        assertTrue(livingRoomLight.isOn());
    }

    @Test
    public void testFanCommands() {
        Command fanHigh = new FanHighCommand(ceilingFan);
        Command fanOff = new FanOffCommand(ceilingFan);

        remote.setCommand(1, fanHigh, fanOff);

        // Test setting fan to high
        remote.onButtonWasPushed(1);
        assertEquals(Fan.HIGH, ceilingFan.getSpeed());

        // Test turning fan off
        remote.offButtonWasPushed(1);
        assertEquals(Fan.OFF, ceilingFan.getSpeed());

        // Test undo functionality
        remote.undoButtonWasPushed();
        assertEquals(Fan.HIGH, ceilingFan.getSpeed());
    }

    @Test
    public void testStereoCommands() {
        Command stereoOnWithCD = new StereoOnWithCDCommand(stereo);
        Command stereoOff = new StereoOffCommand(stereo);

        remote.setCommand(2, stereoOnWithCD, stereoOff);

        // Test turning stereo on with CD
        remote.onButtonWasPushed(2);
        assertTrue(stereo.isOn());
        assertEquals("CD", stereo.getSource());
        assertEquals(11, stereo.getVolume());

        // Test turning stereo off
        remote.offButtonWasPushed(2);
        assertFalse(stereo.isOn());

        // Test undo functionality
        remote.undoButtonWasPushed();
        assertTrue(stereo.isOn());
        assertEquals("CD", stereo.getSource());
    }

    @Test
    public void testMacroCommand() {
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command fanHigh = new FanHighCommand(ceilingFan);
        Command stereoOn = new StereoOnWithCDCommand(stereo);

        Command[] partyOn = { lightOn, fanHigh, stereoOn };
        Command partyMacro = new MacroCommand(partyOn);

        remote.setCommand(3, partyMacro, null);

        // Test party macro command
        remote.onButtonWasPushed(3);
        assertTrue(livingRoomLight.isOn());
        assertEquals(Fan.HIGH, ceilingFan.getSpeed());
        assertTrue(stereo.isOn());
        assertEquals("CD", stereo.getSource());

        // Test undo functionality
        remote.undoButtonWasPushed();
        assertFalse(livingRoomLight.isOn());
        assertEquals(Fan.OFF, ceilingFan.getSpeed());
        assertFalse(stereo.isOn());
    }
}
